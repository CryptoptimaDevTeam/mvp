package CryptOptima.server.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDateTime parseLocalDateTime(String str) {
        DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return str == null ? null : LocalDateTime.parse(str+"T00:00", df);
    }
}