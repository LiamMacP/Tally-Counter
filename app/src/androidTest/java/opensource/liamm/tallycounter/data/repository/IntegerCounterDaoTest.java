package opensource.liamm.tallycounter.data.repository;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import opensource.liamm.tallycounter.data.repository.entity.IntegerCounter;
import opensource.liamm.tallycounter.utils.StringUtils;

@RunWith(AndroidJUnit4.class)
public class IntegerCounterDaoTest {
    private AppDatabase database;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDatabase() {
        database.close();
    }

    @Test
    public void insertCounterSavesData() {
        IntegerCounter integerCounter = new IntegerCounter();

        database.integerCounterDao().insertCounter(integerCounter);

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters();
        assert (!counters.isEmpty());
    }

    @Test
    public void readCounterByIdGetsData() {
        IntegerCounter integerCounter = new IntegerCounter();

        database.integerCounterDao().insertCounter(integerCounter);

        IntegerCounter testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.uid);

        assert (testIntegerCounter != null);
        assert (integerCounter.uid == testIntegerCounter.uid);
    }

    @Test
    public void updateCounterSavesData() {
        IntegerCounter integerCounter = new IntegerCounter();

        database.integerCounterDao().insertCounter(integerCounter);

        IntegerCounter testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.uid);
        assert (testIntegerCounter != null);
        assert (testIntegerCounter.name.equals(StringUtils.EMPTY));

        integerCounter.name = "Test";
        database.integerCounterDao().updateCounter(integerCounter);

        testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.uid);
        assert (testIntegerCounter != null);
        assert (testIntegerCounter.name.equals("Test"));
    }

    @Test
    public void deleteAllCountersClearsData() {
        IntegerCounter integerCounter = new IntegerCounter();
        database.integerCounterDao().insertCounter(integerCounter);

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters();
        assert (!counters.isEmpty());

        database.integerCounterDao().deleteAll();

        counters = database.integerCounterDao().getAllCounters();
        assert (counters.isEmpty());
    }

    @Test
    public void deleteCounterClearsData() {
        IntegerCounter integerCounter = new IntegerCounter();
        database.integerCounterDao().insertCounter(integerCounter);

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters();
        assert (!counters.isEmpty());

        database.integerCounterDao().delete(integerCounter);

        counters = database.integerCounterDao().getAllCounters();
        assert (counters.isEmpty());
    }
}
