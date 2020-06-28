package opensource.liamm.tallycounter.data.db.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public interface ICounterRepository {

    LiveData<IntegerCounter> getCounterById(final long id);

    LiveData<List<IntegerCounter>> getAllCounters();

    void insertCounter(IntegerCounter integerCounter);

    void updateCounter(IntegerCounter integerCounter);

}
