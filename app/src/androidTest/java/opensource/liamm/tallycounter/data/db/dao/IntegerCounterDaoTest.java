package opensource.liamm.tallycounter.data.db.dao;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.BaseTestConsumer;
import io.reactivex.subscribers.TestSubscriber;
import opensource.liamm.tallycounter.data.db.database.AppDatabase;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

@RunWith(AndroidJUnit4.class)
public class IntegerCounterDaoTest {
    private AppDatabase database;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        database =
                Room
                        .inMemoryDatabaseBuilder(context, AppDatabase.class)
                        .allowMainThreadQueries()
                        .build();
    }

    @After
    public void closeDatabase() {
        database.close();
    }

    @Test
    public void insertCounterSavesData() {
        IntegerCounter integerCounter = new IntegerCounter(10L, "Test", 1);

        database
                .integerCounterDao()
                .insertCounter(integerCounter)
                .test()
                .awaitCount(1)
                .assertValue(rowId -> rowId.equals(integerCounter.getId()));
    }

    @Test
    public void readCounterByIdGetsData() {
        IntegerCounter integerCounter = new IntegerCounter();

        Long aLong = database.integerCounterDao().insertCounter(integerCounter).blockingGet();

        TestSubscriber<IntegerCounter> testObserver =
                database.integerCounterDao()
                        .getCounterById(aLong)
                        .test()
                        .awaitCount(1);

        testObserver.assertValue(value -> value.getName().equals(integerCounter.getName()));
    }

    @Test
    public void updateCounterSavesData() {
        IntegerCounter integerCounter = new IntegerCounter(10L, "Test", 1);

        Long rowId = database.integerCounterDao().insertCounter(integerCounter).blockingGet();

        integerCounter.setName("Test Name");

        database.integerCounterDao().updateCounter(integerCounter);

        database.integerCounterDao()
                .getCounterById(rowId)
                .test()
                .awaitCount(1)
                .assertValue(value -> value.getName().equals(integerCounter.getName()));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void deleteAllCountersClearsData() {
        IntegerCounter integerCounter = new IntegerCounter(10L, "Test", 10);

        database.integerCounterDao().insertCounter(integerCounter).blockingGet();

        database.integerCounterDao()
                .getAllCounters()
                .test()
                .awaitCount(1)
                .assertValue(value -> !value.isEmpty())
                .dispose();

        database.integerCounterDao().deleteAllCounters();

        database.integerCounterDao()
                .getAllCounters()
                .test()
                .awaitCount(1, BaseTestConsumer.TestWaitStrategy.SLEEP_1000MS, 5000)
                .assertValue(List::isEmpty);
    }

    @Test
    public void deleteCounterClearsData() {
        IntegerCounter integerCounter = new IntegerCounter();

        Long rowId = database.integerCounterDao().insertCounter(integerCounter).blockingGet();

        integerCounter.setId(rowId);

        database.integerCounterDao().deleteCounter(integerCounter);

        database.integerCounterDao()
                .getCounterById(rowId)
                .test()
                .assertEmpty();
    }
}
