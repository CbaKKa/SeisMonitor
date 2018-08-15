package ru.mzhurov.seismonitor.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.model.Earthquake;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter<EarthquakesRecyclerAdapter.ViewHolder> {

    private List<Earthquake> earthquakes = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView latitudeTextView;
        public TextView altitudeTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);

            latitudeTextView = view.findViewById(R.id.text_view_earthquake_latitude);
            altitudeTextView = view.findViewById(R.id.text_view_earthquake_altitude);
            descriptionTextView = view.findViewById(R.id.text_view_earthquake_description);
        }
    }

    public void setEarthquakes(final List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;

        notifyDataSetChanged();
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
