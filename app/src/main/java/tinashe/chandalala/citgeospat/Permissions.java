package tinashe.chandalala.citgeospat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class Permissions {

    Activity activity;

    public Permissions(Activity activity) {
        this.activity = activity;
    }

    public void checkGPS_Enabled () {

        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE ) ;

        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager. GPS_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager. NETWORK_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }

        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(activity )
                    .setMessage( "Enable gps to access location services" )
                    .setPositiveButton( "Settings" , new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                    paramDialogInterface.dismiss();
                                    activity.startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                                }
                            })
                    .setNegativeButton( "Cancel" , null )
                    .show() ;
        }
    }
}
