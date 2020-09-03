package tinashe.chandalala.citgeospat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MapStyleOptions;

public class MapController {

    private MapView googleMapView;
    private Bundle savedInstanceState;
    private Activity activity;

    public MapController(Activity activity, MapView googleMapView, Bundle savedInstanceState) {
        this.googleMapView = googleMapView;
        this.savedInstanceState = savedInstanceState;
        this.activity = activity;
    }
    

    public void setGoogleMapView(){
        googleMapView.setCameraDistance(15);
        googleMapView.onCreate(savedInstanceState);
        googleMapView.onResume();

        try {
            MapsInitializer.initialize(activity.getApplicationContext());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLocationUI(GoogleMap googleMap) {
        if (googleMap == null) {
            return;
        }
        try {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.style_json));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }




}
