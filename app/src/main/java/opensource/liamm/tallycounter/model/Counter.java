package opensource.liamm.tallycounter.model;

import androidx.annotation.NonNull;

import opensource.liamm.tallycounter.data.db.exceptions.InvalidCounterNameException;

/**
 * Interface for a counter with a specified type.
 * @param <T> the type that the counter should use
 */
public interface Counter<T> {

    /**
     * Gets the unique id of the counter
     * @return the id of the counter
     */
    Long getId();

    /**
     * Sets the unique id of the counter
     */
    void setId(Long id);

    /**
     * Gets the name of the counter
     * @return the name of the counter
     */
    @NonNull
    String getName();

    /**
     * Sets the name of the counter
     * @param name the new name of the counter
     */
    void setName(@NonNull final String name) throws InvalidCounterNameException;

    /**
     * Gets the value of the counter
     * @return the value of the counter
     */
    @NonNull
    T getValue();

    /**
     * Sets the value of the counter
     * @param value the new value for the counter
     */
    void setValue(@NonNull final T value);

    /**
     * Increments the counter
     */
    void increment();

    /**
     * Increments the counter by a specified value
     * @param value the value to add to the counter value
     */
    void incrementBy(@NonNull T value);

    /**
     * Decrements the counter
     */
    void decrement();

    /**
     * Decrement the counter by a specified value
     * @param value the value to remove from the counter value
     */
    void decrementBy(@NonNull T value);

    /**
     * Resets the counter to the default for that type.
     */
    void reset();
}
