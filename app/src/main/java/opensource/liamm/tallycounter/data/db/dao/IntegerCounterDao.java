package opensource.liamm.tallycounter.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

@Dao
public interface IntegerCounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertCounter(IntegerCounter integerCounter);

    @Query("SELECT * FROM counters")
    Flowable<List<IntegerCounter>> getAllCounters();

    @Query("SELECT * FROM counters WHERE id = :id")
    Flowable<IntegerCounter> getCounterById(final long id);

    @Query("SELECT * FROM counters WHERE id = :id")
    Maybe<IntegerCounter> checkIfCounterExists(final long id);

    @Query("SELECT * FROM counters LIMIT 1")
    Maybe<IntegerCounter> getFirstCounter();

    @Update
    void updateCounter(IntegerCounter integerCounter);

    @Delete
    void deleteCounter(IntegerCounter integerCounter);

    @Query("DELETE FROM counters")
    void deleteAllCounters();

}
