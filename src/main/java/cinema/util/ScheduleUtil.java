package cinema.util;

import java.text.DecimalFormat;

public final class ScheduleUtil {
    private ScheduleUtil() {
        throw new AssertionError("util");
    }

    public static String createScheduleId(int movieCode) {
        DecimalFormat codeFormat = new DecimalFormat("000");
        final String codePrefix = "SC";
        return codePrefix + codeFormat.format(movieCode);
    }
}
