package com.example.googlemapsexample;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapsexample.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binds map activity to an layout inflator
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Assigns a mapFragment to an existing fragment with an id of map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // Gets a mapFragment asynchronously
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //once the map is ready
        mMap = googleMap;

        // Add a default map marker in Boston area.
        LatLng sydney = new LatLng(42,  -71);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Boston"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                //creates a map marker at position that the user clicks on.
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title("New Marker");
                mMap.addMarker(marker);
                //moves camera to the point where the user put the marker on.
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                //Makes a toast where it shows the latitude and longitude of the position.
                Toast.makeText(getApplicationContext(), "the location in Latitude : " + latLng.latitude + " Longitude : " + latLng.longitude
                        , Toast.LENGTH_SHORT).show();


            }
        });
    }
}