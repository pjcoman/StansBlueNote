package comapps.stansbluenote.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());


        setContentView(R.layout.main);



    /*    if (bar != null) {
     //       bar.setBackgroundDrawable(new ColorDrawable(R.color.Black));
            bar.setIcon(R.drawable.ic_launcher);
        }


        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);



        actionbartitle.setTextSize(18);
        actionbartitle.setTextColor(Color.rgb(94, 139, 246));

          ActionBar actionbar = getActionBar();

           actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#615F5F"))); */



        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat df1 = new SimpleDateFormat("HH");
        SimpleDateFormat df2 = new SimpleDateFormat("mm");

        String formattedDateHH = df1.format(calendar.getTime());
        String formattedDatemm = df2.format(calendar.getTime());

        int hours = Integer.parseInt(formattedDateHH);
        int minutes = Integer.parseInt(formattedDatemm);

        int lastcallhours = 25 - hours;
        int lastcallminutes = 60 - minutes;

        if (lastcallhours > 23) {
            lastcallhours = 1 - hours;
        }


        if ((lastcallhours >= 15) && (lastcallhours <= 23)) {

            TextView timertv = (TextView) findViewById(R.id.timeleft);
            timertv.setText("Go Home.");

        } else {
            if ((lastcallhours == 0)) {

                TextView timertv = (TextView) findViewById(R.id.timeleft);

                timertv.setText(lastcallminutes + " minute(s) till last call");
                timertv.setTextColor(getResources().getColor(R.color.Red));


            } else if ((lastcallhours == 1)) {

                TextView timertv = (TextView) findViewById(R.id.timeleft);

                timertv.setText(lastcallhours + " hour and " + lastcallminutes + " minute(s) till last call");


            } else {

                TextView timertv = (TextView) findViewById(R.id.timeleft);

                timertv.setText(lastcallhours + " hours and " + lastcallminutes + " minute(s) till last call");


            }
        }


        //     if (savedInstanceState == null) {

        //          Intent intentspecials = new Intent(this, ParseSpecialsListViewPopUp.class);
        //          startActivity(intentspecials);


        //      }



    }

    // Action Bar

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

    // Action Bar end


    public void beerlist(View v) {

        Intent intentBeer = new Intent();
        intentBeer.setClass(this, MainActivityViewPager.class);
        intentBeer.putExtra("activityId", "beer");
        startActivity(intentBeer);
    }


    public void specials(View v) {
        Intent intentSpecials = new Intent();
        intentSpecials.setClass(this, MainActivityViewPager.class);
        intentSpecials.putExtra("activityId", "specials");
        startActivity(intentSpecials);
    }

    public void stansmenu(View v) {
        Intent intentMenu = new Intent();
        intentMenu.setClass(this, MainActivityViewPager.class);
        intentMenu.putExtra("activityId", "food");
        startActivity(intentMenu);
    }

    public void stanscocktails(View v) {
        Intent intentCocktails = new Intent();
        intentCocktails.setClass(this, MainActivityViewPager.class);
        intentCocktails.putExtra("activityId", "cocktails");
        startActivity(intentCocktails);
    }

    public void stansstaff(View v) {
        Intent intentStaff = new Intent();
        intentStaff.setClass(this, MainActivityViewPager.class);
        intentStaff.putExtra("activityId", "staff");
        startActivity(intentStaff);
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }

}
