package opensource.liamm.tallycounter.ui.main.fragment;

import androidx.databinding.DataBindingUtil;
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

import java.util.EventListener;

import opensource.liamm.tallycounter.Application;
import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.databinding.MainFragmentBinding;
import opensource.liamm.tallycounter.ui.main.eventhandlers.ClickListener;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;
import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModelFactory;

public class CounterFragment extends Fragment {

    public static final String TAG = CounterFragment.class.getSimpleName();

    private MainViewModel mViewModel;
    private MainFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);

        mBinding.setLifecycleOwner(this);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this, MainViewModelFactory.getInstance(Application.getInstance())).get(MainViewModel.class);

        mBinding.setMainViewModel(mViewModel);
        mBinding.setClickhandler(new ClickListener());

        mViewModel.getCurrentCounter().observe(getViewLifecycleOwner(), new CounterObserver());

    }

    private class CounterObserver implements Observer<IntegerCounter> {
        @Override
        public void onChanged(IntegerCounter integerCounter) {
            if (integerCounter == null) { return; }

            mViewModel.setTitle(integerCounter.getName());
        }
    }

}
