import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLogger {
    public static void log(String message) {
        // Log to console; you could extend this to write to a file
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("AUDIT " + timestamp + " - " + message);
    }
}
