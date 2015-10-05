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
public class MenuListViewFragment extends ListFragment {


    private List<MenuListObject> menuObject;
    MenuListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.menulistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "stansmenu");
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("x");

            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            menuObject = new ArrayList<>();

            for (ParseObject menu : ob) {
                // Locate images in flag column


                MenuListObject menuItem = new MenuListObject();
                menuItem.setItem((String) menu.get("item"));
                menuItem.setPrice((String) menu.get("price"));
                menuObject.add(menuItem);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new MenuListViewAdapter(getActivity(), menuObject);
        setListAdapter(adapter);


    }
}



