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

import opensource.liamm.tallycounter.data.db.database.AppDatabase;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.db.exceptions.InvalidCounterNameException;
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

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters().getValue();
        assert (counters != null);
        assert (!counters.isEmpty());
    }

    @Test
    public void readCounterByIdGetsData() {
        IntegerCounter integerCounter = new IntegerCounter();

        database.integerCounterDao().insertCounter(integerCounter);

        IntegerCounter testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.getId()).getValue();

        assert (testIntegerCounter != null);
        assert (integerCounter.getId() == testIntegerCounter.getId());
    }

    @Test
    public void updateCounterSavesData() throws InvalidCounterNameException {
        IntegerCounter integerCounter = new IntegerCounter();

        database.integerCounterDao().insertCounter(integerCounter);

        IntegerCounter testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.getId()).getValue();
        assert (testIntegerCounter != null);
        assert (testIntegerCounter.getName().equals(StringUtils.EMPTY));

        integerCounter.setName("Test");
        database.integerCounterDao().updateCounter(integerCounter);

        testIntegerCounter = database.integerCounterDao().getCounterById(integerCounter.getId()).getValue();
        assert (testIntegerCounter != null);
        assert (testIntegerCounter.getName().equals("Test"));
    }

    @Test
    public void deleteAllCountersClearsData() {
        IntegerCounter integerCounter = new IntegerCounter();
        database.integerCounterDao().insertCounter(integerCounter);

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters().getValue();
        assert (counters != null);
        assert (!counters.isEmpty());

        database.integerCounterDao().deleteAll();

        counters = database.integerCounterDao().getAllCounters().getValue();
        assert (counters != null);
        assert (counters.isEmpty());
    }

    @Test
    public void deleteCounterClearsData() {
        IntegerCounter integerCounter = new IntegerCounter();
        database.integerCounterDao().insertCounter(integerCounter);

        List<IntegerCounter> counters = database.integerCounterDao().getAllCounters().getValue();
        assert (counters != null);
        assert (!counters.isEmpty());

        database.integerCounterDao().deleteCounter(integerCounter);

        counters = database.integerCounterDao().getAllCounters().getValue();
        assert (counters != null);
        assert (counters.isEmpty());
    }
}
