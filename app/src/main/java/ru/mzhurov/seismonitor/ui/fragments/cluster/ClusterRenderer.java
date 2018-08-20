package ru.mzhurov.seismonitor.ui.fragments.cluster;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class ClusterRenderer extends DefaultClusterRenderer<EarthquakeItem> {

    private IconGenerator iconGenerator;

    public ClusterRenderer(final Context context, final GoogleMap map, final ClusterManager<EarthquakeItem> clusterManager) {
        super(context, map, clusterManager);

        iconGenerator = new IconGenerator(context);
    }

    @Override
    protected void onBeforeClusterItemRendered(final EarthquakeItem item, final MarkerOptions markerOptions) {
        final MarkerOptions itemMarkerOptions = item.getMarkerOptions();

        markerOptions.icon(itemMarkerOptions.getIcon());
        markerOptions.title(itemMarkerOptions.getTitle());
    }

    @Override
    protected void onBeforeClusterRendered(final Cluster<EarthquakeItem> cluster, final MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);

        EarthquakeItem strongestEarthquake = null;

        for (final EarthquakeItem earthquakeItem : cluster.getItems()) {
            if (strongestEarthquake == null) {
                strongestEarthquake = earthquakeItem;
            } else if (strongestEarthquake.getMagnitude() < earthquakeItem.getMagnitude()) {
                strongestEarthquake = earthquakeItem;
            }
        }

        if (strongestEarthquake != null) {
//            iconGenerator.setColor(EarthquakeColorService.getColor());

            markerOptions.icon(strongestEarthquake.getIcon());
        }
    }
}
