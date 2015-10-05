package comapps.stansbluenote.app;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 9/29/2015.
 */
public class SpecialsListViewFragment extends ListFragment {


    private List<SpecialsListObject> specialsObject;
    SpecialsListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.specialslistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "specials").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("x");

            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            specialsObject = new ArrayList<>();

            for (ParseObject stansspecials : ob) {
                // Locate images in flag column


                SpecialsListObject special = new SpecialsListObject();
                special.setDay((String) stansspecials.get("dayofweek"));
                special.setSpecial((String) stansspecials.get("data"));
                specialsObject.add(special);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new SpecialsListViewAdapter(getActivity(), specialsObject);
        setListAdapter(adapter);


    }
}



