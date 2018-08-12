package ru.mzhurov.seismonitor.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeometryModel {
    @SerializedName("type")
    private String type;

    @SerializedName("coordinates")
    private List<Double> coordinates;

    public GeometryModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
