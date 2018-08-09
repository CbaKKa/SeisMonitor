package ru.mzhurov.seismonitor;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mzhurov.seismonitor.api.ApiEarthquake;
import ru.mzhurov.seismonitor.api.models.FeatureCollectionModel;
import ru.mzhurov.seismonitor.data.RetrofitRepository;

public class MainActivity extends AppCompatActivity {

    private static ApiEarthquake apiEarthquake;
    private Retrofit retrofit;
    private FeatureCollectionModel featureCollectionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //<editor-fold desc="Retrofit example">
        retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiEarthquake = retrofit.create(ApiEarthquake.class);

        apiEarthquake.getData().enqueue(new Callback<FeatureCollectionModel>() {
            @Override
            public void onResponse(Call<FeatureCollectionModel> call, Response<FeatureCollectionModel> response) {
                featureCollectionModel = response.body();
            }

            @Override
            public void onFailure(Call<FeatureCollectionModel> call, Throwable t) {
                int a;
                a = 0;
            }
        });
        //</editor-fold>

        RetrofitRepository.getData().observe(this, new Observer<FeatureCollectionModel>() {
            @Override
            public void onChanged(@Nullable FeatureCollectionModel featureCollectionModel) {
                featureCollectionModel = featureCollectionModel;
            }
        });
    }
}
