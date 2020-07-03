package opensource.liamm.tallycounter.ui.main.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    private final CompositeDisposable mCompositeDisposable;

    public static CounterFragment newInstance() {
        return new CounterFragment();
    }


    private CounterFragment() {
        mCompositeDisposable = new CompositeDisposable();
    }

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

        Long value = mViewModel.getSelectedCounterId();
        if (value != null) {
            checkIfCounterExists(value)
                    .subscribe(new DisposableMaybeObserver<IntegerCounter>() {
                        @Override
                        public void onSuccess(IntegerCounter integerCounter) {
                            loadSelectedCounter(value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "CheckIfCounterExists: error when trying to retrieve counter.", e);
                        }

                        @Override
                        public void onComplete() {
                            getFirstCounterOrCreate();
                        }
                    });
        } else {
            getFirstCounterOrCreate();
        }

        mBinding.setClickhandler(new ClickListener());
        mBinding.setMainViewModel(mViewModel);
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
        super.onDestroy();
    }

    private Maybe<IntegerCounter> checkIfCounterExists(long id) {
        return this.mViewModel.checkIfCounterExists(id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());

    }

    private void getFirstCounterOrCreate() {
        mViewModel.getFirstCounter()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<IntegerCounter>() {
                    @Override
                    public void onSuccess(IntegerCounter integerCounter) {
                        loadSelectedCounter(integerCounter.getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "checkIfAnyCountersExist: error when trying to retrieve counter.", e);
                    }

                    @Override
                    public void onComplete() {
                        insertNewCounter();
                    }
                });
    }


    private void insertNewCounter() {
        new InsertCounter(this).execute(new IntegerCounter());
    }


    private void loadSelectedCounter(Long id) {
        if (mCompositeDisposable != null) {
            if (!mCompositeDisposable.isDisposed()) {
                mCompositeDisposable.clear();
            }

            mCompositeDisposable.add(this.mViewModel.getCounterById(id)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerCounter -> mViewModel.setIntegerCounter(integerCounter)));
        }
    }

    private static class InsertCounter extends AsyncTask<IntegerCounter, Void, Void> {

        private final WeakReference<CounterFragment> counterFragmentRef;

        public InsertCounter(CounterFragment counterFragment) {
            this.counterFragmentRef = new WeakReference<>(counterFragment);
        }

        @Override
        protected Void doInBackground(IntegerCounter... integerCounters) {
            final IntegerCounter integerCounter = integerCounters[0];
            final CounterFragment counterFragment = counterFragmentRef.get();

            if (counterFragment == null) return null;

            counterFragment.mViewModel
                    .insertCounter(integerCounter)
                    .subscribe((rowId, throwable) -> {
                        if (rowId == null || throwable != null) {
                            Log.e(TAG, "InsertTask: Could not insert into database.", throwable);
                            return;
                        }

                        counterFragment.loadSelectedCounter(rowId);
                    }).dispose();

            return null;
        }
    }

}
