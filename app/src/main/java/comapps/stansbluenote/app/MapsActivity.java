package comapps.stansbluenote.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static comapps.stansbluenote.app.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MAPSACTIVITY";

    private GoogleMap mMap;
    private Double personLat;
    private Double personLng;
    private String personName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            personLat = getIntent().getExtras().getDouble("lat");
            personLng = getIntent().getExtras().getDouble("lng");
            personName = getIntent().getExtras().getString("name");
        }

        Log.i(TAG, "person latlng is " + personLat + ", " + personLng);

        // Add a marker in Sydney and move the camera
        LatLng locationOfPerson = new LatLng(personLat, personLng);
        mMap.addMarker(new MarkerOptions().position(locationOfPerson).title(personName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationOfPerson));
     //   mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(STANS, 0));
        mMap.setMinZoomPreference(19.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
