package comapps.stansbluenote.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StaffParseListView extends Activity {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    StaffParseListViewAdapter adapter;
    private List<StaffListObject> stafflist = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        // Get the view from listview_main.xml
        setContentView(R.layout.stafflist);
        // Execute RemoteDataTask AsyncTask



        // Action Bar

        ActionBar bar = getActionBar();
        if (bar != null) {
            //       bar.setBackgroundDrawable(new ColorDrawable(R.color.Black));
            bar.setBackgroundDrawable(getResources().getDrawable(R.color.Black));
        }

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTextSize(18);
        actionbartitle.setTextColor(Color.rgb(94, 139, 246));

        // Action Bar end




















            new RemoteDataTask().execute();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);




    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_map:
                Map();
                return true;
            case R.id.action_gmail:
                Gmail();
                return true;
            case R.id.action_call:
                CallStans();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Map() {

        // TODO Auto-generated method stub

        Intent intent5 = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/Stan's+Blue+Note/@32.824255,-96.769911,17z/data=!3m1!4b1!4m2!3m1!1s0x864e9f40bd0b9551:0x3c0fa6a555470cde?hl=en"));
        intent5.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        startActivity(intent5);

    }


    private void Gmail() {
        // TODO Auto-generated method stub

        // The following code is the implementation of Email client
        Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = {"stansbluenote@gmail.com"};

        intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        intent3.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
        intent3.putExtra(android.content.Intent.EXTRA_TEXT, "text");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

    }

    private void CallStans() {

        // TODO Auto-generated method stub

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:2148271977"));
        startActivity(callIntent);


    }


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(StaffParseListView.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Stan's Staff");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

            // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        }

        @Override
        protected Void doInBackground(Void... params) {

            stafflist = new ArrayList<StaffListObject>();

            try {
                // Locate the class table named "stansdata" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "stansstaff").fromLocalDatastore();
                // Locate the column named "name" in Parse.com and order list
                // by ascending
                query.orderByAscending("name");
                ob = query.find();

                ParseObject.pinAllInBackground(ob);

                for (ParseObject stansdata : ob) {
                    // Locate images in flag column

                    ParseFile image = (ParseFile) stansdata.get("image");

                    StaffListObject staff = new StaffListObject();
                    staff.setName((String) stansdata.get("name"));
                    staff.setOther((String) stansdata.get("other"));

                    staff.setImage(image.getUrl());
                    stafflist.add(staff);
                }


            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.stafflist);
            // Pass the results into ParseListViewAdapter.java
            adapter = new StaffParseListViewAdapter(StaffParseListView.this,
                    stafflist);


            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items



        }
    }



    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

}