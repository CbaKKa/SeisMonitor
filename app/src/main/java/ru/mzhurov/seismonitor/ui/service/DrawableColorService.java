package ru.mzhurov.seismonitor.ui.service;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import ru.mzhurov.seismonitor.R;

public class DrawableColorService extends BaseColorService<Drawable, Integer> {

    private final Context context;

    public DrawableColorService(final Context context) {
        this.context = context;
    }

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
    protected Drawable getResult(final Integer magnitudeColor) {
        return ContextCompat.getDrawable(context, magnitudeColor);
    }
}
