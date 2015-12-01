package in.diyfix.swachsauchalay;

import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;

import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.bonuspack.overlays.Marker;


public class MapFragment extends Fragment {
    private View mFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

        mFragmentView = inflater.inflate(R.layout.map_layout, container, false);

        MapView map = (MapView) mFragmentView.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        GeoPoint elan = new GeoPoint(12.917907, 77.673657);
        mapController.setZoom(9);
        mapController.setCenter(elan);
        Marker pt = new Marker(map);
        pt.setPosition(elan);
        pt.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        pt.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.toilet, null));
        pt.setTitle("Start");
        map.getOverlays().add(pt);

        return mFragmentView;
    }
}
