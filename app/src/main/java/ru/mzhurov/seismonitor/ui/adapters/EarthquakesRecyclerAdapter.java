package ru.mzhurov.seismonitor.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter<EarthquakesRecyclerAdapter.ViewHolder> {

    //TODO: pair adapter with recyclerview

    private List<Earthquake> earthquakes;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView latitudeTextView;
        public TextView altitudeTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);

            latitudeTextView = view.findViewById(R.id.earthquake_latitude_text_view);
            altitudeTextView = view.findViewById(R.id.earthquake_altitude_text_view);
            descriptionTextView = view.findViewById(R.id.earthquake_description_text_view);
        }
    }

//    public EarthquakesRecyclerAdapter(List<Earthquake> earthquakes) {
//        this.earthquakes = earthquakes;
//    }

    public void setEarthquakes(final List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquakes_item_layout,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Earthquake earthquake = earthquakes.get(position);

        holder.latitudeTextView.setText(String.format("%s", earthquake.getLatitude()));
        holder.altitudeTextView.setText(String.format("%s", earthquake.getLongtitude()));
        holder.descriptionTextView.setText(earthquake.getDescrtiption());
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }
}
