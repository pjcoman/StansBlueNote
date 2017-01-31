package comapps.stansbluenote.app.drinks;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comapps.stansbluenote.app.LoadingCallback;
import comapps.stansbluenote.app.R;

/**
 * Created by me on 5/18/2016.
 */
public class BeerRecyclerViewFragmentList extends Fragment {
    private static final String TAG = "BEERRECYCLERVIEWFRAGMENT ";


    private BackendlessCollection<BeerObject> beersBackendlessCollection;
    private List<BeerObject> beerList = new ArrayList<>();
    private BeerRecyclerViewAdapterList adapter;

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";

    FloatingActionButton fab;

    private int x = 1;


    RecyclerView recyclerViewList;
    TextView fabTitle;



    public BeerRecyclerViewFragmentList() {

    }

    public static BeerRecyclerViewFragmentList newInstance(int page) {
        BeerRecyclerViewFragmentList list = new BeerRecyclerViewFragmentList();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        list.setArguments(args);



        return list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.beerrecyclerview_list, container, false);




        recyclerViewList = (RecyclerView) rootView.findViewById(R.id.recyclerviewlist);
        //    recyclerView.setItemAnimator(new SlideInUpAnimator());

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
         fabTitle = (TextView) rootView.findViewById(R.id.fabText);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "fab was clicked, x = " + x, Toast.LENGTH_SHORT).show();

                x = x + 1;

                if (x == 8) {

                    x = 0;

                }


            }
        });

        recyclerViewList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                    Log.i(TAG, "ScrollListener dy " + String.valueOf(dy));

                if (dy > 20 || dy < -20) {

                    fab.setVisibility(View.INVISIBLE);
                    fabTitle.setVisibility(View.INVISIBLE);

                } else {

                    fab.setVisibility(View.VISIBLE);
                    fabTitle.setVisibility(View.VISIBLE);

                }
            }
        });


        final LinearLayoutManager manager;



        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewList.setLayoutManager(manager);





        return rootView;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("order ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

        Backendless.Data.of( BeerObject.class ).find(query, new LoadingCallback<BackendlessCollection<BeerObject>>
                (getActivity(), "loading taps...", true) {

            @Override
            public void handleResponse(BackendlessCollection<BeerObject> response) {
                beersBackendlessCollection = response;
                addMoreItems(beersBackendlessCollection);
                super.handleResponse(beersBackendlessCollection);
            }

        });

        adapter = new BeerRecyclerViewAdapterList(beerList, getActivity().getApplicationContext());

        recyclerViewList.setAdapter(adapter);

        adapter.notifyDataSetChanged();



    }

    private void addMoreItems(BackendlessCollection<BeerObject> nextPage) {
        beerList.clear();
        beerList.addAll(nextPage.getCurrentPage());

        Collections.sort(beerList, new Comparator<BeerObject>() {
            @Override
            public int compare(BeerObject lhs, BeerObject rhs) {
                fabTitle.setText("name");
                return lhs.getName().compareTo(rhs.getName());
            }



        });





        adapter.notifyDataSetChanged();

    }





}



