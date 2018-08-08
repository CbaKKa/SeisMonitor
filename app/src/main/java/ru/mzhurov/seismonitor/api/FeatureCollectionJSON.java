package ru.mzhurov.seismonitor.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeatureCollectionJSON {
    @SerializedName("type")
    private String type;

    @SerializedName("metadata")
    private Metadata metadata;

    @SerializedName("features")
    private List<Feature> features;
}
