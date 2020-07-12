package opensource.liamm.tallycounter.utils;

import androidx.annotation.NonNull;
import androidx.databinding.InverseMethod;

public class IntegerConverter {

    @InverseMethod("convertIntToString")
    public static int convertStringToInt(@NonNull final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String convertIntToString(final int value) {
        return String.valueOf(value);
    }

}
