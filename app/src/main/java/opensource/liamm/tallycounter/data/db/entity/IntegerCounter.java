package opensource.liamm.tallycounter.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import opensource.liamm.tallycounter.model.Counter;
import opensource.liamm.tallycounter.utils.StringUtils;

@Entity(tableName = "counters")
public class IntegerCounter implements Counter<Integer> {

    private static final String DEFAULT_NAME = "Counter";

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(defaultValue = DEFAULT_NAME)
    @NonNull
    private String name;

    @ColumnInfo(defaultValue = "0")
    @NonNull
    private Integer value;

    @Ignore
    private boolean emptyName;

    public IntegerCounter() {
        this.name = DEFAULT_NAME;
        this.emptyName = false;
        this.value = 0;
    }

    @Ignore
    public IntegerCounter(Long id, @NonNull String name, @NonNull Integer value) {
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
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(@NonNull String name) {
        this.emptyName = name.equals(StringUtils.EMPTY);

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

    public boolean isEmptyName() {
        return emptyName;
    }
}
