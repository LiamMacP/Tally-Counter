package opensource.liamm.tallycounter.data.db.dao;

import androidx.lifecycle.LiveData;
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
    void insertCounter(IntegerCounter integerCounters);

    @Query("SELECT * FROM counters")
    LiveData<List<IntegerCounter>> getAllCounters();

    @Query("SELECT * FROM counters WHERE rowid = :counterId")
    LiveData<IntegerCounter> getCounterById(int counterId);

    @Update
    void updateCounter(IntegerCounter integerCounters);

    @Delete
    void deleteCounter(IntegerCounter integerCounters);

    @Query("DELETE FROM counters")
    void deleteAll();
}
