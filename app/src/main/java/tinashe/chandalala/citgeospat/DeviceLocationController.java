package tinashe.chandalala.citgeospat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeviceLocationController {

    private Activity activity;
    private LocationRequest locationRequest;
    private Marker marker;

    public DeviceLocationController(Activity activity) {
        this.activity = activity;
    }

    public aa getDeviceLocation(GoogleMap googleMap) {
        if (googleMap == null) {
            return null;
        }

        buildLocationRequest();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);

        try {

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, getPendingIntent());
            LatLng latLng;
            double altitude;
            aa a;

            if (UserLocationReciever.location != null){

                latLng = new LatLng(UserLocationReciever.location.getLatitude(), UserLocationReciever.location.getLongitude());
                altitude = UserLocationReciever.location.getAltitude();
                a = new aa(latLng, altitude);


                Marker user_current_location_marker = googleMap.addMarker
                        (new MarkerOptions()
                                .title("You are here")
                                .position(latLng)
                        );

                if (user_current_location_marker != null) {
                    user_current_location_marker.remove();
                }

                if (Constants.startStatus){
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }

                return a;


            }
            else {
                Log.d("msg", "Current location is null. Using defaults.");
                googleMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-33, 151), 15));
                Toast.makeText(activity,"Enable gps and click the my location button to show your location",Toast.LENGTH_LONG).show();
            }
        }
        catch (SecurityException e)  {
            Log.d("msg", "getDeviceLocation: "+e.getMessage());
        }

        return null;
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(activity, UserLocationReciever.class);
        intent.setAction(UserLocationReciever.USER_ACTION);
        return PendingIntent.getBroadcast(activity,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest()
                .setSmallestDisplacement(100)
                .setFastestInterval(300_000)
                .setInterval(500_000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }
}
