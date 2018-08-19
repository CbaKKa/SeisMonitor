package ru.mzhurov.seismonitor.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class GeoService {

    public List<Earthquake> mergeEarthquakesToOnePoint(final List<Earthquake> earthquakes, final int radius) {
        final List<Earthquake> baseEarthquakeList = new ArrayList<>(earthquakes);
        final List<Earthquake> result = new ArrayList<>(earthquakes);
        final Map<Point, List<Earthquake>> pointListMap = new HashMap<>();

        for (final Earthquake earthquake : earthquakes) {
            final double latitude = earthquake.getLatitude();
            final double altitude = earthquake.getLongtitude();

            final Point point = new Point(latitude, altitude);


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

    class Point {
        private double latitude;
        private double altitude;

        public Point(final double latitude, final double altitude) {
            this.latitude = latitude;
            this.altitude = altitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(final double latitude) {
            this.latitude = latitude;
        }

        public double getAltitude() {
            return altitude;
        }

        public void setAltitude(final double altitude) {
            this.altitude = altitude;
        }

        @Override
        public int hashCode() {
            return (int) (5 * (latitude + altitude));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point point = (Point) obj;

                return latitude == point.latitude && altitude == point.altitude;
            } else {
                return false;
            }
        }
    }
}
