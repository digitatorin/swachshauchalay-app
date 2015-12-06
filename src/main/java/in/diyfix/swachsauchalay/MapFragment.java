package in.diyfix.swachsauchalay;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;

import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;

import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.bonuspack.overlays.Marker;

public class MapFragment extends Fragment implements LocationListener {
    private View mFragmentView;
    private LocationManager mLocationManager;
    private static final long GPS_UPDATE_FREQUENCY = 60 * 1000 * 5;
    private static final float GPS_UPDATE_DISTANCE = 50.0f;
    private MapView mMapView;
    private Location mCurrentLocation;
    private Marker mUserMarker;
    private static final String TAG = "SwachShauchalayMapFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

        mFragmentView = inflater.inflate(R.layout.map_layout, container, false);

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "Checking for providers");
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPS_UPDATE_FREQUENCY, GPS_UPDATE_DISTANCE, this);
            Log.d(TAG, "Requesting updates from GPS_PROVIDER");
            mCurrentLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } else {
            if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, GPS_UPDATE_FREQUENCY, GPS_UPDATE_DISTANCE, this);
                Log.d(TAG, "Requesting updates from NETWORK_PROVIDER");
                mCurrentLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } else {
                Log.d(TAG, "Unable to receive updates from any provider");
            }
        }

        Log.d(TAG, "Current location:" + mCurrentLocation);

        mMapView = (MapView) mFragmentView.findViewById(R.id.map);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        IMapController mapController = mMapView.getController();
        mapController.setZoom(9);

        if (mCurrentLocation == null) {
            //hardcoding a loc in the middle of Bangalore
            mCurrentLocation = new Location(LocationManager.GPS_PROVIDER);
            mCurrentLocation.setLatitude(12.976844);
            mCurrentLocation.setLongitude(77.599614);
        }

        GeoPoint here = new GeoPoint(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        mUserMarker = new Marker(mMapView);
        mUserMarker.setPosition(here);
        mUserMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mUserMarker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.toilet, null));
        mUserMarker.setTitle("Start");
        mMapView.getOverlays().add(mUserMarker);
        mapController.setCenter(here);

        return mFragmentView;
    }

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint here = new GeoPoint(location.getLatitude(), location.getLongitude());
        mMapView.getOverlays().remove(mUserMarker);
        mUserMarker.setPosition(here);
        mMapView.getOverlays().add(mUserMarker);
        mMapView.invalidate();
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
