package comapps.stansbluenote.app.people;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoPoint;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.PublishOptions;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.backendless.services.messaging.MessageStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comapps.stansbluenote.app.LoadingCallback;
import comapps.stansbluenote.app.MapsActivity;
import comapps.stansbluenote.app.R;


/**
 * Created by me on 9/29/2015.
 */
public class PeopleListFragment extends ListFragment {


    private static final String TAG = "PEOPLELISTFRAGMENT";

    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";

    private PopupWindow createPopup;


    private List<BackendlessUser> peopleList = new ArrayList<>();
    private List<BackendlessUser> peopleListNot = new ArrayList<>();
    private ListView lv;
    private PeopleListAdapter adapter;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButton2;


    GeoPoint geoPointStans = new GeoPoint(32.824054, -96.7697702);
    Double stansLat = 32.824054;
    Double stansLong = -96.7697702;


    public PeopleListFragment() {

    }

    public static PeopleListFragment newInstance(int page) {
        PeopleListFragment fragment = new PeopleListFragment();


        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);

        fragment.setArguments(args);


        return fragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.peoplelistfragment, container, false);
        lv = (ListView) view.findViewById(R.id.list);




        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Marker Felt.ttf");

        radioButton.setTypeface(font);
        radioButton2.setTypeface(font);


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(getActivity(), radioButton.getText() + " clicked",
                        Toast.LENGTH_LONG).show();*/

            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Toast.makeText(getActivity(), radioButton2.getText() + " clicked",
                        Toast.LENGTH_LONG).show();
*/

            }
        });


        return view;

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //    int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);

        adapter = new PeopleListAdapter(getActivity(), peopleList);
        setListAdapter(adapter);


        Backendless.Persistence.of(BackendlessUser.class).find(new AsyncCallback<BackendlessCollection<BackendlessUser>>() {
            @Override
            public void handleResponse(BackendlessCollection<BackendlessUser> foundContacts) {
                // all Contact instances have been found
                addMoreItems(foundContacts);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                String queryString;


                if (checkedId == radioButton2.getId()) {

                    queryString = "distance( %f, %f, location.latitude, location.longitude ) < ft(300)";

                } else {

                    queryString = "distance( %f, %f, location.latitude, location.longitude ) < ft(5000)";


                }


                final String whereClause = String.format(queryString, stansLat, stansLong);


                BackendlessDataQuery dataQuery = new BackendlessDataQuery(whereClause);
                QueryOptions queryOptions = new QueryOptions();
                queryOptions.addRelated("location");
                dataQuery.setQueryOptions(queryOptions);


                Backendless.Persistence.of(BackendlessUser.class).find(dataQuery, new LoadingCallback<BackendlessCollection<BackendlessUser>>
                        (getActivity(), "Loading people...", true) {

                    @Override
                    public void handleResponse(BackendlessCollection<BackendlessUser> peopleBackendlessCollection) {
                        addMoreItems(peopleBackendlessCollection);
                        super.handleResponse(peopleBackendlessCollection);

                        BackendlessDataQuery dataQuery = new BackendlessDataQuery(whereClause);
                        QueryOptions queryOptions = new QueryOptions();
                        queryOptions.addRelated("location");
                        dataQuery.setQueryOptions(queryOptions);

                        Backendless.Persistence.of(BackendlessUser.class).find(dataQuery, new LoadingCallback<BackendlessCollection<BackendlessUser>>
                                (getActivity(), "Loading people...", true) {

                            @Override
                            public void handleResponse(BackendlessCollection<BackendlessUser> peopleBackendlessCollection) {
                                addMoreItems(peopleBackendlessCollection);
                                super.handleResponse(peopleBackendlessCollection);


                            }

                        });


                    }

                });


                adapter.notifyDataSetChanged();


            }
        });


    }


    private void addMoreItems(BackendlessCollection<BackendlessUser> nextPage) {
        peopleList.clear();
        peopleList.addAll(nextPage.getCurrentPage());

        for (BackendlessUser person : peopleList) {

            Log.i(TAG, "people here " + person.getProperty("name"));

        }


        adapter.notifyDataSetChanged();

    }


    private void initiateWindow(final String person, final String deviceID, final GeoPoint location) {
        View popUpLayout = null;
        try {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popUpLayout = inflater.inflate(R.layout.popupwindow, null);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            createPopup = new PopupWindow(popUpLayout, (int) (width / 1.1), height / 2, true);
            createPopup.showAtLocation(popUpLayout, Gravity.TOP, 0, 150);

            TextView popUpTitle = (TextView) popUpLayout.findViewById(R.id.textViewTitleForEditText);
            final EditText notificationText = (EditText) popUpLayout.findViewById(R.id.et);
            Button b1 = (Button) popUpLayout.findViewById(R.id.sendButton);
            Button b2 = (Button) popUpLayout.findViewById(R.id.onMap);
            popUpTitle.setText("send notification to " + person);


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //    Toast.makeText(getActivity(), "Send button was clicked", Toast.LENGTH_SHORT).show();

                    DeliveryOptions deliveryOptions = new DeliveryOptions();
                    deliveryOptions.addPushSinglecast(deviceID);
                    PublishOptions publishOptions = new PublishOptions();
                    publishOptions.putHeader("android-ticker-text", "push notification from Stan's!");
                    publishOptions.putHeader("android-content-title", "From Stan's:");
                    publishOptions.putHeader("android-content-text", notificationText.getText().toString());


                    Backendless.Messaging.publish("stansnotify", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
                        @Override
                        public void handleResponse(MessageStatus messageStatus) {


                            createPopup.dismiss();

                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            String error = backendlessFault.getMessage();

                            Log.i(TAG, "messaging error is " + error);
                        }
                    });


                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i(TAG, "map button clicked");
                    Log.i(TAG, "location is " + location);

                    double latToPass = ((double) location.getLatitudeE6()) / 1e6;
                    double lngToPass = ((double) location.getLongitudeE6()) / 1e6;


                    Intent intentMenu = new Intent();
                    intentMenu.setClass(getContext(), MapsActivity.class);
                    intentMenu.putExtra("lat", latToPass);
                    intentMenu.putExtra("lng", lngToPass);
                    intentMenu.putExtra("name", person);
                    startActivity(intentMenu);


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        popUpLayout.setOnTouchListener(new View.OnTouchListener() {
            int orgX, orgY;
            int offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        orgX = (int) event.getX();
                        orgY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        offsetX = (int) event.getRawX() - orgX;
                        offsetY = (int) event.getRawY() - orgY;
                        createPopup.update(offsetX, offsetY, -1, -1, true);
                        break;
                }
                return true;
            }
        });


    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLUE));
        getListView().setDividerHeight(2);

    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        //      Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();

        Date dateUpdated = (Date) peopleList.get(pos).getProperty("updated");
        Date currentDateMinus2Hours = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2);

        GeoPoint geoPoint = (GeoPoint) peopleList.get(pos).getProperty("location");

        if (!dateUpdated.before(currentDateMinus2Hours) && geoPoint != null ) {

            initiateWindow((String) peopleList.get(pos).getProperty("name"), (String) peopleList.get(pos).getProperty("deviceID"),
                    geoPoint);
        }
    }


}






















