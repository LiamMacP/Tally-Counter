package opensource.liamm.tallycounter.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.db.repository.CounterRepository;
import opensource.liamm.tallycounter.data.db.repository.ICounterRepository;

public class MainViewModel extends ViewModel {

    private ICounterRepository mCounterRepository;
    private LiveData<IntegerCounter> mCurrentIntegerCounter;
    private MutableLiveData<String> mTitle;

    public MainViewModel(Application application) {
        this.mCounterRepository = CounterRepository.getInstance(application);
        this.mCurrentIntegerCounter = new MutableLiveData<>();
        this.mTitle = new MutableLiveData<>();
    }

    public LiveData<IntegerCounter> getCurrentCounter() {
        return mCurrentIntegerCounter;
    }

    /*public void setCurrentCounter(IntegerCounter integerCounter) {
        this.mCurrentIntegerCounter.postValue(integerCounter);
    }*/

    public void loadCounterById(final long id) {
        this.mCurrentIntegerCounter = this.mCounterRepository.getCounterById(id);
    }

    public void createCounter(IntegerCounter integerCounter) {
        this.mCounterRepository.insertCounter(integerCounter);
        //this.mCurrentIntegerCounter.postValue(integerCounter);
    }

    public void incrementCounter() {
        IntegerCounter integerCounter = mCurrentIntegerCounter.getValue();

        if (integerCounter != null) {
            integerCounter.increment();
            this.mCounterRepository.updateCounter(integerCounter);
        }
    }

    public void setTitle(@NonNull String title) {
        this.mTitle.postValue(title);
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }

}
