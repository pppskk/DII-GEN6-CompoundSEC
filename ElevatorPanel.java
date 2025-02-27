//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ElevatorPanel {
//    private JFrame elevatorFrame;
//    private JLabel display, statusLabel;
//    private int currentFloor = 1;
//    private Timer timer;
//    private int targetFloor;
//
//    public ElevatorPanel(BaseCard card) {
//        elevatorFrame = new JFrame("🚪 Elevator Control Panel");
//        elevatorFrame.setSize(300, 400);
//        elevatorFrame.setLayout(new BorderLayout());
//
//        // 🔢 หน้าจอแสดงเลขชั้นปัจจุบัน
//        display = new JLabel("Floor: " + currentFloor, SwingConstants.CENTER);
//        display.setFont(new Font("Arial", Font.BOLD, 24));
//        display.setForeground(Color.RED);
//
//        // 🟢 แสดงสถานะลิฟต์
//        statusLabel = new JLabel("Status: Idle", SwingConstants.CENTER);
//        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        elevatorFrame.add(display, BorderLayout.NORTH);
//        elevatorFrame.add(statusLabel, BorderLayout.SOUTH);
//
//        // 🔲 ปุ่มตัวเลขลิฟต์
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        for (int floor : card.getFloorAccess()) {
//            JButton floorButton = new JButton("Floor " + floor);
//            floorButton.setFont(new Font("Arial", Font.BOLD, 16));
//            floorButton.setBackground(Color.LIGHT_GRAY);
//
//            floorButton.addActionListener(e -> startElevatorAnimation(floor));
//
//            buttonPanel.add(floorButton);
//        }
//
//        elevatorFrame.add(buttonPanel, BorderLayout.CENTER);
//        elevatorFrame.setVisible(true);
//    }
//
//    // 🔹 ฟังก์ชันเริ่มการเคลื่อนที่ของลิฟต์แบบแอนิเมชัน
//    private void startElevatorAnimation(int floor) {
//        targetFloor = floor;
//        statusLabel.setText("Status: Moving...");
//
//        // 🔄 สร้าง Timer เพื่อให้เลขชั้นเปลี่ยนไปทีละขั้น
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (currentFloor < targetFloor) {
//                    currentFloor++;
//                } else if (currentFloor > targetFloor) {
//                    currentFloor--;
//                }
//
//                display.setText("Floor: " + currentFloor);
//
//                if (currentFloor == targetFloor) {
//                    timer.stop();
//                    statusLabel.setText("Status: Arrived!");
//                    JOptionPane.showMessageDialog(elevatorFrame, "✅ Arrived at Floor " + targetFloor + "!");
//                }
//            }
//        });
//
//        timer.start();
//    }
//}