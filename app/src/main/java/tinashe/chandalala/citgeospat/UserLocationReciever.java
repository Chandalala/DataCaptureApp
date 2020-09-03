package tinashe.chandalala.citgeospat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.android.gms.location.LocationResult;

public class UserLocationReciever extends BroadcastReceiver {

    public static final String USER_ACTION = "tinashe.chandalala.citgeospat.UPDATE_USER_LOCATION";
    public static Location location;

    public UserLocationReciever() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent != null) {

            final String action = intent.getAction();

            if (action.equals(USER_ACTION)) {

                LocationResult result = LocationResult.extractResult(intent);

                if (result != null) {

                    location = result.getLastLocation();


                    // Set the map's camera position to the current location of the device.

//                        Geofence geofence =new Geofence.Builder()
//                                .setRequestId(geofence_request_id)
//                                .setCircularRegion(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude(),Constants.GEOFENCE_RADIUS_IN_METERS)
//                                .setExpirationDuration(50000)
//                                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
//                                .build();
//
//                        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
//                        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
//                        builder.addGeofence(geofence);
//                        builder.build();


                }

            }
        }
    }

    public Location getLocation(){

        return location;
    }
}
