package ru.mzhurov.seismonitor.ui;

import android.content.res.Resources;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import ru.mzhurov.seismonitor.R;

public class EarthquakeColorService {

    public static int getColor(final Resources resources, final double magnitude) {
        int magnitudeColor;

        if (magnitude < 1.0) {
            magnitudeColor = resources.getColor(R.color.colorLowDanger);
        } else if (magnitude < 2.5) {
            magnitudeColor = resources.getColor(R.color.colorMidDanger);
        } else if (magnitude < 4.5) {
            magnitudeColor = resources.getColor(R.color.colorHighDanger);
        } else {
            magnitudeColor = resources.getColor(R.color.colorExtremeDanger);
        }

        return magnitudeColor;
    }

    public static BitmapDescriptor getMarkerColor(final double magnitude) {
        float markerColor;

        if (magnitude < 1.0) {
            markerColor = BitmapDescriptorFactory.HUE_CYAN;
        } else if (magnitude < 2.5) {
            markerColor = BitmapDescriptorFactory.HUE_GREEN;
        } else if (magnitude < 4.5) {
            markerColor = BitmapDescriptorFactory.HUE_YELLOW;
        } else {
            markerColor = BitmapDescriptorFactory.HUE_RED;
        }

        return BitmapDescriptorFactory.defaultMarker(markerColor);
    }
}
