package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
//
//public class OptionTwo extends FragmentActivity implements OnMapReadyCallback {
//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initializeMap();
//        initializePlaces();
//    }
//
//    private void initializePlaces() {
//        if (!Places.isInitialized()){
//            Places.initialize(this,getString(R.string.google_maps_key));
//        }
//        AutocompleteSupportFragment autocompleteFragment= (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG));
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                try {
//                    MarkerOptions marker = new MarkerOptions();
//                    marker.position(place.getLatLng()).title(place.getName());
//                    mMap.addMarker(marker);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
//                }catch (Exception e){
//                    Log.d("Ahmed",e.getMessage());
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//                Log.d("Mama",""+status.getStatusMessage());
//            }
//        });
//
//    }
//
//    private void initializeMap() {
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        LatLng sydney = new LatLng(30, 33);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
//    }
//}




//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        initializeMap();
//        initializePlaces();
//    }
//
//    private void initializePlaces() {
//        if (!Places.isInitialized()){
//            Places.initialize(this,getString(R.string.google_maps_key));
//        }
//        AutocompleteSupportFragment autocompleteFragment= (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG));
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                try {
//                    MarkerOptions marker = new MarkerOptions();
//                    marker.position(place.getLatLng()).title(place.getName());
//                    mMap.addMarker(marker);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
//                }catch (Exception e){
//                    Log.d("Ahmed",e.getMessage());
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//                Log.d("Mama",""+status.getStatusMessage());
//            }
//        });
//
//    }
//
//    private void initializeMap() {
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        LatLng sydney = new LatLng(30, 33);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Your Location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
//    }
//}


