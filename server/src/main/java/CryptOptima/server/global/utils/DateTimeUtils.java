package CryptOptima.server.global.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDateTime parseLocalDateTime(String str, String type) {
        DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = (str == null) ? null : LocalDateTime.parse(str + "T00:00", df);
        return getLocalDateTime(localDateTime, type);
    }

    private static LocalDateTime getLocalDateTime(LocalDateTime localDateTime, String type) {
        if (type.equals("start")) {
            return localDateTime == null ? LocalDateTime.now().minusDays(10).with(LocalTime.MIN) : localDateTime.with(LocalTime.MIN);
        }
        return localDateTime == null ? LocalDateTime.now().with(LocalTime.MAX) : localDateTime.with(LocalTime.MAX);
    }
}