package opensource.liamm.tallycounter.data.model;

import androidx.annotation.NonNull;

import opensource.liamm.tallycounter.data.InvalidCounterNameException;
import opensource.liamm.tallycounter.utils.StringUtils;

public class IntegerCounter implements Counter<Integer> {

    private int uid;

    @NonNull
    private String name;

    @NonNull
    private Integer value;

    public IntegerCounter() {
        this.uid = 0;
        this.name = StringUtils.EMPTY;
        this.value = 0;
    }

    public IntegerCounter(@NonNull final String name) throws InvalidCounterNameException {
        if (name.isEmpty()) {
            throw new InvalidCounterNameException("Provided name is not valid");
        }

        this.uid = 0;
        this.name = name;
        this.value = 0;
    }

    public IntegerCounter(@NonNull final String name, @NonNull final Integer value) throws InvalidCounterNameException {
        if (name.isEmpty()) {
            throw new InvalidCounterNameException("Provided name is not valid");
        }

        this.uid = 0;
        this.name = name;
        this.value = value;
    }

    @Override
    public int getId() {
        return this.uid;
    }

    @Override
    public void setId(int id) {
        this.uid = id;
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
