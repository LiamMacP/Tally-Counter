package opensource.liamm.tallycounter.data.db.exceptions;

import androidx.annotation.NonNull;

public class InvalidCounterNameException extends Exception {
    public InvalidCounterNameException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
