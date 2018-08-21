package ru.mzhurov.seismonitor.ui;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class MarkerColorService extends BaseColorService<Float, Float, Float> {
    @Override
    protected Float getLowDangerColor() {
        return BitmapDescriptorFactory.HUE_CYAN;
    }

    @Override
    protected Float getMidDangerColor() {
        return BitmapDescriptorFactory.HUE_GREEN;
    }

    @Override
    protected Float getHighDangerColor() {
        return BitmapDescriptorFactory.HUE_YELLOW;
    }

    @Override
    protected Float getExtremeDangerColor() {
        return BitmapDescriptorFactory.HUE_RED;
    }

    @Override
    protected Float getResult(final Float color) {
        return color;
    }
}
