package comapps.stansbluenote.app.specials;


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
public class SpecialsListFragment extends ListFragment {

    private static final String TAG = "SPECIALSLISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";

    private BackendlessCollection<SpecialsObject> specials;
    private List<SpecialsObject> specialsList = new ArrayList<>();
    private SpecialsListAdapter adapter;




    public SpecialsListFragment() {

    }

    public static SpecialsListFragment newInstance(int page) {
        SpecialsListFragment fragment = new SpecialsListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);
        


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.specialslistfragment, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //    int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);




        adapter = new SpecialsListAdapter(getActivity(), specialsList);

        setListAdapter(adapter);

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("x ASC");
        queryOptions.setPageSize(10);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

   //     String whereClause = "groupsort = " + String.valueOf(groupId);
   //     Log.i(TAG, whereClause);
   //     query.setWhereClause(whereClause);

        Backendless.Persistence.of( SpecialsObject.class ).find(query, new LoadingCallback<BackendlessCollection<SpecialsObject>>
                (getActivity(), "Loading items...", true) {

            @Override
            public void handleResponse(BackendlessCollection<SpecialsObject> specialsBackendlessCollection) {
                specials = specialsBackendlessCollection;
                addMoreItems(specialsBackendlessCollection);
                super.handleResponse(specialsBackendlessCollection);
            }

        });

        adapter.notifyDataSetChanged();

    }



    private void addMoreItems( BackendlessCollection<SpecialsObject> nextPage) {
        specialsList.clear();
        specialsList.addAll(nextPage.getCurrentPage());
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.WHITE));
        getListView().setDividerHeight(1);
    }


}






















