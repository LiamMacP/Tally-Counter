package opensource.liamm.tallycounter.data.db.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public interface ICounterRepository {

    Maybe<IntegerCounter> getCounterById(final long id);

    LiveData<List<IntegerCounter>> getAllCounters();

    Completable insertCounter(IntegerCounter integerCounter);

    Completable updateCounter(IntegerCounter integerCounter);

}
