package ru.mzhurov.seismonitor.ui.service;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class BitmapColorService extends BaseColorService<BitmapDescriptor, Float> {
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
    protected BitmapDescriptor getResult(final Float magnitudeColor) {
        return BitmapDescriptorFactory.defaultMarker(magnitudeColor);
    }
}