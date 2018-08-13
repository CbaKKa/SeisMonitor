package ru.mzhurov.seismonitor.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter {

    private List<Earthquake> earthquakes;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView latitudeTextView;
        public TextView altitudeTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);

//            latitudeTextView = view.findViewById()
        }
    }

    public EarthquakesRecyclerAdapter(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
