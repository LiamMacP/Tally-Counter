package opensource.liamm.tallycounter.ui.main.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import opensource.liamm.tallycounter.data.db.database.AppDatabase;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.db.repository.CounterRepository;
import opensource.liamm.tallycounter.data.db.repository.ICounterRepository;
import opensource.liamm.tallycounter.utils.StringUtils;

public class MainViewModel extends ViewModel {

    private ICounterRepository mCounterRepository;
    private MutableLiveData<IntegerCounter> mCurrentIntegerCounter;
    private MutableLiveData<String> mTitle;

    public MainViewModel(Application application) {
        this.mCounterRepository = CounterRepository.getInstance(application);
        this.mCurrentIntegerCounter = new MutableLiveData<>();
        this.mTitle = new MutableLiveData<>();
    }

    public LiveData<IntegerCounter> getCurrentCounter() {
        return mCurrentIntegerCounter;
    }

    public void loadCounterById(final long id) {
        AppDatabase.databaseReadWriteExecutor.execute(() ->
                this.mCounterRepository.getCounterById(id).subscribe(
                        integerCounterLiveData -> mCurrentIntegerCounter.postValue(integerCounterLiveData),
                        throwable -> Log.d("MainViewModel", "loadCounterById: Could not retrieve value from database."),
                        () -> createCounter(new IntegerCounter()))
                .dispose());
    }

    public void createCounter(IntegerCounter integerCounter) {
        this.mCounterRepository
                .insertCounter(integerCounter)
                .subscribe(() -> mCurrentIntegerCounter.postValue(integerCounter))
                .dispose();
    }

    public void incrementCounter() {
        IntegerCounter integerCounter = mCurrentIntegerCounter.getValue();

        if (integerCounter != null) {
            integerCounter.increment();

            AppDatabase.databaseReadWriteExecutor.execute(() -> this.mCounterRepository
                    .updateCounter(integerCounter)
                    .subscribe(() -> mCurrentIntegerCounter.postValue(integerCounter))
                    .dispose());
        }
    }

    public void decrementCounter() {
        IntegerCounter integerCounter = mCurrentIntegerCounter.getValue();

        if (integerCounter != null) {
            integerCounter.decrement();

            AppDatabase.databaseReadWriteExecutor.execute(() -> this.mCounterRepository
                    .updateCounter(integerCounter)
                    .subscribe(() -> mCurrentIntegerCounter.postValue(integerCounter))
                    .dispose());
        }
    }

    public void setTitle(@NonNull String title) {
        this.mTitle.postValue(title);
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }

}
