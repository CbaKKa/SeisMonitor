package ru.mzhurov.seismonitor.ui.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<Earthquake>> mutableLiveData = new MutableLiveData<>();

    public List<Earthquake> getData() {
        return mutableLiveData.getValue();
    }

    public void setData(final List<Earthquake> earthquakes) {
        mutableLiveData.postValue(earthquakes);
    }


}
