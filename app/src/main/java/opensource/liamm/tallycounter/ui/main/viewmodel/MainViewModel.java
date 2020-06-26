package opensource.liamm.tallycounter.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.db.repository.CounterRepository;
import opensource.liamm.tallycounter.data.db.repository.ICounterRepository;

public class MainViewModel extends ViewModel {


    private MutableLiveData<IntegerCounter> mIntegerCounter;
    private ICounterRepository mCounterRepository;
    private MutableLiveData<String> mTitle;

    public MainViewModel(Application application) {
        this.mIntegerCounter = new MutableLiveData<>();
        this.mCounterRepository = CounterRepository.getInstance(application);
        this.mTitle = new MutableLiveData<>();
    }

    public LiveData<IntegerCounter> getCounter() {
        return mIntegerCounter;
    }

    public void loadCounter(int id) {
        IntegerCounter integerCounter = this.mCounterRepository.getCounterById(id).getValue();

        if (integerCounter == null) {
            integerCounter = new IntegerCounter();

            mIntegerCounter.postValue(integerCounter);
            mCounterRepository.insertCounter(integerCounter);
        } else {
            mIntegerCounter.postValue(integerCounter);
        }
    }

    public void setTitle(@NonNull String title) {
        this.mTitle.postValue(title);
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }

}
