package utils;

/**
 * utilities
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
    public static Date toDate(String text) {
        try {
            return sdf.parse(text);
        } catch (Exception e) {
            return null;
        }
    }
    public static String toDate(Date date) {
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
