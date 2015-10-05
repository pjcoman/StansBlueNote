package comapps.stansbluenote.app;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 9/29/2015.
 */
public class StaffListViewFragment extends ListFragment {


    private List<StaffListObject> staffObject;
    StaffListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.stafflistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "stansstaff").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("name");

            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            staffObject = new ArrayList<>();

            for (ParseObject stansstaff : ob) {
                // Locate images in flag column

                ParseFile image = (ParseFile) stansstaff.get("image");

                StaffListObject staff = new StaffListObject();
                staff.setName((String) stansstaff.get("name"));
                staff.setOther((String) stansstaff.get("other"));
                staff.setImage(image.getUrl());
                staffObject.add(staff);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new StaffListViewAdapter(getActivity(), staffObject);
        setListAdapter(adapter);


    }
}



