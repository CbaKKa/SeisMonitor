package ru.mzhurov.seismonitor.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class GeoService {

    public List<Earthquake> mergeEarthquakesToOnePoint(final List<Earthquake> earthquakes, final double radius) {
        final Map<Point, Earthquake> earthquakesMap = new HashMap<>();

        for (final Earthquake earthquake : earthquakes) {
            double latitude = earthquake.getLatitude();
            double altitude = earthquake.getLongtitude();

//            latitude = Math.round(latitude * 100.0) / 100.0;
//            altitude = Math.round(altitude * 100.0) / 100.0;
            latitude = Math.round(latitude);
            altitude = Math.round(altitude);

            final Point point = new Point(latitude, altitude);

            final Earthquake newestEarthquake = earthquakesMap.get(point);

            if (newestEarthquake == null) {
                earthquakesMap.put(point, earthquake);
            } else if ((newestEarthquake.getMagnitude() < earthquake.getMagnitude()) && (earthquake.getMagnitude() >= 5)) {
                earthquakesMap.put(point, earthquake);
            } else if (newestEarthquake.getTime() < earthquake.getTime()) {
                earthquakesMap.put(point, earthquake);
            }
        }

        return new ArrayList<>(earthquakesMap.values());
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
