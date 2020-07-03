package opensource.liamm.tallycounter.data.db.repository;

import android.app.Application;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import opensource.liamm.tallycounter.data.db.database.AppDatabase;
import opensource.liamm.tallycounter.data.db.dao.IntegerCounterDao;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public class CounterRepository implements ICounterRepository {

    private static CounterRepository instance;

    private final IntegerCounterDao mIntegerCounterDao;

    public CounterRepository(Application application) {
        mIntegerCounterDao = AppDatabase.getInstance(application.getApplicationContext()).integerCounterDao();
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
    public Single<Long> insertCounter(IntegerCounter integerCounter) {
        return mIntegerCounterDao.insertCounter(integerCounter);
    }

    @Override
    public Flowable<List<IntegerCounter>> getAllCounters() {
        return mIntegerCounterDao.getAllCounters();
    }

    @Override
    public Flowable<IntegerCounter> getCounterById(long id) {
        return mIntegerCounterDao.getCounterById(id);
    }

    @Override
    public Maybe<IntegerCounter> getFirstCounter() {
        return mIntegerCounterDao.getFirstCounter();
    }

    @Override
    public Maybe<IntegerCounter> checkIfCounterExists(long id) {
        return mIntegerCounterDao.checkIfCounterExists(id);
    }

    @Override
    public void updateCounter(IntegerCounter integerCounter) {
        AppDatabase.databaseReadWriteExecutor.execute(() -> mIntegerCounterDao.updateCounter(integerCounter));
    }

    @Override
    public void deleteCounter(IntegerCounter integerCounter) {
        AppDatabase.databaseReadWriteExecutor.execute(() -> mIntegerCounterDao.deleteCounter(integerCounter));
    }

    @Override
    public void deleteAllCounters() {
        AppDatabase.databaseReadWriteExecutor.execute(() -> mIntegerCounterDao.deleteAllCounters());
    }
}
