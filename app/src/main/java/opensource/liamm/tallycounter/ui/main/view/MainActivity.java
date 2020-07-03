package opensource.liamm.tallycounter.ui.main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import opensource.liamm.tallycounter.Application;
import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.ui.main.fragment.CounterFragment;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainViewModel viewModel = new ViewModelProvider(this, MainViewModelFactory.getInstance(Application.getInstance())).get(MainViewModel.class);
        viewModel.getIntegerCounter().observe(this, new TitleObserver());

        if (savedInstanceState == null) {
            CounterFragment counterFragment = CounterFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, counterFragment, CounterFragment.TAG)
                    .commitNow();
        }
    }

    private class TitleObserver implements Observer<IntegerCounter> {
        @Override
        public void onChanged(@NonNull IntegerCounter integerCounter) {
            setTitle(integerCounter.getName());
        }
    }

}
