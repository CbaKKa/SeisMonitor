package ru.mzhurov.seismonitor.api;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mzhurov.seismonitor.api.models.FeatureCollectionModel;

public interface ApiEarthquake {

    @GET("fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02")
    Call<FeatureCollectionModel> getData();
}
