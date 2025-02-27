import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ElevatorFacade {
    private ElevatorController elevatorController;
    private DefaultTableModel tableModel;
    private Card card;

    public ElevatorFacade(ElevatorController controller, DefaultTableModel tableModel, Card card) {
        this.elevatorController = controller;
        this.tableModel = tableModel;
        this.card = card;
    }

    public void requestElevator(int floor) {
        if (card.getFloorAccess().contains(floor)) {
            ElevatorLogger.logAction(tableModel, "Access granted for Floor " + floor);
            elevatorController.moveElevator(floor, tableModel);
        } else {
            ElevatorLogger.logAction(tableModel, "Access denied for Floor " + floor);
            JOptionPane.showMessageDialog(null, "⚠️ Access Denied! You cannot go to Floor " + floor);
        }
    }
}
