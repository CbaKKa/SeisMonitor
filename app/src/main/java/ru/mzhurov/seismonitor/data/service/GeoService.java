package ru.mzhurov.seismonitor.data.service;

import java.util.ArrayList;
import java.util.List;

import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class GeoService {

    public List<Earthquake> mergeEarthquakesToOneDot(final List<Earthquake> earthquakes) {
        final List<Earthquake> baseEarthquakeList = new ArrayList<>(earthquakes);
        final List<Earthquake> result = new ArrayList<>(earthquakes);

        for (final Earthquake baseEarthquake : baseEarthquakeList) {
            for (final Earthquake earthquake : result) {
                if (!baseEarthquake.equals(earthquake)) {
                    if (checkPointRadius(baseEarthquake, earthquake, 1)) {
                        result.remove(earthquake);
                        baseEarthquakeList.remove(earthquake);
                    }
                }
            }
        }

        return result;
    }

    private boolean checkPointRadius(final Earthquake baseEarthquake, final Earthquake earthquake, final double radius) {
        final double baseLongtitude = baseEarthquake.getLongtitude();
        final double baseAltitude = baseEarthquake.getLatitude();

        final double longtitude = earthquake.getLongtitude();
        final double altitude = earthquake.getLatitude();

        final double longtitudeDelta = Math.abs(baseLongtitude - longtitude);
        final double altitudeDelta = Math.abs(baseAltitude - altitude);

        return (longtitudeDelta <= radius) && (altitudeDelta <= radius);
    }
}
