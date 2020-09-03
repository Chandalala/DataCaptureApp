package tinashe.chandalala.citgeospat;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Constants {
    public static final String DRIVER_INFORMATION = "driver_information";
    public static final String DRIVER_UID_SAVE_KEY = "SaveUid";
    public static final String TOKENS = "Tokens";
    public static final String BUS = "bus";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String BUS_NUMBER = "bus_number";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SPEED = "speed";
    public static final String LOCATION_NAME = "location_name";
    public static final String TIME = "time";
    public static final float GEOFENCE_RADIUS_IN_METERS = 1000 ;
    public static final String BUS_STATUS = "bus_status";
    public static final String BUS_ON_ROAD = "On road";
    public static final String BUS_OFF_ROAD = "Off road";
    public static final String ZERO = "0";
    public static final String USER_UID_SAVE_KEY = "SaveUserUid";
    public static   int count=0;
    public static boolean popBackstack=false;
    public static boolean isRegistered = false;
    public static boolean startTracking = false;
    public static boolean startTrackingMultipleBuses = false;


    public static boolean startStatus=false;
    public static int bus_number;


    public static String getDateFormatted(Date date){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }

    public static Date convertTimeStampToDate(long time){
        return new Date(new Timestamp(time).getTime());
    }

}
