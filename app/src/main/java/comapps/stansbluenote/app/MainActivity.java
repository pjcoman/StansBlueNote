package comapps.stansbluenote.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.DeviceRegistration;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import comapps.stansbluenote.app.drinks.BeerRecyclerViewFragment;
import comapps.stansbluenote.app.user.DefaultCallback;
import comapps.stansbluenote.app.specials.SpecialsObject;
import comapps.stansbluenote.app.user.LoginActivity;
import im.delight.android.location.SimpleLocation;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final String MyPREFERENCES = "MyDrinkPrefs";
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    private SimpleLocation location;


    TextView timertv;
    TextView heretv;
    TextView visitstv;
    static CountDownTimer countDownTimer;
    String dayOfTheWeek = null;
    String user_Id = null;
    String device_Id = null;
    String favDrinksString = null;
    String leaderBoardString;
    GeoPoint geoPoint;
    GeoPoint geoPointStans = new GeoPoint(32.824054, -96.7697702);

    int i = 0;
    int visits;


    float distanceFromStansMeters;
    float distanceFromStansFeet;

    private FragmentManager mFragmentManager;

    private Location mLastLocation;
    private LocationRequest mLocationRequest;


    GoogleApiClient mGoogleApiClient;

    Criteria criteria = new Criteria();

    public static final String TAG = "MAINACTIVITY";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());


        setContentView(R.layout.main);

        sharedPrefs = getSharedPreferences(SetDrinkFavorites.MyPREFERENCES,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();


        buildGoogleApiClient();

        mFragmentManager = getSupportFragmentManager();

        timertv = (TextView) findViewById(R.id.timeleft);
        heretv = (TextView) findViewById(R.id.textViewHere);
        visitstv = (TextView) findViewById(R.id.textViewVisits);

        String appVersion = "v1";
        Backendless.initApp(this, "41852CE7-4EDB-5633-FFD5-0A860A6CDD00", "D18D98FD-C4C8-AD1B-FF0F-F2B33F4A8C00", appVersion);

        Backendless.Messaging.registerDevice("733022457738", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.i(TAG, "Device has been registered");

                Backendless.Messaging.getRegistrations(new AsyncCallback<DeviceRegistration>() {

                    @Override

                    public void handleResponse(final DeviceRegistration devReg) {

                        device_Id = devReg.getDeviceId();

                        Log.i(TAG, "messaging deviceID is " + device_Id);


                    }

                    @Override

                    public void handleFault(BackendlessFault arg0) {

                    }

                });


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, "Server reported an error " + fault.getMessage());

            }
        });

        user_Id = Backendless.UserService.loggedInUser();


        Log.i(TAG, "Current user is " + user_Id);


        Calendar c = Calendar.getInstance();
        final int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        final int hoursOfDay = c.get(Calendar.HOUR_OF_DAY);


        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addSortByOption("x ASC");
        queryOptions.setPageSize(10);
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

        //     String whereClause = "groupsort = " + String.valueOf(groupId);
        //     Log.i(TAG, whereClause);
        //     query.setWhereClause(whereClause);

        if (i != 1) {


            Backendless.Persistence.of(SpecialsObject.class).find(query, new AsyncCallback<BackendlessCollection<SpecialsObject>>() {
                @Override
                public void handleResponse(BackendlessCollection<SpecialsObject> specials) {

                    List<SpecialsObject> specialsList = new ArrayList<>();
                    specialsList.addAll(specials.getCurrentPage());

                    if (hoursOfDay < 2) {

                        Log.i(TAG, "specialsList(" + (dayOfWeek - 2) + ") " + specialsList.get(dayOfWeek - 2).getSpecial());

                    } else {

                        Log.i(TAG, "specialsList(" + (dayOfWeek - 1) + ") " + specialsList.get(dayOfWeek - 1).getSpecial());

                    }



        /*        DeliveryOptions deliveryOptions = new DeliveryOptions();
                deliveryOptions.addPushSinglecast(device_Id);
                PublishOptions publishOptions = new PublishOptions();
                publishOptions.putHeader("android-ticker-text", "push notification from Stan's!");
                publishOptions.putHeader("android-content-title", "Today's Specials");
                if ( hoursOfDay < 2 ) {

                    publishOptions.putHeader("android-content-text", specialsList.get(dayOfWeek - 2).getSpecial());
                } else {

                    publishOptions.putHeader("android-content-text", specialsList.get(dayOfWeek - 1).getSpecial());

                }



                Backendless.Messaging.publish("stansspecials", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
                    @Override
                    public void handleResponse(MessageStatus messageStatus) {




                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        String error = backendlessFault.getMessage();

                        Log.i(TAG, "messaging error is " + error);
                    }
                });
*/


                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                }
            });


        }


        startTimer();


    }

    //****************************************************************************************************************************************************************

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        String userToken = UserTokenStorageFactory.instance().getStorage().get();
        if (userToken != null && !userToken.equals("")) {
            inflater.inflate(R.menu.activity_main_actions, menu);

        } else {

            inflater.inflate(R.menu.activity_main_actions_login, menu);

        }


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
            case R.id.action_set_drink:
                SetDrinks();
                return true;
            case R.id.action_login:
                Login();
                return true;
            case R.id.action_logout:
                Logout();
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

    public void SetDrinks() {


        Intent intentSetDrinks = new Intent();
        intentSetDrinks.setClass(this, SetDrinkFavorites.class);
        startActivity(intentSetDrinks);
    }


    public void Login() {


        Intent intentLogin = new Intent();
        intentLogin.setClass(this, LoginActivity.class);
        startActivity(intentLogin);
    }

    private void Logout() {
        Backendless.UserService.logout(new DefaultCallback<Void>(this) {
            @Override
            public void handleResponse(Void response) {
                super.handleResponse(response);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if (fault.getCode().equals("3023")) // Unable to logout: not logged in (session expired, etc.)
                    handleResponse(null);
                else
                    super.handleFault(fault);
            }
        });

    }

    // Action Bar end

  /*  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                getLocation();
                break;
            default:
                break;
        }
    }

     void getLocation(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.



       criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

        Looper looper = null;

        locationManager.requestSingleUpdate(criteria, listener, looper);


            }

*/


    public void beerlist(View v) {

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new BeerRecyclerViewFragment()).commit();
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

    public void people(View v) {
        Intent intentPeople = new Intent();
        intentPeople.setClass(this, MainActivityViewPager.class);
        intentPeople.putExtra("activityId", "people");
        startActivity(intentPeople);
    }


    private void startTimer() {

        int noOfMinutesTillClose = 0;


        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat df1 = new SimpleDateFormat("HH");
        SimpleDateFormat df2 = new SimpleDateFormat("mm");
        SimpleDateFormat df3 = new SimpleDateFormat("ss");

        String formattedDateHH = df1.format(calendar.getTime());
        String formattedDatemm = df2.format(calendar.getTime());
        String formattedDatess = df3.format(calendar.getTime());

        int hours = Integer.parseInt(formattedDateHH);
        int minutes = Integer.parseInt(formattedDatemm);
        int seconds = Integer.parseInt(formattedDatess);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        Log.i(TAG, "day of week is " + dayOfTheWeek);


        int lastcallminutes = 59 - minutes;


        Log.i(TAG, "hours = " + hours);
        Log.i(TAG, "minutes = " + lastcallminutes);

        //  hours = 10;
        //  dayOfTheWeek = "Saturday";


        switch (hours) {
            case 0:
                noOfMinutesTillClose = (60 * 60 * 1000) + (lastcallminutes * 60 * 1000);
                break;
            case 1:
                noOfMinutesTillClose = lastcallminutes * 60 * 1000;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                timertv.setText("Go Home.");
                break;
            case 8:
            case 9:
            case 10:
                timertv.setText("Not open yet....");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:

                if (dayOfTheWeek.equalsIgnoreCase("Saturday") || dayOfTheWeek.equalsIgnoreCase("Sunday")) {


                    int hoursToSeconds = 60 * 60 * (25 - hours);
                    int minutesToSeconds = (lastcallminutes * 60) + (60 - seconds);
                    noOfMinutesTillClose = (hoursToSeconds + minutesToSeconds) * 1000;

                } else {

                    timertv.setText("Not open yet....");
                }

                break;
            default:
                int hoursToSeconds = 60 * 60 * (25 - hours);
                int minutesToSeconds = (lastcallminutes * 60) + (60 - seconds);
                noOfMinutesTillClose = (hoursToSeconds + minutesToSeconds) * 1000;
                break;
        }

        CountDownTimer countDownTimer = new CountDownTimer(noOfMinutesTillClose, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;

                if (millis < 60 * 60 * 1000) {

                    timertv.setTextColor(getResources().getColor(R.color.Red));
                    String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    timertv.setText(hms);//set text

                } else {

                    //Convert milliseconds into hour,minute and seconds
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    timertv.setText(hms);//set text
                }
            }


            public void onFinish() {
                timertv.setText("Go Home!"); //On finish change timer text
            }

        };

        countDownTimer.start();

    }

    public float getDistanceInMeters(GeoPoint p1, GeoPoint p2) {
        double lat1 = ((double) p1.getLatitudeE6()) / 1e6;
        double lng1 = ((double) p1.getLongitudeE6()) / 1e6;
        double lat2 = ((double) p2.getLatitudeE6()) / 1e6;
        double lng2 = ((double) p2.getLongitudeE6()) / 1e6;
        float[] dist = new float[1];
        Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
        return dist[0];
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            mLastLocation = location;
        //    Toast.makeText(this, location.getLongitude() + " , " + location.getLatitude() + " : " + location.getAccuracy(), Toast.LENGTH_LONG).show();

            geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

            distanceFromStansMeters = getDistanceInMeters(geoPoint, geoPointStans);
            double distanceFromStansFeet = distanceFromStansMeters * 3.2808399;

            Log.i(TAG, "distance from Stan's in feet" + distanceFromStansFeet);

            if (distanceFromStansFeet < 300) {
                heretv.setVisibility(View.VISIBLE);

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                Log.i(TAG, "formatteddate " + formattedDate);


                leaderBoardString = sharedPrefs.getString("leaderBoard", "0000000");
                Log.i(TAG, "leaderBoardString " + leaderBoardString);

               

                char[] leaderBoardChars = leaderBoardString.toCharArray();

                if ( sharedPrefs.getString("leaderBoardMonth", String.valueOf(Calendar.MONTH)).equals(String.valueOf(Calendar.MONTH))) {

                    leaderBoardChars[getCurrentDay() - 1] = '1';
                    leaderBoardString = String.valueOf(leaderBoardChars);
                    Log.i(TAG, "leaderBoardString " + leaderBoardString);


                } else {

                    char[] leaderBoardCharsNew = {0, 0, 0, 0, 0, 0, 0};
                    leaderBoardCharsNew[getCurrentDay() - 1] = '1';
                    leaderBoardString = String.valueOf(leaderBoardCharsNew);
                    Log.i(TAG, "leaderBoardString " + leaderBoardString);

                    editor.putString("leaderBoard", leaderBoardString);
                    editor.putString("leaderBoardMonth", String.valueOf(Calendar.MONTH));
                    editor.apply();


                }








            } else {
                heretv.setVisibility(View.INVISIBLE);

            }


            Backendless.Data.of(BackendlessUser.class).findById(user_Id, new AsyncCallback<BackendlessUser>() {

                public void handleResponse(BackendlessUser response) {

                    if (response != null) {

                        Backendless.UserService.setCurrentUser(response);


                        try {
                            response.setProperty("location", geoPoint);
                            response.setProperty("deviceID", device_Id);
                            response.setProperty("leaderboard", leaderBoardString);
                        //    response.setProperty("visits", sharedPrefs.getInt("visits", 0));
                        //    visitstv.setText(Integer.toString(sharedPrefs.getInt("visits", 0)));







                            response = Backendless.UserService.update(response);


                        } catch (BackendlessException e) {
                            e.printStackTrace();
                        }


                        Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                            public void handleResponse(BackendlessUser user) {

                                Log.i(TAG, user.toString() + "US update successful");


                            }

                            public void handleFault(BackendlessFault fault) {
                                Log.i(TAG, fault.toString() + "US update failed");
                            }
                        });

                    }

                }

                @Override

                public void handleFault(BackendlessFault fault) {

                }

            });

        }


    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        Log.i(TAG, "distance in meters from Stans is " + String.valueOf(getDistanceInMeters(geoPoint, geoPointStans)));

        Toast.makeText(getApplicationContext(), "distance from Stans is " +
                String.valueOf(getDistanceInMeters(geoPoint, geoPointStans)) + " meters", Toast.LENGTH_LONG).show();

        distanceFromStansMeters = getDistanceInMeters(geoPoint, geoPointStans);

        Log.i(TAG, "location is " + location.getLongitude() + " " + location.getLatitude());
        geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

        Backendless.Data.of(BackendlessUser.class).findById(user_Id, new AsyncCallback<BackendlessUser>() {

            public void handleResponse(BackendlessUser response) {

                if (response != null) {

                    Backendless.UserService.setCurrentUser(response);


                    try {
                        response.setProperty("location", geoPoint);
                        response.setProperty("deviceID", device_Id);
                        response = Backendless.UserService.update(response);

                        leaderBoardString = (String) response.getProperty("leaderboard");


                    } catch (BackendlessException e) {
                        e.printStackTrace();
                    }


                    Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser user) {

                            Log.i(TAG, user.toString() + "US update successful");


                        }

                        public void handleFault(BackendlessFault fault) {
                            Log.i(TAG, fault.toString() + "US update failed");
                        }
                    });

                }

            }

            @Override

            public void handleFault(BackendlessFault fault) {

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public int getCurrentDay(){

        String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return day;

    }






}