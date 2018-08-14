package ru.mzhurov.seismonitor.ui.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ru.mzhurov.seismonitor.data.RetrofitRepository;

public class SharedViewModel extends AndroidViewModel {

    private MutableLiveData<List<Earthquake>> mutableLiveData;

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Earthquake>> getData() {
        if (mutableLiveData == null) {
            mutableLiveData = RetrofitRepository.getData();
        }

        return mutableLiveData;
    }

    public Earthquake getEarthquakeByPosition(final int i) {
        final List<Earthquake> earthquakes = mutableLiveData.getValue();

        return earthquakes.get(i);
    }

}
