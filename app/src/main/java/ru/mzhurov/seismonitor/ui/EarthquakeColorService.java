package ru.mzhurov.seismonitor.ui;

import android.content.res.Resources;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import ru.mzhurov.seismonitor.R;

public class EarthquakeColorService {

    public static int getColor(final Resources resources, final double magnitude) {
        int magnitudeColor;

        if (magnitude <= 2) {
            magnitudeColor = resources.getColor(R.color.colorNoDanger);
        } else if (magnitude <= 4) {
            magnitudeColor = resources.getColor(R.color.colorLowDanger);
        } else if (magnitude <= 6) {
            magnitudeColor = resources.getColor(R.color.colorMidDanger);
        } else if (magnitude <= 8) {
            magnitudeColor = resources.getColor(R.color.colorHighDanger);
        } else {
            magnitudeColor = resources.getColor(R.color.colorExtremeDanger);
        }

        return magnitudeColor;
    }

    public static BitmapDescriptor getMarkerColor(final double magnitude) {
        float markerColor;

        if (magnitude <= 2) {
            markerColor = BitmapDescriptorFactory.HUE_CYAN;
        } else if (magnitude <= 4) {
            markerColor = BitmapDescriptorFactory.HUE_GREEN;
        } else if (magnitude <= 6) {
            markerColor = BitmapDescriptorFactory.HUE_YELLOW;
        } else if (magnitude <= 8) {
            markerColor = BitmapDescriptorFactory.HUE_ORANGE;
        } else {
            markerColor = BitmapDescriptorFactory.HUE_RED;
        }

        return BitmapDescriptorFactory.defaultMarker(markerColor);
    }
}
