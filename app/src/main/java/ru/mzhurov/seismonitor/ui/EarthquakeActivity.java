package ru.mzhurov.seismonitor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class EarthquakeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String MAP_BUNDLE      = "MAP_BUNDLE";
    private static final int    EARTHQUAKE_ITEM = R.string.earthquake_activity_bundle_key;

    final Calendar         calendar         = Calendar.getInstance();
    final TimeZone         timeZone         = calendar.getTimeZone();
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

    private MapView   mapView;
    private GoogleMap googleMap;
    private Earthquake earthquake = new Earthquake();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        simpleDateFormat.setTimeZone(timeZone);

        Toolbar toolbar = findViewById(R.id.toolbar);

        final Intent intent = getIntent();
        Bundle mapBundle = null;

        if (savedInstanceState != null) {
            mapBundle = savedInstanceState.getBundle(MAP_BUNDLE);
        }

        String description = null;

        if (intent != null) {
            final String key = getString(EARTHQUAKE_ITEM);
            earthquake = (Earthquake) intent.getSerializableExtra(key);

            description = earthquake.getDescription();

            toolbar.setTitle(description);
        }

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        mapView = findViewById(R.id.earthquake_map_view);
        mapView.onCreate(mapBundle);
        mapView.getMapAsync(this);

        final TextView earthquakeTitleTextView = findViewById(R.id.text_view_earthquake_title);
        earthquakeTitleTextView.setText(description);

        final long time = earthquake.getTime();
        final String timeString = simpleDateFormat.format(new Date(time));

        final TextView earthquakeIncidentTimeTextView = findViewById(R.id.text_view_earthquake_incident_time);
        earthquakeIncidentTimeTextView.setText(timeString);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        final UiSettings uiSettings = this.googleMap.getUiSettings();

        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        if (earthquake != null) {
            final LatLng position = new LatLng(earthquake.getLatitude(), earthquake.getLongtitude());
            final String title = earthquake.getDescription();

            final MarkerOptions markerOptions = new MarkerOptions()
                    .position(position)
                    .title(title);

            this.googleMap.addMarker(markerOptions);

//            1: World
//            5: Landmass/continent
//            10: City
//            15: Streets
//            20: Buildings

            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 3f));
        }

        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(EarthquakeActivity.this.getApplicationContext(),
                        marker.getTitle(), Toast.LENGTH_SHORT)
                        .show();

                return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_BUNDLE);

        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_BUNDLE, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mapView.onLowMemory();
    }
}
