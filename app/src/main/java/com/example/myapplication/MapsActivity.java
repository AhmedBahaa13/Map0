package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private static String TAG = "info";
    private Location userLocation;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient locationProvider;
    FloatingActionButton button;
    public static int REQUEST_CODE_LOCATION = 1;
    Place placeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        button = findViewById(R.id.loc_btn);
        // initialize Maps
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        initializeMaps();
        // initialize places Method
        // Create AutoComplete Search For Find Places And Return Place Has Selected
        initializePlaces();

        //get Location from Location Services and Async Map from fetchLastKnownLocation Method
        locationProvider = LocationServices.getFusedLocationProviderClient(this);
        fetchLastKnownLocation();
       //button to Get Last Known Location
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLastKnownLocation();
            }
        });
    }

    private void initializeMaps() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    private Place initializePlaces() {
        if (!Places.isInitialized())
        Places.initialize(this, getString(R.string.google_maps_key));

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
       autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);
       autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(30,33),
                new LatLng(30,32)));
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                placeSelected = place;
                LatLng placeLatLng = place.getLatLng();
                if (place != null)
                    mMap.addMarker(new MarkerOptions().position(placeLatLng).title(""+placeSelected.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLatLng, 15));
                Toast.makeText(MapsActivity.this, "" +placeSelected.getName(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(@NonNull Status status) {
                Log.d("Mapa",status.getStatusMessage());
            }
        });

        return placeSelected;

    }

    private void fetchLastKnownLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MapsActivity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            return;
        }
        Task<Location> task = locationProvider.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    userLocation = location;
                    mapFragment.getMapAsync(MapsActivity.this);
                    // get Location Name From LatLng
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String cityName = addresses.get(0).getAddressLine(0);
                    Toast.makeText(MapsActivity.this, ""+cityName, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MapsActivity.this, "Please Make Gps On", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_CODE_LOCATION == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location permissions granted, starting location", Toast.LENGTH_SHORT).show();
                fetchLastKnownLocation();
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setIndoorEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng sydney = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
    }

}