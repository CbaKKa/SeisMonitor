package ru.mzhurov.seismonitor.ui.fragments.cluster;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

public class EarthquakeItem implements ClusterItem {

    private double latitude;
    private double longtitude;
    private double magnitude;
    private String description;
    private MarkerOptions markerOptions;
    private BitmapDescriptor icon;

    public EarthquakeItem(final double latitude, final double longtitude, final String description, final BitmapDescriptor icon) {
        this.description = description;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.icon = icon;

        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(latitude, longtitude));
        markerOptions.title(description);
        markerOptions.icon(icon);
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longtitude);
    }

    @Override
    public String getTitle() {
        return description;
    }

    @Override
    public String getSnippet() {
        return String.valueOf(magnitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(final double longtitude) {
        this.longtitude = longtitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(final double magnitude) {
        this.magnitude = magnitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(final MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    public void setIcon(final BitmapDescriptor icon) {
        this.icon = icon;
    }
}
