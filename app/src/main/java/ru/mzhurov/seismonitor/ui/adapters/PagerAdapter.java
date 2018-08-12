package ru.mzhurov.seismonitor.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.mzhurov.seismonitor.ui.fragments.EarthquakesTabFragment;
import ru.mzhurov.seismonitor.ui.fragments.MapTabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);

        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                EarthquakesTabFragment earthquakesTabFragment = new EarthquakesTabFragment();
                return earthquakesTabFragment;
            case 1:
                MapTabFragment mapTabFragment = new MapTabFragment();
                return mapTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
