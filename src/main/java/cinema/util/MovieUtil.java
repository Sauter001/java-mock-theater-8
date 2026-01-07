package cinema.util;

import java.text.DecimalFormat;

public final class MovieUtil {
    private MovieUtil() {
        throw new AssertionError("util");
    }

    public static String createMovieId(int movieCode) {
        DecimalFormat codeFormat = new DecimalFormat("000");
        final String codePrefix = "MV";
        return codePrefix + codeFormat.format(movieCode);
    }
}
