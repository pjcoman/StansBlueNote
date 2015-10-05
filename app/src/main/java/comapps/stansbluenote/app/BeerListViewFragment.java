package comapps.stansbluenote.app;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 9/29/2015.
 */
public class BeerListViewFragment extends ListFragment {

    int x = 0;
    private List<BeerListObject> beerObject;
    BeerListViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.beerlistfragment, null, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "stansbeers").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("name");

            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            beerObject = new ArrayList<>();

            for (ParseObject stansbeers : ob) {
                // Locate images in flag column

                ParseFile image = (ParseFile) stansbeers.get("image");

                BeerListObject beer = new BeerListObject();
                beer.setBeerName((String) stansbeers.get("name"));
                beer.setBeerWhereFrom((String) stansbeers.get("wherefrom"));
                beer.setBeerAbv((String) stansbeers.get("abv"));
                beer.setBeerType((String) stansbeers.get("type"));
                beer.setBeerImage(image.getUrl());
                beerObject.add(beer);

            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new BeerListViewAdapter(getActivity(), beerObject);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        x = x + 1;

        if (x == 8) {

            x = 0;

        }

        String[] toastSort = new String[8];
        toastSort[0] = "Sorted by name Ascending";
        toastSort[1] = "by name descending";
        toastSort[2] = "by type ascending";
        toastSort[3] = "by type descending";
        toastSort[4] = "by A.B.V ascending";
        toastSort[5] = "by A.B.V descending";
        toastSort[6] = "by where from ascending";
        toastSort[7] = "by where from descending";

        String tm = toastSort[x];

        Toast sort = Toast.makeText(getActivity(), tm, Toast.LENGTH_LONG);
        sort.setGravity(Gravity.CENTER, 0, 0);
        sort.show();

        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "stansbeers").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending

            switch (x) {
                case 0:
                    query.orderByAscending("name");
                    break;
                case 1:
                    query.orderByDescending("name");
                    break;
                case 2:
                    query.orderByAscending("type");
                    break;
                case 3:
                    query.orderByDescending("type");
                    break;
                case 4:
                    query.orderByAscending("abv");
                    break;
                case 5:
                    query.orderByDescending("abv");
                    break;
                case 6:
                    query.orderByAscending("wherefrom");
                    break;
                case 7:
                    query.orderByDescending("wherefrom");
                    break;
            }


            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            beerObject = new ArrayList<>();

            for (ParseObject stansbeers : ob) {
                // Locate images in flag column

                ParseFile image = (ParseFile) stansbeers.get("image");

                BeerListObject beer = new BeerListObject();
                beer.setBeerName((String) stansbeers.get("name"));
                beer.setBeerWhereFrom((String) stansbeers.get("wherefrom"));
                beer.setBeerAbv((String) stansbeers.get("abv"));
                beer.setBeerType((String) stansbeers.get("type"));
                beer.setBeerImage(image.getUrl());
                beerObject.add(beer);

            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new BeerListViewAdapter(getActivity(), beerObject);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


}






















