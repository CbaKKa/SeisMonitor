package ru.mzhurov.seismonitor.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import ru.mzhurov.seismonitor.ui.adapters.EarthquakesRecyclerAdapter;
import ru.mzhurov.seismonitor.ui.model.Earthquake;
import ru.mzhurov.seismonitor.ui.model.SharedViewModel;

public class EarthquakesTabFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    private RecyclerView recyclerView;
    private EarthquakesRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.earthquakes_fragment, container, false);

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        recyclerView = view.findViewById(R.id.earthquakes_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EarthquakesRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        sharedViewModel.getData().observe(this, new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(@Nullable List<Earthquake> earthquakes) {
                adapter.setEarthquakes(earthquakes);
            }
        });

        return view;
    }
}
