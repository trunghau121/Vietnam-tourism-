package com.example.hau.dulichviet;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hau.dulichviet.Adapter.AdapterInfoWindow;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.Models.ImageHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HAU on 12/6/2015.
 */
public class MapTour extends AppCompatActivity {
    DataPlace.Place place;
    GoogleMap map;
    //@InjectView(R.id.toolbar)
    Toolbar tbMap;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maptour);
        if (getIntent() != null) {
            if (getIntent().getBundleExtra("bundle") != null) {
                place = (DataPlace.Place) getIntent().getBundleExtra("bundle").getSerializable("data");
            }
        }
        dialog = new ProgressDialog(this);
        dialog.setTitle("Đang tải Map ...");
        dialog.setMessage("Vui lòng chờ...");
        dialog.setCancelable(true);
        dialog.show();
        map=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                dialog.dismiss();
                createMarker();

            }
        });
        map.getUiSettings().isZoomControlsEnabled();
        map.setMyLocationEnabled(true);
        tbMap =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbMap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }
    public void createMarker(){
        LatLng latLng=new LatLng(Double.parseDouble(place.getLatitude()),Double.parseDouble( place.getLongitude()));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .bearing(90)
                .tilt(40)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions option=new MarkerOptions();
        option.position(latLng);
        option.icon(BitmapDescriptorFactory.fromResource(ImageHelper.getIconMarker(Integer.parseInt(place.getCategory_id()))));
        Marker currentMarker= map.addMarker(option);
        map.setInfoWindowAdapter(new AdapterInfoWindow(this,place));
        currentMarker.showInfoWindow();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

}
