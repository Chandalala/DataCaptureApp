package tinashe.chandalala.citgeospat;

import java.util.Date;

public class Data {

    private String imageUrl, status, currentDateTime;
    private double latitude, longitude, altitude;

    public Data(String imageUrl, String status, String currentDateTime, double latitude, double longitude, double altitude) {
        this.imageUrl = imageUrl;
        this.status = status;
        this.currentDateTime = currentDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Data(Data data) {
        this.imageUrl = data.getImageUrl();
        this.status = data.getStatus();
        this.currentDateTime = data.getCurrentDateTime();
        this.latitude = data.getLatitude();
        this.longitude = data.getLongitude();
        this.altitude = data.getAltitude();
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
