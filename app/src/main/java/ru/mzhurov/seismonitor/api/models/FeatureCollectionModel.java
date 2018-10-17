package ru.mzhurov.seismonitor.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeatureCollectionModel {
    @SerializedName("type")
    private String type;

    @SerializedName("metadata")
    private MetadataModel metadataModel;

    @SerializedName("features")
    private List<FeatureModel> featureModels;

    public FeatureCollectionModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MetadataModel getMetadataModel() {
        return metadataModel;
    }

    public void setMetadataModel(MetadataModel metadataModel) {
        this.metadataModel = metadataModel;
    }

    public List<FeatureModel> getFeatureModels() {
        return featureModels;
    }

    public void setFeatureModels(List<FeatureModel> featureModels) {
        this.featureModels = featureModels;
    }
}
