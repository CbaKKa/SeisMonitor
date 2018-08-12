package ru.mzhurov.seismonitor.api.models;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class PropertiesModel {
    @SerializedName("mag")
    private double mag;

    @SerializedName("time")
    private long time;

    @SerializedName("updated")
    private long updated;

    @SerializedName("tz")
    private int tz;

    @SerializedName("place")
    private String place;

    @SerializedName("url")
    private URL url;

    @SerializedName("detail")
    private URL detail;

    @SerializedName("felt")
    private Object felt;

    @SerializedName("cdi")
    private Object cdi;

    @SerializedName("mmi")
    private Object mmi;

    @SerializedName("alert")
    private Object alert;

    @SerializedName("status")
    private String status;

    @SerializedName("tsunami")
    private int tsunami;

    @SerializedName("sig")
    private int sig;

    @SerializedName("net")
    private String net;

    @SerializedName("code")
    private String code;

    @SerializedName("ids")
    private String ids;

    @SerializedName("sources")
    private String sources;

    @SerializedName("types")
    private String types;

    @SerializedName("nst")
    private int nst;

    @SerializedName("dmin")
    private float dmin;

    @SerializedName("rms")
    private float rms;

    @SerializedName("gap")
    private String gap;

    @SerializedName("magType")
    private String magType;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    public PropertiesModel() {
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getTz() {
        return tz;
    }

    public void setTz(int tz) {
        this.tz = tz;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getDetail() {
        return detail;
    }

    public void setDetail(URL detail) {
        this.detail = detail;
    }

    public Object getFelt() {
        return felt;
    }

    public void setFelt(Object felt) {
        this.felt = felt;
    }

    public Object getCdi() {
        return cdi;
    }

    public void setCdi(Object cdi) {
        this.cdi = cdi;
    }

    public Object getMmi() {
        return mmi;
    }

    public void setMmi(Object mmi) {
        this.mmi = mmi;
    }

    public Object getAlert() {
        return alert;
    }

    public void setAlert(Object alert) {
        this.alert = alert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTsunami() {
        return tsunami;
    }

    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }

    public int getSig() {
        return sig;
    }

    public void setSig(int sig) {
        this.sig = sig;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getNst() {
        return nst;
    }

    public void setNst(int nst) {
        this.nst = nst;
    }

    public float getDmin() {
        return dmin;
    }

    public void setDmin(float dmin) {
        this.dmin = dmin;
    }

    public float getRms() {
        return rms;
    }

    public void setRms(float rms) {
        this.rms = rms;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

    public String getMagType() {
        return magType;
    }

    public void setMagType(String magType) {
        this.magType = magType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
