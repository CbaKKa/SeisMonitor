package ru.mzhurov.seismonitor.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.mzhurov.seismonitor.api.models.FeatureCollectionModel;

public interface ApiEarthquake {

    @GET(ApiUrls.QUERY)
    Call<FeatureCollectionModel> getData(@Query(ApiFields.FORMAT) String format,
                                         @Query(ApiFields.START_TIME) String startTime,
                                         @Query(ApiFields.END_TIME) String endTime);

    @GET(ApiUrls.QUERY)
    Call<FeatureCollectionModel> getData(@QueryMap Map<String, String> queryMap);
}
