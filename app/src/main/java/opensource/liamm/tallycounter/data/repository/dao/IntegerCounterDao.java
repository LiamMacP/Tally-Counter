package opensource.liamm.tallycounter.data.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import opensource.liamm.tallycounter.data.repository.entity.IntegerCounter;

@Dao
public interface IntegerCounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCounter(IntegerCounter integerCounters);

    @Query("SELECT * FROM counters")
    List<IntegerCounter> getAllCounters();

    @Query("SELECT * FROM counters WHERE rowid = :counterId")
    IntegerCounter getCounterById(int counterId);

    @Update
    void updateCounter(IntegerCounter integerCounters);

    @Delete
    void delete(IntegerCounter... user);

    @Query("DELETE FROM counters")
    void deleteAll();
}
