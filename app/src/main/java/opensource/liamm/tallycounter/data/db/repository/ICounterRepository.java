package opensource.liamm.tallycounter.data.db.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public interface ICounterRepository {

    Single<Long> insertCounter(IntegerCounter integerCounter);

    Flowable<List<IntegerCounter>> getAllCounters();

    Flowable<IntegerCounter> getCounterById(final long id);

    Maybe<IntegerCounter> getFirstCounter();

    Maybe<IntegerCounter> checkIfCounterExists(long id);

    void updateCounter(IntegerCounter integerCounter);

    void deleteCounter(IntegerCounter integerCounter);

    void deleteAllCounters();

}
