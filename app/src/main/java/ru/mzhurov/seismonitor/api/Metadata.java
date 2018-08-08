package ru.mzhurov.seismonitor.api;

import com.google.gson.annotations.SerializedName;

public class Metadata {
    @SerializedName("generated")
    private long generated;

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private int status;

    @SerializedName("api")
    private String api;

    @SerializedName("count")
    private int count;

    public long getGenerated() {
        return generated;
    }

    public void setGenerated(long generated) {
        this.generated = generated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
