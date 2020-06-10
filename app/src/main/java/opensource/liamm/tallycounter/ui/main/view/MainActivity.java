package opensource.liamm.tallycounter.ui.main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.ui.main.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
