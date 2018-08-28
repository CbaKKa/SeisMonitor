package ru.mzhurov.seismonitor.ui.fragments.cluster;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import ru.mzhurov.seismonitor.R;

public class ClusterRenderer extends DefaultClusterRenderer<EarthquakeItem> {

    private final Context context;
    private IconGenerator iconGenerator;

    public ClusterRenderer(final Context context, final GoogleMap map, final ClusterManager<EarthquakeItem> clusterManager) {
        super(context, map, clusterManager);

        this.context = context;
        iconGenerator = new IconGenerator(context);

        iconGenerator.setTextAppearance(android.support.v4.R.style.TextAppearance_Compat_Notification_Title);
    }

    @Override
    protected void onBeforeClusterItemRendered(final EarthquakeItem item, final MarkerOptions markerOptions) {
        final MarkerOptions itemMarkerOptions = item.getMarkerOptions();

        markerOptions.icon(itemMarkerOptions.getIcon());
        markerOptions.title(itemMarkerOptions.getTitle());
    }

    @Override
    protected void onBeforeClusterRendered(final Cluster<EarthquakeItem> cluster, final MarkerOptions markerOptions) {
        EarthquakeItem strongestEarthquake = null;

        for (final EarthquakeItem earthquakeItem : cluster.getItems()) {
            if (strongestEarthquake == null) {
                strongestEarthquake = earthquakeItem;
            } else if (strongestEarthquake.getMagnitude() < earthquakeItem.getMagnitude()) {
                strongestEarthquake = earthquakeItem;
            }
        }

        if (strongestEarthquake != null) {
            final Bitmap icon = createIcon(strongestEarthquake.getMagnitude());

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }
    }

    private Bitmap createIcon(final double magnitude) {
        final int drawableId = R.drawable.shape_circle_extreme_danger;

        iconGenerator.setBackground(ContextCompat.getDrawable(context, drawableId));

        return iconGenerator.makeIcon(String.valueOf(magnitude));
    }
}
