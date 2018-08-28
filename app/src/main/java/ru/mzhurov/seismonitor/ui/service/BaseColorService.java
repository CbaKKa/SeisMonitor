package ru.mzhurov.seismonitor.ui.service;

public abstract class BaseColorService<T, V> {

    public T getColor(final double magnitude) {
        V magnitudeColor;

        if (magnitude < 1.0) {
            magnitudeColor = getLowDangerColor();
        } else if (magnitude < 2.5) {
            magnitudeColor = getMidDangerColor();
        } else if (magnitude < 4.5) {
            magnitudeColor = getHighDangerColor();
        } else {
            magnitudeColor = getExtremeDangerColor();
        }

        return getResult(magnitudeColor);
    }

    protected abstract V getLowDangerColor();

    protected abstract V getMidDangerColor();

    protected abstract V getHighDangerColor();

    protected abstract V getExtremeDangerColor();

    protected abstract T getResult(final V magnitudeColor);
}
