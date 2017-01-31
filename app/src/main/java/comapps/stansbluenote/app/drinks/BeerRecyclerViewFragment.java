package comapps.stansbluenote.app.drinks;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.List;

import comapps.stansbluenote.app.LoadingCallback;
import comapps.stansbluenote.app.MainActivity;
import comapps.stansbluenote.app.R;

/**
 * Created by me on 5/18/2016.
 */
public class BeerRecyclerViewFragment extends Fragment {
    private static final String TAG = "BEERRECYCLERVIEWFRAGMENT ";


    private BackendlessCollection<BeerObject> beers;
    private List<BeerObject> beerList = new ArrayList<>();
    private BeerRecyclerViewAdapter adapter;

    com.melnykov.fab.FloatingActionButton fab;


    RecyclerView recyclerView;



    public BeerRecyclerViewFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.beerrecyclerview, container, false);




        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        //    recyclerView.setItemAnimator(new SlideInUpAnimator());


        final StaggeredGridLayoutManager manager;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
            manager.setGapStrategy((StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS));
            recyclerView.setLayoutManager(manager);
        }
            else {

            manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
            manager.setGapStrategy((StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS));
            recyclerView.setLayoutManager(manager);

        }

        fab = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent i = new Intent(v.getContext(), MainActivity.class);

                    startActivity(i);






            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //    Log.i(TAG, "ScrollListener dx " + String.valueOf(dx));

                if (dx == 0) {

                    fab.setVisibility(View.INVISIBLE);

                } else {

                    fab.setVisibility(View.VISIBLE);

                }
            }
        });





        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);



        return rootView;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("name ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

        Backendless.Persistence.of( BeerObject.class ).find(query, new LoadingCallback<BackendlessCollection<BeerObject>>
                (getActivity(), "loading taps...", true) {

            @Override
            public void handleResponse(BackendlessCollection<BeerObject> beersBackendlessCollection) {
                beers = beersBackendlessCollection;
                addMoreItems(beersBackendlessCollection);
                super.handleResponse(beersBackendlessCollection);
            }

        });

        Backendless.Persistence.of( BeerObject.class ).find(query, new LoadingCallback<BackendlessCollection<BeerObject>>
                (getActivity(), "loading taps...", true) {

            @Override
            public void handleResponse(BackendlessCollection<BeerObject> beersBackendlessCollection) {
                beers = beersBackendlessCollection;
                addMoreItems(beersBackendlessCollection);
                super.handleResponse(beersBackendlessCollection);
            }

        });

        adapter = new BeerRecyclerViewAdapter(beerList, getActivity().getApplicationContext());

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void addMoreItems(BackendlessCollection<BeerObject> nextPage) {
        beerList.clear();
        beerList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }



    public void onBackPressed() {

getActivity().finish();

    }





}



