package tinashe.chandalala.citgeospat;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.ornach.nobobutton.NoboButton;

import java.io.File;
import java.util.Date;

public class DataCaptureFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private DataCaptureViewModel mViewModel;
    private MapController mapController;
    private DeviceLocationController deviceLocationController;
    private GoogleMap googleMap;


    private MapView googleMapView;
    private NoboButton btnCapture;
    private Data data;
    private aa a;
    private             String imageUrl;



    public static DataCaptureFragment newInstance() {
        return new DataCaptureFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_capture_fragment, container, false);

        googleMapView = view.findViewById(R.id.mapView);
        btnCapture = view.findViewById(R.id.btnCapture);

        btnCapture.setOnClickListener(this);

        mapController = new MapController(getActivity(), googleMapView,savedInstanceState);

        Permissions permissions = new Permissions(getActivity());
        deviceLocationController = new DeviceLocationController(getActivity());

        permissions.checkGPS_Enabled();

        mapController.setGoogleMapView();

        googleMapView.getMapAsync(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DataCaptureViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        mapController.updateLocationUI(googleMap);

                        a=deviceLocationController.getDeviceLocation(googleMap);

/*

                        if (neworkStatus.isConnected()) {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                deviceLocationController.getDeviceLocation(googleMap);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Network unAvailable", Toast.LENGTH_SHORT).show();
                        }
*/

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(), "You must accept permissions", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {


            //get a single image only
            com.esafirm.imagepicker.model.Image image = ImagePicker.getFirstImageOrNull(data);

            File file = new File(image.getPath());

            String filename = a.getLatLng().latitude+"-"+a.getLatLng().longitude;
            imageUrl = file.getAbsolutePath();
            Toast.makeText(getActivity(), imageUrl, Toast.LENGTH_SHORT).show();





        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpload:
                Date date = new Date();
                if (a != null && !imageUrl.equals("")){
                    data = new Data(imageUrl , "status", Constants.getDateFormatted(date), Double.valueOf(a.getLatLng().latitude), Double.valueOf(a.getLatLng().longitude), a.getAltitude());
                    mViewModel.setGeospatialData(data);

                }
                break;
            case R.id.btnCapture:
                //Show dialog
                ImagePicker.create(getActivity())
                        .returnMode(ReturnMode.CAMERA_ONLY) // set whether pick and / or camera action should return immediate result or not.
                        .folderMode(true) // folder mode (false by default)
                        .toolbarFolderTitle("Folder") // folder selection title
                        .toolbarImageTitle("Tap to select") // image selection title
                        .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                        .includeVideo(false) // Show video on image picker
                        .single() // single mode
                        //.multi() // multi mode (default mode)
                        .limit(1) // max images can be selected (99 by default)
                        .showCamera(true) // show camera or not (true by default)
                        .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                        //.origin(images) // original selected images, used in multi mode
                        //.exclude(images) // exclude anything that in image.getPath()
                        //.excludeFiles(files) // same as exclude but using ArrayList<File>
                        .theme(R.style.AppTheme) // must inherit ef_BaseTheme. please refer to sample
                        .enableLog(true)
                        .start(); // start image picker activity with request code
                break;

        }





    }
}
