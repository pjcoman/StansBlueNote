package comapps.stansbluenote.app.people;


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
public class StaffListFragment extends ListFragment {

    private static final String TAG = "STAFFLISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";



    private BackendlessCollection<StaffObject> staff;
    private List<StaffObject> staffList = new ArrayList<>();
    private StaffListAdapter adapter;




    public StaffListFragment() {

    }

    public static StaffListFragment newInstance(int page) {
        StaffListFragment fragment = new StaffListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);
        


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stafflistfragment, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //    int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);




        adapter = new StaffListAdapter(getActivity(), staffList);

        setListAdapter(adapter);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("name ASC");
        queryOptions.setPageSize(100);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

   //     String whereClause = "groupsort = " + String.valueOf(groupId);
   //     Log.i(TAG, whereClause);
   //     query.setWhereClause(whereClause);

        Backendless.Persistence.of( StaffObject.class ).find(query, new LoadingCallback<BackendlessCollection<StaffObject>>
                (getActivity(), "Loading items...", true) {

            @Override
            public void handleResponse(BackendlessCollection<StaffObject> staffBackendlessCollection) {
                staff = staffBackendlessCollection;
                addMoreItems(staffBackendlessCollection);
                super.handleResponse(staffBackendlessCollection);
            }

        });

        adapter.notifyDataSetChanged();

    }




    private void addMoreItems( BackendlessCollection<StaffObject> nextPage) {
        staffList.clear();
        staffList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.WHITE));
        getListView().setDividerHeight(1);
    }


}






















