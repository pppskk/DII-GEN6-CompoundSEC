import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElevatorLogger {
    public static void logAction(DefaultTableModel tableModel, String action) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = sdf.format(new Date()).split(" ")[0];
        String time = sdf.format(new Date()).split(" ")[1];
        tableModel.addRow(new Object[]{date, time, action});
    }
}
