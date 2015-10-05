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
public class CocktailListViewFragment extends ListFragment {


    private List<CocktailListObject> cocktailObject;
    CocktailListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.cocktaillistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "stanscocktails").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("name");

            ob = query.find();

            ParseObject.pinAllInBackground(ob);

            cocktailObject = new ArrayList<>();

            for (ParseObject stanscocktails : ob) {
                // Locate images in flag column

                ParseFile image = (ParseFile) stanscocktails.get("image");

                CocktailListObject cocktail = new CocktailListObject();
                cocktail.setCocktailName((String) stanscocktails.get("name"));
                cocktail.setCocktailIngredients((String) stanscocktails.get("ingredients"));
                cocktail.setCocktailPrice((String) stanscocktails.get("price"));
                cocktail.setCocktailImage(image.getUrl());
                cocktailObject.add(cocktail);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new CocktailListViewAdapter(getActivity(), cocktailObject);
        setListAdapter(adapter);


    }
}















