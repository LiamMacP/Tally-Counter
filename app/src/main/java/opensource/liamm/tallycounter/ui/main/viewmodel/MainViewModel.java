package opensource.liamm.tallycounter.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.db.repository.CounterRepository;
import opensource.liamm.tallycounter.data.db.repository.ICounterRepository;

public class MainViewModel extends ViewModel {

    private final ICounterRepository mCounterRepository;

    private final MutableLiveData<IntegerCounter> mIntegerCounter;

    public MainViewModel(Application application) {
        this.mCounterRepository = CounterRepository.getInstance(application);

        this.mIntegerCounter = new MutableLiveData<>();

    }

    public Single<Long> insertCounter(IntegerCounter integerCounter) {
        return mCounterRepository.insertCounter(integerCounter);
    }

    public Flowable<List<IntegerCounter>> getAllCounters() {
        return mCounterRepository.getAllCounters();
    }

    public Flowable<IntegerCounter> getCounterById(long id) {
        return mCounterRepository.getCounterById(id);
    }

    public Maybe<IntegerCounter> checkIfCounterExists(long id) {
        return mCounterRepository.checkIfCounterExists(id);
    }

    public Maybe<IntegerCounter> getFirstCounter() {
        return mCounterRepository.getFirstCounter();
    }

    public void updateCounter(IntegerCounter integerCounter) {
        mCounterRepository.updateCounter(integerCounter);
    }

    public void deleteCounter(IntegerCounter integerCounter) {
        mCounterRepository.deleteCounter(integerCounter);
    }

    public void setIntegerCounter(@NonNull IntegerCounter counter) {
        this.mIntegerCounter.postValue(counter);
    }

    public LiveData<IntegerCounter> getIntegerCounter() {
        return mIntegerCounter;
    }

    public Long getSelectedCounterId() {
        return 1L;
    }

    public void incrementCounter() {
        IntegerCounter integerCounter = mIntegerCounter.getValue();

        if (integerCounter != null) {
            integerCounter.increment();
            updateCounter(integerCounter);
        }
    }

    public void decrementCounter() {
        IntegerCounter integerCounter = mIntegerCounter.getValue();

        if (integerCounter != null) {
            integerCounter.decrement();
            updateCounter(integerCounter);
        }
    }
}
