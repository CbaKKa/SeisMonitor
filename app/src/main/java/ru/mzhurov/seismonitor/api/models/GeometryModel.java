package ru.mzhurov.seismonitor.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class GeometryModel {
    @SerializedName("type")
    private String type;

    @SerializedName("coordinates")
    private List<Float> coordinates;

    public GeometryModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }
}
