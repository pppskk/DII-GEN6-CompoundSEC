import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeEncryp {
    // A simple time-based encryption simulation:
    public static String encrypt(String input) {
        // For demonstration, reverse the input and append current time (HHmmss)
        String reversed = new StringBuilder(input).reverse().toString();
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        return reversed + timestamp;
    }
}
