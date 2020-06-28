package opensource.liamm.tallycounter.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

@Dao
public interface IntegerCounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCounter(IntegerCounter integerCounters);

    @Query("SELECT * FROM counters")
    LiveData<List<IntegerCounter>> getAllCounters();

    @Query("SELECT * FROM counters WHERE id = :counterId")
    LiveData<IntegerCounter> getCounterById(final long counterId);

    @Update
    void updateCounter(IntegerCounter integerCounters);

    @Delete
    void deleteCounter(IntegerCounter integerCounters);

    @Query("DELETE FROM counters")
    void deleteAll();
}
