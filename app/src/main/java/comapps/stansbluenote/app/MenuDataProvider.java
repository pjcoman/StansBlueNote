package comapps.stansbluenote.app;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by me on 6/2/2015.
 */
public class MenuDataProvider  {





    public static HashMap<String, List<String>> getInfoLocal() {


        List<MenuListObject> menulist = null;
        menulist = new ArrayList<MenuListObject>();

        HashMap<String, List<String>> MenuDetails = new HashMap<String, List<String>>();
        List<String> Appetizers = new ArrayList<String>();
        List<String> Meals = new ArrayList<String>();
        List<String> Others = new ArrayList<String>();

        try {

            List<ParseObject> ob;
            // Locate the class table named "stansmenu" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "stansmenu").fromLocalDatastore();
            // Locate the column named "day" in Parse.com and order list
            // by ascending
            query.orderByAscending("x");
            ob = query.find();




            for (ParseObject menuitem : ob) {
                // Locate images in flag column


                MenuListObject menuobject = new MenuListObject();
                menuobject.setItem((String) menuitem.get("item"));
                menuobject.setPrice((String) menuitem.get("price"));
                menuobject.setType((String) menuitem.get("type"));

                menulist.add(menuobject);
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        for (MenuListObject menuobject : menulist) {
            if (menuobject.getType().equals("appetizer")) {
                String itemandprice = menuobject.getItem() + "  " + menuobject.getPrice();
                Appetizers.add(itemandprice);
            }

            if (menuobject.getType().equals("meal")) {
                String itemandprice = menuobject.getItem() + "  " + menuobject.getPrice();
                Meals.add(itemandprice);
            }

            if (menuobject.getType().equals("other")) {
                String itemandprice = menuobject.getItem() + "  " + menuobject.getPrice();
                Others.add(itemandprice);
            }
        }

        MenuDetails.put("Appetizers", Appetizers);
        MenuDetails.put("Meals", Meals);
        MenuDetails.put("Others", Others);

        return MenuDetails;


    }



}


