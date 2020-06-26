package opensource.liamm.tallycounter.data.db.repository;

import androidx.lifecycle.LiveData;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public interface ICounterRepository {

    LiveData<IntegerCounter> getCounterById(int id);

    void insertCounter(IntegerCounter integerCounter);

}
