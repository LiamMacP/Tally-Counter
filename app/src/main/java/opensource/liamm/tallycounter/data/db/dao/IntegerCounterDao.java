package opensource.liamm.tallycounter.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

@Dao
public interface IntegerCounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCounter(IntegerCounter integerCounters);

    @Query("SELECT * FROM counters")
    LiveData<List<IntegerCounter>> getAllCounters();

    @Query("SELECT * FROM counters WHERE id = :counterId")
    Maybe<IntegerCounter> getCounterById(final long counterId);

    @Update
    Completable updateCounter(IntegerCounter integerCounters);

    @Delete
    void deleteCounter(IntegerCounter integerCounters);

    @Query("DELETE FROM counters")
    void deleteAll();
}
