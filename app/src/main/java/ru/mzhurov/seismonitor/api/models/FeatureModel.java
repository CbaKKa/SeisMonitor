package ru.mzhurov.seismonitor.api.models;

import com.google.gson.annotations.SerializedName;

public class FeatureModel {
    @SerializedName("type")
    private String type;

    @SerializedName("properties")
    private PropertiesModel propertiesModel;

    @SerializedName("geometry")
    private GeometryModel geometryModel;

    @SerializedName("id")
    private String id;

    public FeatureModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PropertiesModel getPropertiesModel() {
        return propertiesModel;
    }

    public void setPropertiesModel(PropertiesModel propertiesModel) {
        this.propertiesModel = propertiesModel;
    }

    public GeometryModel getGeometryModel() {
        return geometryModel;
    }

    public void setGeometryModel(GeometryModel geometryModel) {
        this.geometryModel = geometryModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}