package ru.mzhurov.seismonitor.ui;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import ru.mzhurov.seismonitor.R;

public class DrawableColorService extends BaseColorService<Integer, Integer, BitmapDescriptor> {
    @Override
    protected Integer getLowDangerColor() {
        return R.drawable.shape_circle_low_danger;
    }

    @Override
    protected Integer getMidDangerColor() {
        return R.drawable.shape_circle_mid_danger;
    }

    @Override
    protected Integer getHighDangerColor() {
        return R.drawable.shape_circle_high_danger;
    }

    @Override
    protected Integer getExtremeDangerColor() {
        return R.drawable.shape_circle_extreme_danger;
    }

    @Override
    protected BitmapDescriptor getResult(final Integer color) {
        return BitmapDescriptorFactory.defaultMarker(color);
    }
}
