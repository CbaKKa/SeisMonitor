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

import ru.mzhurov.seismonitor.R;
import ru.mzhurov.seismonitor.ui.adapters.EarthquakesRecyclerAdapter;
import ru.mzhurov.seismonitor.ui.model.SharedViewModel;

public class EarthquakesTabFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.earthquakes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO
//        earthquakes =

                recyclerView = getView().findViewById(R.id.earthquakes_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getView().getContext());
        recyclerView.setLayoutManager(layoutManager);

//        adapter = new EarthquakesRecyclerAdapter(earthquakes);
//        recyclerView.setAdapter(adapter);
    }
}
