import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class ElevatorController {
    private JLabel display;
    private int currentFloor = 1;
    private Timer timer;
    private int targetFloor;

    public ElevatorController(JLabel display) {
        this.display = display;
    }

    public void moveElevator(int floor, DefaultTableModel tableModel) {
        targetFloor = floor;
        ElevatorLogger.logAction(tableModel, "Elevator moving to Floor " + floor);

        timer = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFloor < targetFloor) {
                    currentFloor++;
                } else if (currentFloor > targetFloor) {
                    currentFloor--;
                }

                display.setText("Floor: " + currentFloor);

                if (currentFloor == targetFloor) {
                    timer.stop();
                    ElevatorLogger.logAction(tableModel, "Arrived at Floor " + targetFloor);
                    JOptionPane.showMessageDialog(null, "✅ Arrived at Floor " + targetFloor + "!");
                }
            }
        });
        timer.start();
    }
}
