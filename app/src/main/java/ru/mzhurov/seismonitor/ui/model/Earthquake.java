package ru.mzhurov.seismonitor.ui.model;

public class Earthquake {
    private double longtitude;
    private double latitude;
    private double magnitude;
    private String descrtiption;
    private long time;

    public Earthquake() {
    }

    public double getLongtitude() {
        return longtitude;
    }

    public Earthquake(final long time, final double longtitude, final double latitude, final double magnitude, final String descrtiption) {
        this.time = time;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.magnitude = magnitude;
        this.descrtiption = descrtiption;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public void setLongtitude(final double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(final double magnitude) {
        this.magnitude = magnitude;
    }

    public String getDescription() {
        return descrtiption;
    }

    public void setDescrtiption(final String descrtiption) {
        this.descrtiption = descrtiption;
    }

    @Override
    public int hashCode() {
        return (int) (5 * (longtitude + latitude + time + descrtiption.hashCode()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Earthquake) {
            Earthquake earthquake = (Earthquake) obj;

            return (earthquake.longtitude == getLongtitude()) && (earthquake.latitude == getLatitude() && (earthquake.time == getTime() && (earthquake.magnitude == getMagnitude())));
        } else {
            return false;
        }
    }
}
