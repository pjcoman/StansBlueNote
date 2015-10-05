package comapps.stansbluenote.app;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static comapps.stansbluenote.app.R.drawable.arrowdown;
import static comapps.stansbluenote.app.R.drawable.arrowup;

public class BeerParseListView extends Activity {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    BeerParseListViewAdapter adapter;
    private List<BeerListObject> beerlist = null;
    String sortName = "ascending";
    String sortWhereFrom = "ascending";
    String sortType = "ascending";
    String sortAbv = "ascending";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        // Get the view from listview_main.xml


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

        final TextView byname = (TextView) findViewById(R.id.textView);
        final TextView bycountry = (TextView) findViewById(R.id.textView2);
        final TextView bytype = (TextView) findViewById(R.id.textView3);
        final TextView byabv = (TextView) findViewById(R.id.textView4);


        byname.setTextColor(Color.YELLOW);


        final ImageView arrow = (ImageView) findViewById(R.id.imageView);
        final ImageView arrow2 = (ImageView) findViewById(R.id.imageView2);
        final ImageView arrow3 = (ImageView) findViewById(R.id.imageView3);
        final ImageView arrow4 = (ImageView) findViewById(R.id.imageView4);

        arrow.setVisibility(View.VISIBLE);
        arrow.setImageResource(arrowdown);


        arrow2.setVisibility(View.INVISIBLE);
        arrow3.setVisibility(View.INVISIBLE);
        arrow4.setVisibility(View.INVISIBLE);

        byname.setEnabled(true);
        bycountry.setEnabled(true);
        bytype.setEnabled(true);
        byabv.setEnabled(true);

        byname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (sortName == "ascending") {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b2.getBeerName().compareToIgnoreCase(b1.getBeerName());
                        }
                    });

                    sortName = "descending";

                    arrow.setImageResource(arrowup);


                } else {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b1.getBeerName().compareToIgnoreCase(b2.getBeerName());
                        }
                    });

                    sortName = "ascending";

                    arrow.setImageResource(arrowdown);


                }

                arrow.setVisibility(View.VISIBLE);
                arrow2.setVisibility(View.INVISIBLE);
                arrow3.setVisibility(View.INVISIBLE);
                arrow4.setVisibility(View.INVISIBLE);

                byname.setTextColor(Color.YELLOW);
                bycountry.setTextColor(0xff5e8bf6);
                bytype.setTextColor(0xff5e8bf6);
                byabv.setTextColor(0xff5e8bf6);


                adapter.notifyDataSetChanged();

            }
        });

        bycountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //     Intent intentc = new Intent(v.getContext(), BeerParseListViewByCountry_Ascending.class);
                //     startActivity(intentc);
                //     finish();

                if (sortWhereFrom == "ascending") {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b1.getBeerWhereFrom().compareToIgnoreCase(b2.getBeerWhereFrom());
                        }
                    });

                    sortWhereFrom = "descending";

                    arrow2.setImageResource(arrowdown);


                } else {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b2.getBeerWhereFrom().compareToIgnoreCase(b1.getBeerWhereFrom());
                        }
                    });

                    sortWhereFrom = "ascending";

                    arrow2.setImageResource(arrowup);


                }


                arrow.setVisibility(View.INVISIBLE);
                arrow2.setVisibility(View.VISIBLE);
                arrow3.setVisibility(View.INVISIBLE);
                arrow4.setVisibility(View.INVISIBLE);


                byname.setTextColor(0xff5e8bf6);
                bycountry.setTextColor(Color.YELLOW);
                bytype.setTextColor(0xff5e8bf6);
                byabv.setTextColor(0xff5e8bf6);


                adapter.notifyDataSetChanged();

            }


        });


        bytype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sortType == "ascending") {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b1.getBeerType().compareToIgnoreCase(b2.getBeerType());
                        }
                    });

                    sortType = "descending";

                    arrow3.setImageResource(arrowdown);


                } else {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b2.getBeerType().compareToIgnoreCase(b1.getBeerType());
                        }
                    });

                    sortType = "ascending";

                    arrow3.setImageResource(arrowup);


                }


                arrow.setVisibility(View.INVISIBLE);
                arrow2.setVisibility(View.INVISIBLE);
                arrow3.setVisibility(View.VISIBLE);
                arrow4.setVisibility(View.INVISIBLE);


                byname.setTextColor(0xff5e8bf6);
                bycountry.setTextColor(0xff5e8bf6);
                bytype.setTextColor(Color.YELLOW);
                byabv.setTextColor(0xff5e8bf6);

                adapter.notifyDataSetChanged();

            }
        });


        byabv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sortAbv == "ascending") {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b1.getBeerAbv().compareToIgnoreCase(b2.getBeerAbv());
                        }
                    });

                    sortAbv = "descending";

                    arrow4.setImageResource(arrowdown);


                } else {

                    Collections.sort(beerlist, new Comparator<BeerListObject>() {
                        public int compare(BeerListObject b1, BeerListObject b2) {
                            return b2.getBeerAbv().compareToIgnoreCase(b1.getBeerAbv());
                        }
                    });

                    sortAbv = "ascending";

                    arrow4.setImageResource(arrowup);


                }


                arrow.setVisibility(View.INVISIBLE);
                arrow2.setVisibility(View.INVISIBLE);
                arrow3.setVisibility(View.INVISIBLE);
                arrow4.setVisibility(View.VISIBLE);


                byname.setTextColor(0xff5e8bf6);
                bycountry.setTextColor(0xff5e8bf6);
                bytype.setTextColor(0xff5e8bf6);
                byabv.setTextColor(Color.YELLOW);

                adapter.notifyDataSetChanged();

            }
        });


        SharedPreferences prefs1 = this.getSharedPreferences(
                "com.stansbluenote.app", Context.MODE_PRIVATE);
        boolean hasVisited1 = prefs1.getBoolean("HAS_VISISTED_BEFORE1", false);
        if (!hasVisited1) {


            prefs1.edit().putBoolean("HAS_VISISTED_BEFORE1", true).commit();

            new RemoteDataTask().execute();

        }

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
                Uri.parse("https://www.google.com/maps/place/Stan's+Blue+Note/" +
                        "@32.824255,-96.769911,17z/data=!3m1!4b1!4m2!3m1!1s0x864e9" +
                        "f40bd0b9551:0x3c0fa6a555470cde?hl=en"));
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


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            beerlist = new ArrayList<BeerListObject>();

            try {
                // Locate the class table named "stansbeers" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "stansbeers").fromLocalDatastore();
                // Locate the column named "name" in Parse.com and order list
                // by ascending


                query.orderByAscending("name");

                ob = query.find();

                ParseObject.pinAllInBackground(ob);

                for (ParseObject stansbeers : ob) {
                    // Locate images in flag column

                    ParseFile image = (ParseFile) stansbeers.get("image");

                    BeerListObject beer = new BeerListObject();
                    beer.setBeerName((String) stansbeers.get("name"));
                    beer.setBeerWhereFrom((String) stansbeers.get("wherefrom"));
                    beer.setBeerAbv((String) stansbeers.get("abv"));
                    beer.setBeerType((String) stansbeers.get("type"));
                    beer.setBeerImage(image.getUrl());
                    beerlist.add(beer);
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
            listview = (ListView) findViewById(R.id.beerlist);
            // Pass the results into ParseListViewAdapter.java
            adapter = new BeerParseListViewAdapter(BeerParseListView.this, beerlist);
            listview.setAdapter(adapter);
            // Close the progressdialog

        }
    }

        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

        }


    }

