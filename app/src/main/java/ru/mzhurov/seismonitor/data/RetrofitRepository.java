package ru.mzhurov.seismonitor.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mzhurov.seismonitor.api.ApiEarthquake;
import ru.mzhurov.seismonitor.api.ApiFields;
import ru.mzhurov.seismonitor.api.models.FeatureCollectionModel;
import ru.mzhurov.seismonitor.api.models.FeatureModel;
import ru.mzhurov.seismonitor.api.models.GeometryModel;
import ru.mzhurov.seismonitor.api.models.PropertiesModel;
import ru.mzhurov.seismonitor.data.service.GeoService;
import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class RetrofitRepository {

    public static final String BASE_URL = "https://earthquake.usgs.gov/";
    private static final RetrofitRepository retrofitRepository = new RetrofitRepository();
    private static MutableLiveData<List<Earthquake>> data = new MutableLiveData<>();

    private static Retrofit retrofit = null;
    private static final GeoService geoService = new GeoService();

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }

        return retrofit;
    }

    public static RetrofitRepository getInstance() {
        return retrofitRepository;
    }

    public static MutableLiveData<List<Earthquake>> getData() {
        return data;
    }

    public void getAllEarthquakes() {
        Log.d("", "PROCESSING IN THREAD BEFORE RETROFIT CALL " + Thread.currentThread().getName());

        final String startDateString;
        final String endDateString;
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = getDateFormat();

        endDateString = simpleDateFormat.format(calendar.getTime());

        calendar.add(Calendar.DATE, -1);
        startDateString = simpleDateFormat.format(calendar.getTime());

        final Map<String, String> queryMap = new HashMap<>();
        queryMap.put(ApiFields.FORMAT, "geojson");
        queryMap.put(ApiFields.START_TIME, startDateString);
        queryMap.put(ApiFields.END_TIME, endDateString);

        sendResponse(queryMap);
    }

    private void sendResponse(final Map<String, String> queryMap) {
        final Call<FeatureCollectionModel> call = getRetrofitClient().create(ApiEarthquake.class)
                .getData(queryMap);

        call.enqueue(new Callback<FeatureCollectionModel>() {
            @Override
            public void onResponse(Call<FeatureCollectionModel> call, Response<FeatureCollectionModel> response) {
                FeatureCollectionModel featureCollectionModel = response.body();

                List<Earthquake> earthquakes = new ArrayList<>();

                for (final FeatureModel featureModel : featureCollectionModel.getFeatureModels()) {
                    final GeometryModel geometryModel = featureModel.getGeometryModel();
                    final List<Double> coordinates = geometryModel.getCoordinates();

                    final PropertiesModel propertiesModel = featureModel.getPropertiesModel();
                    final double magnitude = propertiesModel.getMag();

                    final long time = propertiesModel.getTime();

                    final Earthquake earthquake = new Earthquake(time, coordinates.get(0), coordinates.get(1),
                            magnitude, propertiesModel.getPlace());

                    earthquakes.add(earthquake);
                }

                Log.d("EARTHQUAKES", "EARTHQUAKES COUNT IS: " + earthquakes.size());

                earthquakes = geoService.mergeEarthquakesToOnePoint(earthquakes, 0.5);

                data.postValue(earthquakes);

                Log.d("", "PROCESSING IN THREAD IN RETROFIT RESPONSE HANDLER " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<FeatureCollectionModel> call, Throwable t) {
                Log.d("", "error RETROFIT");
            }
        });
    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
