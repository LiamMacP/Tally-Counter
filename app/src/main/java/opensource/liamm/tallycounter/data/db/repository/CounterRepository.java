package opensource.liamm.tallycounter.data.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import opensource.liamm.tallycounter.data.db.database.AppDatabase;
import opensource.liamm.tallycounter.data.db.dao.IntegerCounterDao;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public class CounterRepository implements ICounterRepository {

    private static CounterRepository instance;

    private IntegerCounterDao mIntegerCounterDao;

    public CounterRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mIntegerCounterDao = db.integerCounterDao();
    }

    public static CounterRepository getInstance(Application application) {
        if(instance == null) {
            synchronized (CounterRepository.class) {
                if(instance == null) {
                    instance = new CounterRepository(application);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<IntegerCounter> getCounterById(final long id) {
        return mIntegerCounterDao.getCounterById(id);
    }

    @Override
    public LiveData<List<IntegerCounter>> getAllCounters() {
        return mIntegerCounterDao.getAllCounters();
    }

    @Override
    public void insertCounter(IntegerCounter integerCounter) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mIntegerCounterDao.insertCounter(integerCounter);
        });
    }

    @Override
    public void updateCounter(IntegerCounter integerCounter) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mIntegerCounterDao.updateCounter(integerCounter);
        });
    }
}
