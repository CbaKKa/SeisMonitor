package ru.mzhurov.seismonitor.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.model.Earthquake;
import ru.mzhurov.seismonitor.ui.view.CircularTextView;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter<EarthquakesRecyclerAdapter.ViewHolder> {

    private List<Earthquake> earthquakes = new ArrayList<>();
    private Context context = null;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView latitudeTextView;
        public TextView altitudeTextView;
        public TextView descriptionTextView;
        public TextView timeTextView;
        public CircularTextView magnitudeTextView;

        public ViewHolder(View view) {
            super(view);

            latitudeTextView = view.findViewById(R.id.text_view_earthquake_latitude);
            altitudeTextView = view.findViewById(R.id.text_view_earthquake_altitude);
            descriptionTextView = view.findViewById(R.id.text_view_earthquake_description);
            timeTextView = view.findViewById(R.id.text_view_earthquake_time);
            magnitudeTextView = view.findViewById(R.id.text_view_earthquake_magnitude);
        }
    }

    public EarthquakesRecyclerAdapter(final Context context) {
        this.context = context;
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

        final CircularTextView magnitudeTextView = holder.magnitudeTextView;

        double magnitude = earthquake.getMagnitude();
        magnitudeTextView.setText(String.format("%s", magnitude));

        final Resources resources = context.getResources();

        int magnitudeColor;

        if (magnitude <= 2) {
            magnitudeColor = resources.getColor(R.color.colorNoDanger);
        } else if (magnitude <= 4) {
            magnitudeColor = resources.getColor(R.color.colorLowDanger);
        } else if (magnitude <= 6) {
            magnitudeColor = resources.getColor(R.color.colorMidDanger);
        } else if (magnitude <= 8) {
            magnitudeColor = resources.getColor(R.color.colorHighDanger);
        } else {
            magnitudeColor = resources.getColor(R.color.colorExtremeDanger);
        }

        magnitudeTextView.setSolidColor(magnitudeColor);

        final long timestamp = earthquake.getTime();
        final Calendar calendar = Calendar.getInstance();
        final TimeZone timeZone = calendar.getTimeZone();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        simpleDateFormat.setTimeZone(timeZone);

        final String localTime = simpleDateFormat.format(new Date(timestamp));

        holder.timeTextView.setText(localTime);
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }
}
