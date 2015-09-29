package comapps.stansbluenote.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by me on 6/2/2015.
 */
public class ParseMenuListViewExpandable extends Activity {
    // Declare Variables




    HashMap<String, List<String>> Menu_Category;
    List<String> Menu_list;
    ExpandableListView Exp_list;
    ParseMenuListViewExpandableAdapter adapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.menulist_expandable);











            Menu_Category = MenuDataProvider.getInfoLocal();




        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);

        Menu_list = new ArrayList<String>(Menu_Category.keySet());
        adapter = new ParseMenuListViewExpandableAdapter(this, Menu_Category, Menu_list);
        Exp_list.setAdapter(adapter);












        ActionBar bar = getActionBar();
        if (bar != null) {
            //       bar.setBackgroundDrawable(new ColorDrawable(R.color.Black));
            bar.setBackgroundDrawable(getResources().getDrawable(R.color.Black));
        }





        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/chalkdust.ttf");

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);



        actionbartitle.setTextSize(18);
        actionbartitle.setTextColor(Color.rgb(94, 139, 246));



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

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }








}
