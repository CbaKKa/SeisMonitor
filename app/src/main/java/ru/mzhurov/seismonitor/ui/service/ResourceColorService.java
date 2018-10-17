package ru.mzhurov.seismonitor.ui.service;

import android.content.res.Resources;

import ru.mzhurov.seismonitor.R;

public class ResourceColorService extends BaseColorService<Integer, Integer> {

    private final Resources resources;

    public ResourceColorService(final Resources resources) {
        this.resources = resources;
    }

    @Override
    protected Integer getLowDangerColor() {
        return resources.getColor(R.color.colorLowDanger);
    }

    @Override
    protected Integer getMidDangerColor() {
        return resources.getColor(R.color.colorMidDanger);
    }

    @Override
    protected Integer getHighDangerColor() {
        return resources.getColor(R.color.colorHighDanger);
    }

    @Override
    protected Integer getExtremeDangerColor() {
        return resources.getColor(R.color.colorExtremeDanger);
    }

    @Override
    protected Integer getResult(final Integer magnitudeColor) {
        return magnitudeColor;
    }
}