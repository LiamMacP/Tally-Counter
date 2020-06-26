package opensource.liamm.tallycounter.data.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

    public LiveData<IntegerCounter> getCounterById(int id) {
        return mIntegerCounterDao.getCounterById(id);
    }

    public void insertCounter(IntegerCounter integerCounter) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mIntegerCounterDao.insertCounter(integerCounter);
        });
    }
}
