package ru.mzhurov.seismonitor.ui.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.EarthquakeActivity;
import ru.mzhurov.seismonitor.ui.model.Earthquake;
import ru.mzhurov.seismonitor.ui.service.ResourceColorService;
import ru.mzhurov.seismonitor.ui.view.CircularTextView;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter<EarthquakesRecyclerAdapter.ViewHolder> {

    private static SimpleDateFormat     simpleDateFormat;
    private        ResourceColorService colorService;
    private        ClipboardManager     clipboardManager;

    private List<Earthquake> earthquakes = new ArrayList<>();
    private Context context;

    static {
        final Calendar calendar = Calendar.getInstance();
        final TimeZone timeZone = calendar.getTimeZone();
        simpleDateFormat = new SimpleDateFormat("HH:mm dd:MM:yyyy");

        simpleDateFormat.setTimeZone(timeZone);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView         latitudeTextView;
        public TextView         altitudeTextView;
        public TextView         descriptionTextView;
        public TextView         timeTextView;
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

        colorService = new ResourceColorService(context.getResources());
        clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Earthquake earthquake = earthquakes.get(position);

        holder.latitudeTextView.setText(String.format("%s", earthquake.getLatitude()));
        holder.altitudeTextView.setText(String.format("%s", earthquake.getLongtitude()));
        holder.descriptionTextView.setText(earthquake.getDescription());

        final CircularTextView magnitudeTextView = holder.magnitudeTextView;

        double magnitude = earthquake.getMagnitude();
        magnitudeTextView.setText(String.format("%s", magnitude));

        final int magnitudeColor = colorService.getColor(magnitude);

        magnitudeTextView.setSolidColor(magnitudeColor);

        final long timestamp = earthquake.getTime();
        final String localTime = simpleDateFormat.format(new Date(timestamp));

        holder.timeTextView.setText(localTime);

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String latLng = earthquake.getLatitude() + ", " + earthquake.getLongtitude();

                final ClipData clipData = ClipData.newPlainText("Earthquake LatLng", latLng);

                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(context, "Position copied", Toast.LENGTH_SHORT).show();
            }
        };

        holder.itemView.findViewById(R.id.linear_layout_earthquake_coordinates).setOnClickListener(onClickListener);

        final View.OnClickListener openEarthquakeOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent(context, EarthquakeActivity.class);
                final Bundle bundle = new Bundle();

                final int id = R.string.earthquake_activity_bundle_key;
                final String key = context.getString(id);

                bundle.putSerializable(key, earthquake);

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        };

        holder.itemView.getRootView().setOnClickListener(openEarthquakeOnClickListener);
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }
}
