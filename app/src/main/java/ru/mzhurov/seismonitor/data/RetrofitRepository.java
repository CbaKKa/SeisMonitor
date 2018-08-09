package ru.mzhurov.seismonitor.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mzhurov.seismonitor.api.ApiEarthquake;
import ru.mzhurov.seismonitor.api.models.FeatureCollectionModel;
import ru.mzhurov.seismonitor.api.models.FeatureModel;

public class RetrofitRepository {

    public static final String BASE_URL = "";
    private static MutableLiveData<FeatureCollectionModel> data = new MutableLiveData<>();

    private static Retrofit retrofit = null;

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

    public static LiveData<FeatureCollectionModel> getData() {
        return data;
    }

    public static void getFeatureModel() {
        Log.d("", "PROCESSING IN THREAD BEFORE RETROFIT CALL " + Thread.currentThread().getName());

        final Call<FeatureCollectionModel> call = getRetrofitClient().create(ApiEarthquake.class).getData();

        call.enqueue(new Callback<FeatureCollectionModel>() {
            @Override
            public void onResponse(Call<FeatureCollectionModel> call, Response<FeatureCollectionModel> response) {
                FeatureCollectionModel featureCollectionModel = response.body();

                data.postValue(featureCollectionModel);

                Log.d("", "PROCESSING IN THREAD IN RETROFIT RESPONSE HANDLER " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<FeatureCollectionModel> call, Throwable t) {
                Log.d("", "error RETROFIT");
            }
        });
    }
}
