package ru.mzhurov.seismonitor;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ru.mzhurov.seismonitor.data.RetrofitRepository;
import ru.mzhurov.seismonitor.models.Earthquake;

public class MainActivity extends AppCompatActivity {

    private static List<Earthquake> earthquakes;

    private Button allEarthqueakesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allEarthqueakesButton = findViewById(R.id.button_all_earthquakes);
        allEarthqueakesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.earthquakes_list_fragment, new EarthquakesFragment());

                fragmentTransaction.commit();
            }
        });

        RetrofitRepository.getData().observe(this, new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(@Nullable final List<Earthquake> earthquakes) {
                MainActivity.earthquakes = earthquakes;
            }
        });

        RetrofitRepository.getFeatureModel();
    }
}
