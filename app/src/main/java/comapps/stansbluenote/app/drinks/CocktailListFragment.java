package comapps.stansbluenote.app.drinks;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
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
import comapps.stansbluenote.app.R;


/**
 * Created by me on 9/29/2015.
 */
public class CocktailListFragment extends ListFragment {

    private static final String TAG = "COCKTAILLISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";



    private BackendlessCollection<CocktailObject> cocktails;
    private List<CocktailObject> cocktailList = new ArrayList<>();
    private CocktailListAdapter adapter;




    public CocktailListFragment() {

    }

    public static CocktailListFragment newInstance(int page) {
        CocktailListFragment fragment = new CocktailListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);
        


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.cocktaillistfragment, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //    int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);




        adapter = new CocktailListAdapter(getActivity(), cocktailList);

        setListAdapter(adapter);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("name ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

   //     String whereClause = "groupsort = " + String.valueOf(groupId);
   //     Log.i(TAG, whereClause);
   //     query.setWhereClause(whereClause);

        Backendless.Persistence.of( CocktailObject.class ).find(query, new LoadingCallback<BackendlessCollection<CocktailObject>>
                (getActivity(), "Loading items...", true) {

            @Override
            public void handleResponse(BackendlessCollection<CocktailObject> cocktailsBackendlessCollection) {
                cocktails = cocktailsBackendlessCollection;
                addMoreItems(cocktailsBackendlessCollection);
                super.handleResponse(cocktailsBackendlessCollection);
            }

        });

        adapter.notifyDataSetChanged();

    }





    private void addMoreItems( BackendlessCollection<CocktailObject> nextPage) {
        cocktailList.clear();
        cocktailList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.WHITE));
        getListView().setDividerHeight(1);
    }



}






















