package comapps.stansbluenote.app.food;


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
public class MenuListFragment extends ListFragment {

    private static final String TAG = "MENULISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";


    private BackendlessCollection<MenuObject> menuitems;
    private List<MenuObject> menuList = new ArrayList<>();
    private MenuListAdapter adapter;




    public MenuListFragment() {

    }

    public static MenuListFragment newInstance(int page) {
        MenuListFragment fragment = new MenuListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);
        


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.menulistfragment, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



//        int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);




        adapter = new MenuListAdapter(getActivity(), menuList);

        setListAdapter(adapter);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("x ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

   //     String whereClause = "groupsort = " + String.valueOf(groupId);
   //     Log.i(TAG, whereClause);
   //     query.setWhereClause(whereClause);

        Backendless.Persistence.of( MenuObject.class ).find(query, new LoadingCallback<BackendlessCollection<MenuObject>>
                (getActivity(), "Loading items...", true) {

            @Override
            public void handleResponse(BackendlessCollection<MenuObject> menuBackendlessCollection) {
                menuitems = menuBackendlessCollection;
                addMoreItems(menuBackendlessCollection);
                super.handleResponse(menuBackendlessCollection);
            }

        });

        adapter.notifyDataSetChanged();

    }






    private void addMoreItems( BackendlessCollection<MenuObject> nextPage) {
        menuList.clear();
        menuList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(0);
    }





}






















