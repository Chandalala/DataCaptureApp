package tinashe.chandalala.citgeospat;

import androidx.lifecycle.ViewModel;

public class DataCaptureViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public void setGeospatialData(Data data){
        new RemoteData().setGeospatialData(data);
    }
}
