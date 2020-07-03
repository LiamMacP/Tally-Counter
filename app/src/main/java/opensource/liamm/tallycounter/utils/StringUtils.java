package opensource.liamm.tallycounter.utils;

public class StringUtils {

    /**
     * Utility class cannot be initialised.
     */
    private StringUtils() { }

    public static final String EMPTY = "";

    /**
     * Returns whether the inputted string is null or empty.
     * @param value string to check
     * @return true if null or empty, otherwise, false
     */
    public static boolean IsNullOrEmpty(final String value) {
        return value == null || value.equals(EMPTY);
    }

}
