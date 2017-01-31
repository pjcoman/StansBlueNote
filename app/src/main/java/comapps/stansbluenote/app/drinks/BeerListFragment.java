package comapps.stansbluenote.app.drinks;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.List;

import comapps.stansbluenote.app.LoadingCallback;
import comapps.stansbluenote.app.R;


/**
 * Created by me on 9/29/2015.
 */
public class BeerListFragment extends ListFragment {

    private static final String TAG = "BEERLISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";
    private int x = 1;
    private int y = 0;


    private BackendlessCollection<BeerObject> beers;
    private List<BeerObject> beerList = new ArrayList<>();
    private BeerListAdapter adapter;




    public BeerListFragment() {

    }

    public static BeerListFragment newInstance(int page) {
        BeerListFragment fragment = new BeerListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);
        


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.beerlistfragment_be, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //    int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);




        adapter = new BeerListAdapter(getActivity(), beerList);

        setListAdapter(adapter);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("name ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

   //     String whereClause = "groupsort = " + String.valueOf(groupId);
   //     Log.i(TAG, whereClause);
   //     query.setWhereClause(whereClause);

        Backendless.Persistence.of( BeerObject.class ).find(query, new LoadingCallback<BackendlessCollection<BeerObject>>
                (getActivity(), "Loading items...", true) {

            @Override
            public void handleResponse(BackendlessCollection<BeerObject> beersBackendlessCollection) {
                beers = beersBackendlessCollection;
                addMoreItems(beersBackendlessCollection);
                super.handleResponse(beersBackendlessCollection);
            }

        });

        adapter.notifyDataSetChanged();

    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        x = x + 1;

        if (x == 8) {

            x = 0;

        }
//        int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);
//        String groupIdString = Integer.toString(groupId);
//        System.out.println(TAG + "Group ID ARG_PAGE_NUMBER is " + groupIdString);






        QueryOptions queryOptions = new QueryOptions();



        switch (x) {
            case 0:
                queryOptions.addSortByOption("name ASC");
                break;
            case 1:
                queryOptions.addSortByOption("name DESC");
                break;
            case 2:
                queryOptions.addSortByOption("abv ASC");
                break;
            case 3:
                queryOptions.addSortByOption("abv DESC");
                break;
            case 4:
                queryOptions.addSortByOption("type ASC");
                break;
            case 5:
                queryOptions.addSortByOption("type DESC");
                break;
            case 6:
                queryOptions.addSortByOption("wherefrom ASC");
                break;
            case 7:
                queryOptions.addSortByOption("wherefrom DESC");
                break;
        }


        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);


  //      String whereClause = "groupsort = " + String.valueOf(groupId);
  //      Log.i(TAG, whereClause);
  //      query.setWhereClause(whereClause);

        Backendless.Persistence.of( BeerObject.class ).find(query, new LoadingCallback<BackendlessCollection<BeerObject>>
                (getActivity(), "Sorting beer list...", true) {

            @Override
            public void handleResponse(BackendlessCollection<BeerObject> beersBackendlessCollection) {
                beers = beersBackendlessCollection;
                addMoreItems( beersBackendlessCollection );
                super.handleResponse(beersBackendlessCollection);

                String[] toastSort = new String[8];
                toastSort[0] = "Sorted by name\nascending";
                toastSort[1] = "by name\ndescending";
                toastSort[2] = "by abv (alcohol by volume)\nascending";
                toastSort[3] = "by abv\ndescending";
                toastSort[4] = "by type\n ascending";
                toastSort[5] = "by type\ndescending";
                toastSort[6] = "by where from\nascending";
                toastSort[7] = "by where from\ndescending";

                String tm = toastSort[x];

                Toast sort = Toast.makeText(getActivity(), tm, Toast.LENGTH_SHORT);
                centerText(sort.getView());
                sort.setGravity(Gravity.CENTER, 0, 200);
                sort.show();
            }

        });



    }

    private void addMoreItems( BackendlessCollection<BeerObject> nextPage) {
        beerList.clear();
        beerList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }

    private  void centerText(View view) {
        if( view instanceof TextView){
            ((TextView) view).setGravity(Gravity.CENTER);
        }else if( view instanceof ViewGroup){
            ViewGroup group = (ViewGroup) view;
            int n = group.getChildCount();
            for( int i = 0; i<n; i++ ){
                centerText(group.getChildAt(i));
            }
        }
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.WHITE));
        getListView().setDividerHeight(1);
    }



}






















