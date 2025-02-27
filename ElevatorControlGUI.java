import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElevatorControlGUI {
//    private JFrame frame;
//    private JLabel display;
//    private DefaultTableModel tableModel;
//    private int currentFloor = 1;
//    private Timer timer;
//    private int targetFloor;
//    private Card card;
//
//    public ElevatorControlGUI(Card card) {
//        this.card = card;
//        frame = new JFrame("Elevator Control Panel");
//        frame.setSize(500, 500);
//        frame.setLayout(new BorderLayout());
//
//        display = new JLabel("Floor: " + currentFloor, SwingConstants.CENTER);
//        display.setFont(new Font("Arial", Font.BOLD, 24));
//
//        tableModel = new DefaultTableModel(new String[]{"Date", "Time", "Action"}, 0);
//        JTable logTable = new JTable(tableModel);
//        JScrollPane logScrollPane = new JScrollPane(logTable);
//
//        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
//        for (int floor = 1; floor <= 5; floor++) {
//            JButton floorButton = new JButton("Floor " + floor);
//            floorButton.setFont(new Font("Arial", Font.BOLD, 16));
//            if (card.getFloorAccess().contains(floor)) {
//                int target = floor;
//                floorButton.addActionListener(e -> moveElevator(target));
//            } else {
//                floorButton.setEnabled(false);
//            }
//            buttonPanel.add(floorButton);
//        }
//
//        JButton btnExit = new JButton("Exit");
//        btnExit.addActionListener(e -> frame.dispose());
//
//        frame.add(display, BorderLayout.NORTH);
//        frame.add(buttonPanel, BorderLayout.CENTER);
//        frame.add(btnExit, BorderLayout.SOUTH);
//        frame.add(logScrollPane, BorderLayout.EAST);
//        frame.setVisible(true);
//    }
//
//    private void moveElevator(int floor) {
//        targetFloor = floor;
//        logAction("Elevator moving to Floor " + floor);
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (currentFloor < targetFloor) currentFloor++;
//                else if (currentFloor > targetFloor) currentFloor--;
//                display.setText("Floor: " + currentFloor);
//                if (currentFloor == targetFloor) {
//                    timer.stop();
//                    logAction("Arrived at Floor " + targetFloor);
//                }
//            }
//        });
//        timer.start();
//    }
//
//    private void logAction(String action) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        String date = sdf.format(new Date()).split(" ")[0];
//        String time = sdf.format(new Date()).split(" ")[1];
//        tableModel.addRow(new Object[]{date, time, action});
//    }
    private JFrame frame;
    private JLabel display;
    private DefaultTableModel tableModel;
    private int currentFloor = 1;
    private Timer timer;
    //private int targetFloor;
    private BaseCard card;
    private AccessFacade accessFacade;

    public ElevatorControlGUI(BaseCard card, AccessFacade accessFacade) {
        this.card = card;
        this.accessFacade = accessFacade;

        frame = new JFrame("Elevator Control Panel");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        display = new JLabel("Floor: " + currentFloor, SwingConstants.CENTER);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        tableModel = new DefaultTableModel(new String[]{"Date", "Time", "Action"}, 0);
        JTable logTable = new JTable(tableModel);
        JScrollPane logScrollPane = new JScrollPane(logTable);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        for (int floor = 1; floor <= 5; floor++) {
            //int finalFloor = floor;
            JButton floorButton = new JButton("Floor " + floor);
            floorButton.setFont(new Font("Arial", Font.BOLD, 16));

            //String roomNumber = card.getRoomNumber();// ดึงหมายเลขห้องจากบัตร
            // ตรวจสอบสิทธิ์ก่อนเปิดใช้งานปุ่ม
            if (accessFacade.canAccess(card.getCardId(), floor, getRoomIfAvailable(card))) {
                int target = floor;
                floorButton.addActionListener(e -> moveElevator(target));
            } else {
                floorButton.setEnabled(false);
            }

            buttonPanel.add(floorButton);
        }

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> frame.dispose());

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(btnExit, BorderLayout.SOUTH);
        frame.add(logScrollPane, BorderLayout.EAST);
        frame.setVisible(true);
    }

    private void moveElevator(int floor) {
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000, new ActionListener() {
            private int targetFloor = floor;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFloor < targetFloor) currentFloor++;
                else if (currentFloor > targetFloor) currentFloor--;

                display.setText("Floor: " + currentFloor);

                if (currentFloor == targetFloor) {
                    timer.stop();
                    logAction("Arrived at Floor " + targetFloor);
                }
            }
        });

        logAction("Elevator moving to Floor " + floor);
        timer.start();

    }
    private void logAction(String action) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = sdf.format(new Date()).split(" ")[0];
        String time = sdf.format(new Date()).split(" ")[1];
        tableModel.addRow(new Object[]{date, time, action});
    }

    private String getRoomIfAvailable(BaseCard card) {
        if (card instanceof RoomCard) {
            RoomCard roomCard = (RoomCard) card;
            return roomCard.getRoomAccess().isEmpty() ? "" : roomCard.getRoomAccess().get(0);
        }
        return "";
    }

}