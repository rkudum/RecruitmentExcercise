package ufo.util;

import java.time.DateTimeException;
import java.time.Month;
import java.time.Year;

public class DateUtil {

    public static boolean isValidYearMonth(int year, int month) {
        try {
            Year.of(year);
            Month.of(month);
        }
        catch(DateTimeException e) {
            return false;
        }

        return true;
    }
}
