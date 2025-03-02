import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerElevatorGUI {
    private JFrame frame;
    private JComboBox<Integer> floorComboBox;
    private JButton btnGo;
    private JPanel roomPanel;
    private BaseCard card;           // Assume this is a RoomCard instance
    private RoomManager roomManager;
    private AccessFacade accessFacade;

    public CustomerElevatorGUI(BaseCard card, RoomManager roomManager, AccessFacade accessFacade) {
        this.card = card;
        this.roomManager = roomManager;
        this.accessFacade = accessFacade;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Customer Elevator Access");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for floor selection at the top.
        JPanel floorPanel = new JPanel(new FlowLayout());
        floorPanel.add(new JLabel("Select Floor:"));

        // Use allowed floors from the card for the combo box.
        List<Integer> allowedFloors = card.getFloorAccess();
        // If you wish to allow all floors regardless, adjust accordingly.
        floorComboBox = new JComboBox<>(allowedFloors.toArray(new Integer[0]));
        floorPanel.add(floorComboBox);

        btnGo = new JButton("Confirm Floor");
        floorPanel.add(btnGo);
        frame.add(floorPanel, BorderLayout.NORTH);

        // Panel for room selection; initially empty.
        roomPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        frame.add(roomPanel, BorderLayout.CENTER);

        // When the customer clicks "Confirm Floor":
        btnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedFloor = (int) floorComboBox.getSelectedItem();
                // Simulate elevator arrival:
                JOptionPane.showMessageDialog(frame, "Elevator has arrived at Floor " + selectedFloor);
                showRoomsForFloor(selectedFloor);
            }
        });

        frame.setVisible(true);
    }

    // Displays all rooms on the given floor.
    private void showRoomsForFloor(int floor) {
        roomPanel.removeAll();
        // Get all rooms on the selected floor.
        List<Room> roomsOnFloor = roomManager.getRoomsByFloor(floor);
        // For a RoomCard, get its allowed room list.
        List<String> allowedRooms = null;
        if (card instanceof RoomCard) {
            allowedRooms = ((RoomCard) card).getRoomAccess();
        }

        // For each room on that floor, create a button.
        for (Room room : roomsOnFloor) {
            JButton roomButton = new JButton("Room " + room.getRoomId());
            // If the customer's allowed room list contains this room, mark as allowed (green).
            if (allowedRooms != null && allowedRooms.contains(room.getRoomId())) {
                roomButton.setBackground(Color.GREEN);
                roomButton.setEnabled(true);
            } else {
                roomButton.setBackground(Color.LIGHT_GRAY);
                roomButton.setEnabled(false);
            }

            // For allowed rooms, add an action to prompt for a password.
            roomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputPassword = JOptionPane.showInputDialog(frame,
                            "Enter password for Room " + room.getRoomId() + ":");
                    // Check access: we pass the card ID, the floor, the room id, and the entered password.
                    if (accessFacade.canAccess(card.getCardId(), floor, room.getRoomId(), inputPassword)) {
                        JOptionPane.showMessageDialog(frame, "Access granted to Room " + room.getRoomId());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect password for Room " + room.getRoomId());
                    }
                }
            });

            roomPanel.add(roomButton);
        }
        roomPanel.revalidate();
        roomPanel.repaint();
    }
}
