package comapps.stansbluenote.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by me on 1/16/2017.
 */

public class SetDrinkFavorites extends AppCompatActivity {

    private static final String TAG = "SETDRINKFAVORITES";

    public static final String MyPREFERENCES = "MyDrinkPrefs" ;
    SharedPreferences sharedPrefs;


    private String favs;
    private String[] favText = new String[]{"1","2","3"};
    private String[] favItems;
    private Button setButton;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());



        setContentView(R.layout.drinkfavorites_main);

        sharedPrefs = getSharedPreferences(SetDrinkFavorites.MyPREFERENCES,
                Context.MODE_PRIVATE);


        setButton = (Button) findViewById(R.id.buttonSet);




            favItems = sharedPrefs.getString("favs", "item,item,item").split(",", -1);





            for (String s: favItems) {
                //Do your stuff here

                s = s.trim();
                Log.i(TAG, "favs at start " + s + " pos " + i);
                favItems[i] = favItems[i].trim();

                i++;
            }



        MyListAdapter myListAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(myListAdapter);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;

                for (String s: favItems) {
                    //Do your stuff here

                    if ( s == null || s == "" ) {

                        s = "";

                    }

                    s = s.trim();
                    Log.i(TAG, "favs at finish " + s + " pos " + i);
                    favItems[i] = s;

                    i++;
                }




                favs = favItems[0] + "," + favItems[1] + "," + favItems[2] ;



                Log.i(TAG, "favs are " + favs);


                sharedPrefs.edit().putString("favs", favs).apply();


                Backendless.Data.of(BackendlessUser.class).findById( Backendless.UserService.loggedInUser(), new AsyncCallback<BackendlessUser>() {

                    public void handleResponse(BackendlessUser response) {

                        if (response != null) {

                            Backendless.UserService.setCurrentUser(response);


                            try {

                                response.setProperty( "favorites", favs);
                                response = Backendless.UserService.update(response);
                            } catch (BackendlessException e) {
                                e.printStackTrace();
                            }




                            Backendless.UserService.update( response, new AsyncCallback<BackendlessUser>()
                            {
                                public void handleResponse( BackendlessUser user ) {

                                    Log.i(TAG, user.toString() + "user update successful");

                                    finish();

                                }

                                public void handleFault( BackendlessFault fault )
                                {
                                    Log.i(TAG, fault.toString() + "user update failed");
                                }
                            });

                        }

                    }

                    @Override

                    public void handleFault(BackendlessFault fault) {

                    }

                });




            }
        });
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if(favText != null && favText.length != 0){
                return favText.length;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return favText[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            //ViewHolder holder = null;
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                LayoutInflater inflater = SetDrinkFavorites.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.drinkfavorites_list, null);
                holder.editText1 = (EditText) convertView.findViewById(R.id.editText1);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.ref = position;


            holder.editText1.setText(favItems[position]);
            holder.editText1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub



                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    favItems[holder.ref] = arg0.toString();




                }
            });





        return convertView;

        }

        private class ViewHolder {
            EditText editText1;
            int ref;
        }


    }

    public char LastChar(String a){
        return a.charAt(a.length() - 1);
    }

    public char FirstChar(String a){
        return a.charAt(0);
    }


    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }


}