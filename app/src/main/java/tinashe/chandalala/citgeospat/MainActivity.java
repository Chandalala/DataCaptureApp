package tinashe.chandalala.citgeospat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager=getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new DataCaptureFragment()).commit();
        }

    }
}
