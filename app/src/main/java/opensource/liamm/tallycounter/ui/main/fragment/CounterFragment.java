package opensource.liamm.tallycounter.ui.main.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import opensource.liamm.tallycounter.Application;
import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModelFactory;

public class CounterFragment extends Fragment {

    public static final String TAG = CounterFragment.class.getSimpleName();

    private MainViewModel mViewModel;
    private IntegerCounter mIntegerCounter;

    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this, MainViewModelFactory.getInstance(Application.getInstance())).get(MainViewModel.class);
        mViewModel.getCounter().observe(getViewLifecycleOwner(), new CounterObserver());

        //TODO: Get Id from Preferences
        mViewModel.loadCounter(1);
    }

    private class CounterObserver implements Observer<IntegerCounter> {

        @Override
        public void onChanged(IntegerCounter integerCounter) {
            mIntegerCounter = integerCounter;

            mViewModel.setTitle(mIntegerCounter.getName() + "ET");
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


    }
}
