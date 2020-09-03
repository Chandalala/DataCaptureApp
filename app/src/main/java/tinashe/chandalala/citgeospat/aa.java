package tinashe.chandalala.citgeospat;


import com.google.android.gms.maps.model.LatLng;

public class aa {

    private double altitude;
    private LatLng latLng;

    public aa(LatLng latLng, double altitude) {
        this.latLng = latLng;
        this.altitude = altitude;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
