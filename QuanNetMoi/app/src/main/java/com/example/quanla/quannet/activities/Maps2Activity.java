package com.example.quanla.quannet.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.adapters.CustomInfoAdapter;
import com.example.quanla.quannet.adapters.GameRoomAdapter;
import com.example.quanla.quannet.database.models.GameRoom;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback {
    private  GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
        Intent intent = getIntent();

        //GameRoom gameRoom = new GameRoom(getUrlImage(),getName(),getAddress(),getRate(),getLatitude(),getLongtitude());
        GameRoom gameRoom = (GameRoom) intent.getSerializableExtra("GAME");
        Log.d("GameROom", String.format("onClick: %s",gameRoom ));
        // Add a marker in Sydney and move the camera
        LatLng locgame = new LatLng(gameRoom.getLatitude(), gameRoom.getLongitude());
        Marker marker = mMap.addMarker(new MarkerOptions().position(locgame).title(gameRoom.getTitle()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locgame,13));
        mMap.setInfoWindowAdapter(new CustomInfoAdapter(Maps2Activity.this,gameRoom));
        marker.showInfoWindow();

    }
}
