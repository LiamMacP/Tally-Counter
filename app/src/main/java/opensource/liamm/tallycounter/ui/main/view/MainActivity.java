package opensource.liamm.tallycounter.ui.main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import opensource.liamm.tallycounter.Application;
import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.ui.main.fragment.CounterFragment;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainViewModel viewModel = new ViewModelProvider(this, MainViewModelFactory.getInstance(Application.getInstance())).get(MainViewModel.class);
        viewModel.getIntegerCounter().observe(this, new TitleObserver());

        if (savedInstanceState == null) {
            mCurrentFragment = CounterFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mCurrentFragment, CounterFragment.TAG)
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();

        if (mCurrentFragment instanceof CounterFragment) {
            inflater.inflate(R.menu.counter_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mCurrentFragment instanceof CounterFragment) {
            return currentCounterMenuItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean currentCounterMenuItemSelected(@NonNull MenuItem item) {
        CounterFragment fragment = (CounterFragment) mCurrentFragment;

        if (item.getItemId() == R.id.rename_counter_menu_item) {
            fragment.renameCounter();
            return true;
        }

        return true;
    }

    private class TitleObserver implements Observer<IntegerCounter> {
        @Override
        public void onChanged(@NonNull IntegerCounter integerCounter) {
            setTitle(integerCounter.isEmptyName() ? "(Counter Unnamed)" : integerCounter.getName());
        }
    }

}
