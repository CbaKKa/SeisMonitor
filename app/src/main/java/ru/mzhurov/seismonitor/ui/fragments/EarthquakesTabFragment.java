package ru.mzhurov.seismonitor.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.models.Earthquake;
import ru.mzhurov.seismonitor.ui.MainActivity;
import ru.mzhurov.seismonitor.ui.adapters.EarthquakesRecyclerAdapter;

public class EarthquakesTabFragment extends Fragment {

    private static List<Earthquake> earthquakes;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        earthquakes = MainActivity.getEarthquakes();

        if (container != null) {
            recyclerView = container.findViewById(R.id.earthquakes_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(container.getContext());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new EarthquakesRecyclerAdapter(earthquakes);
            recyclerView.setAdapter(adapter);
        }

        return inflater.inflate(R.layout.earthquakes_fragment, container, false);
    }
}
