package comapps.stansbluenote.app;

import android.app.Application;
import android.util.Log;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(getApplicationContext());

        // Add your initialization code here
        Parse.initialize(this, "ou48PJFcg03zAiknM61FqTj7x9cTRIWmBtqKTIqU", "nIRAnkLq87H9hm5Iamc3kxmOWACf4O7P3YvuewKi");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

     //   PushService.setDefaultPushCallback(this, MainActivity.class);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseQuery<ParseObject> queryStaff = new ParseQuery<ParseObject>(
                "stansstaff");

        queryStaff.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> staff, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("stansstaff", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("stansstaff", staff );
                    }
                });
            }
        });

        ParseQuery<ParseObject> queryBeers = new ParseQuery<ParseObject>(
                "stansbeers");

        queryBeers.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> beers, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("stansbeers", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("stansbeers", beers);
                    }
                });
            }
        });

        ParseQuery<ParseObject> queryMenu = new ParseQuery<ParseObject>(
                "stansmenu");
        // Locate the column named "day" in Parse.com and order list
        // by ascending

        queryMenu.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> menu, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("stansmenu", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("stansmenu", menu);
                    }
                });
            }
        });

        ParseQuery<ParseObject> queryCocktails = new ParseQuery<ParseObject>(
                "stanscocktails");

        queryCocktails.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> cocktails, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("stanscocktails", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("stanscocktails", cocktails);
                    }
                });
            }
        });

        ParseQuery<ParseObject> querySpecials = new ParseQuery<ParseObject>(
                "specials");

        querySpecials.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> specials, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("specials", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("specials", specials);
                    }
                });
            }
        });





    }

}