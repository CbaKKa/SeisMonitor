package ru.mzhurov.seismonitor.ui;

public abstract class BaseColorService<T, K, V> {

    public <T> T getColor(final double magnitude) {
        K markerColor;

        if (magnitude < 1.0) {
            markerColor = getLowDangerColor();
        } else if (magnitude < 2.5) {
            markerColor = getMidDangerColor();
        } else if (magnitude < 4.5) {
            markerColor = getHighDangerColor();
        } else {
            markerColor = getExtremeDangerColor();
        }

        return (T) getResult(markerColor);
    }

    protected abstract K getLowDangerColor();

    protected abstract K getMidDangerColor();

    protected abstract K getHighDangerColor();

    protected abstract K getExtremeDangerColor();

    protected abstract V getResult(final K color);
}
