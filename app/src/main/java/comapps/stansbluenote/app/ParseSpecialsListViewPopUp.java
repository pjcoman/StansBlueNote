package comapps.stansbluenote.app;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ParseSpecialsListViewPopUp extends Activity {
 // Declare Variables
 ListView listview;
 List<ParseObject> ob;
 ProgressDialog mProgressDialog;
 ParseSpecialsListViewAdapter adapter;
 private List<SpecialsListObject> specialslist = null;


 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 // Get the view from listview_main.xml

  CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
          .setDefaultFontPath("fonts/Marker Felt.ttf")
          .setFontAttrId(R.attr.fontPath)
          .build());



 setContentView(R.layout.specialslistpopup);


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

 // RemoteDataTask AsyncTask
 private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
 @Override
 protected void onPreExecute() {
 super.onPreExecute();
 // Create a progressdialog
 mProgressDialog = new ProgressDialog(ParseSpecialsListViewPopUp.this);
 // Set progressdialog title
 mProgressDialog.setTitle("Stan's Specials");
 // Set progressdialog message
 mProgressDialog.setMessage("Loading...");
 mProgressDialog.setIndeterminate(false);
 // Show progressdialog
 mProgressDialog.show();
 }



 @Override
 protected Void doInBackground(Void... params) {

 specialslist = new ArrayList<SpecialsListObject>();

  try {
   // Locate the class table named "stansdata" in Parse.com
   ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
           "specials").fromLocalDatastore();
   // Locate the column named "day" in Parse.com and order list
   // by ascending

   Calendar c = Calendar.getInstance();
   int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


   query.whereContains("x", String.valueOf(dayOfWeek));

   ob = query.find();



   for (ParseObject specials : ob) {
    // Locate images in flag column


    SpecialsListObject dayandspecial = new SpecialsListObject();
    dayandspecial.setDay((String) specials.get("dayofweek"));
    dayandspecial.setSpecial((String) specials.get("data"));

    specialslist.add(dayandspecial);
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
 listview = (ListView) findViewById(R.id.specialslist);
 // Pass the results into ParseListViewAdapter.java
 adapter = new ParseSpecialsListViewAdapter(ParseSpecialsListViewPopUp.this,
 specialslist);


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





 public void onExitPressed(View v) {
   // TODO Auto-generated method stub
   finish();

  }
 }
