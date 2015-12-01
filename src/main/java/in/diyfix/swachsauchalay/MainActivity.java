package in.diyfix.swachsauchalay;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.bonuspack.overlays.Marker;

public class MainActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MapView map = (MapView) findViewById(R.id.map);
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
        pt.setIcon(getResources().getDrawable(R.drawable.toilet));
        pt.setTitle("Start");
        map.getOverlays().add(pt);
    }

    public void navigateToLogin(View view) {
        setContentView(R.layout.login);
    }

    public void navigateToMain(View view) {
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rate_toilet:
                startActivity( new Intent(this, RateToiletActivity.class));
                return true;
            case R.id.find:
                startActivity(new Intent(this, SearchToiletActivity.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
        
        }
        return false;
    }
}
