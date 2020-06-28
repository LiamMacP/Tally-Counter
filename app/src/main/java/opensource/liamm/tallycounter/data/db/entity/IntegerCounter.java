package opensource.liamm.tallycounter.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import opensource.liamm.tallycounter.data.db.exceptions.InvalidCounterNameException;
import opensource.liamm.tallycounter.model.Counter;

@Entity(tableName = "counters")
public class IntegerCounter implements Counter<Integer> {

    private static final String DEFAULT_NAME = "Counter";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(defaultValue = DEFAULT_NAME)
    @NonNull
    private String name;

    @ColumnInfo(defaultValue = "0")
    @NonNull
    private Integer value;

    public IntegerCounter() {
        this.name = DEFAULT_NAME;
        this.value = 0;
    }

    @Ignore
    public IntegerCounter(int id, @NonNull String name, @NonNull Integer value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public IntegerCounter(IntegerCounter counter) {
        this.id = counter.id;
        this.name = counter.name;
        this.value = counter.value;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(@NonNull String name) throws InvalidCounterNameException {
        if (name.isEmpty()) {
            throw new InvalidCounterNameException("Provided name is not valid");
        }

        this.name = name;
    }

    @NonNull
    @Override
    public Integer getValue() {
        return this.value;
    }


    @Override
    public void setValue(@NonNull Integer value) {
        this.value = value;
    }

    @Override
    public void increment() {
        ++value;
    }


    @Override
    public void incrementBy(@NonNull Integer value) {
        this.value += value;
    }

    @Override
    public void decrement() {
        --this.value;
    }

    @Override
    public void decrementBy(@NonNull Integer value) {
        this.value -= value;
    }

    @Override
    public void reset() {
        this.value = 0;
    }
}
