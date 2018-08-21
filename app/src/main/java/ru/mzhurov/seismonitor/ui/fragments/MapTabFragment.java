package ru.mzhurov.seismonitor.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.BaseColorService;
import ru.mzhurov.seismonitor.ui.DrawableColorService;
import ru.mzhurov.seismonitor.ui.fragments.cluster.ClusterRenderer;
import ru.mzhurov.seismonitor.ui.fragments.cluster.EarthquakeItem;
import ru.mzhurov.seismonitor.ui.model.Earthquake;
import ru.mzhurov.seismonitor.ui.model.SharedViewModel;

public class MapTabFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    private SharedViewModel sharedViewModel;
    private ClusterManager<EarthquakeItem> mClusterManager;
    private static List<Earthquake> earthquakes = new ArrayList<>();

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private final BaseColorService<Integer, Integer, BitmapDescriptor> colorService = new DrawableColorService();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_layout, container, false);

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        Bundle mapViewBundle = null;

        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        sharedViewModel.getData().observe(this, new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(@Nullable List<Earthquake> earthquakes) {
                addEarthquakesToClusterManager(earthquakes);

                MapTabFragment.earthquakes = earthquakes;
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);

        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setUpCluster();

        final UiSettings uiSettings = this.googleMap.getUiSettings();

        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                final LatLng position = marker.getPosition();

                Earthquake selectedEarthquake = new Earthquake();

                for (final Earthquake earthquake : earthquakes) {
                    if (earthquake.getLongtitude() == position.longitude && earthquake.getLatitude() == position.latitude) {
                        selectedEarthquake = earthquake;
                    }
                }

                final double magnitude = selectedEarthquake.getMagnitude();

                Toast.makeText(MapTabFragment.this.getContext(),
                        marker.getTitle() + ", " + magnitude, Toast.LENGTH_SHORT)
                        .show();

                return true;
            }
        });
    }

    private void addEarthquakesToClusterManager(final List<Earthquake> earthquakesList) {
        for (final Earthquake earthquake : earthquakesList) {
            final double magnitude = earthquake.getMagnitude();
            final BitmapDescriptor icon = colorService.getColor(magnitude);

            final EarthquakeItem earthquakeItem = new EarthquakeItem(earthquake.getLatitude(),
                    earthquake.getLongtitude(), earthquake.getMagnitude(), earthquake.getDescrtiption(), icon);

            mClusterManager.addItem(earthquakeItem);

            mClusterManager.cluster();
        }
    }

    private void setUpCluster() {
        mClusterManager = new ClusterManager<>(getContext(), googleMap);
        mClusterManager.setRenderer(new ClusterRenderer(getContext(), googleMap, mClusterManager));

        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
    }
}
