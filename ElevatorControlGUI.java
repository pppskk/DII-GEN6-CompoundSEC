import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ElevatorControlGUI {
    private JFrame frame;
    private JLabel display;
    private JButton confirmButton;
    private JPanel floorPanel, roomPanel;
    private int currentFloor = 1;
    private BaseCard card;
    private AccessFacade accessFacade;
    private String allowedRoom; // Store the single accessible room
    private String roomPassword = "1234"; // Example password for room access

    public ElevatorControlGUI(BaseCard card, AccessFacade accessFacade) {
        this.card = card;
        this.accessFacade = accessFacade;

        // Ensure only one room is assigned per card (for RoomCard)
        if (card instanceof RoomCard) {
            List<String> rooms = ((RoomCard) card).getRoomAccess();
            allowedRoom = (rooms.isEmpty()) ? null : rooms.get(0); // Assign the first room
        } else {
            allowedRoom = null;  // No room access for other cards
        }

        frame = new JFrame("Elevator Control Panel");
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        display = new JLabel("Select Floor", SwingConstants.CENTER);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(display, BorderLayout.NORTH);

        // Floor selection panel (display buttons for floors the card has access to)
        floorPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        for (int floor = 1; floor <= 5; floor++) {
            final int selectedFloor = floor;  // Make the floor variable effectively final
            JButton floorButton = new JButton("Floor " + selectedFloor);
            floorButton.setFont(new Font("Arial", Font.BOLD, 16));

            // Disable floors the card doesn't have access to
            if (card.getFloorAccess().contains(floor)) {
                floorButton.setEnabled(true);
                floorButton.addActionListener(e -> selectFloor(selectedFloor));
            } else {
                floorButton.setEnabled(false);
                floorButton.setBackground(Color.LIGHT_GRAY); // Disable button visually
            }

            floorPanel.add(floorButton);
        }
        frame.add(floorPanel, BorderLayout.CENTER);

        // Room display panel; initially empty
        roomPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        frame.add(roomPanel, BorderLayout.SOUTH);

        // Confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(e -> moveElevator(currentFloor));
        frame.add(confirmButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

    // Called when a floor is selected.
    private void selectFloor(int floor) {
        currentFloor = floor;
        display.setText("Floor: " + currentFloor);
        confirmButton.setEnabled(true);
        updateRooms(currentFloor);  // Show rooms on the selected floor
    }

    // Update rooms based on the selected floor
    private void updateRooms(int floor) {
        roomPanel.removeAll(); // Clear previous rooms
        List<Room> roomsOnFloor = accessFacade.getRoomsByFloor(floor);

        // For each room on that floor, create a button
        for (Room room : roomsOnFloor) {
            JButton roomButton = new JButton(room.getRoomId());
            roomButton.setOpaque(true);
            roomButton.setBorderPainted(false);

            // If it's an allowed room, mark it as green and enable it
            if (allowedRoom != null && allowedRoom.equals(room.getRoomId())) {
                roomButton.setBackground(Color.GREEN);
                roomButton.setEnabled(true);
                roomButton.addActionListener(e -> requestPassword(room)); // Add password check
            } else {
                roomButton.setBackground(Color.LIGHT_GRAY);
                roomButton.setEnabled(false); // Disable the button if room is not allowed
            }

            roomPanel.add(roomButton);
        }

        roomPanel.revalidate();
        roomPanel.repaint();
    }

    // Request password to access the room
    private void requestPassword(Room room) {
        String inputPassword = JOptionPane.showInputDialog(frame, "Enter password for Room " + room.getRoomId());

        if (inputPassword != null && inputPassword.equals(roomPassword)) {
            JOptionPane.showMessageDialog(frame, "Access Granted! Welcome to Room " + room.getRoomId());
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong Password! Access Denied.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Simulate elevator movement
    private void moveElevator(int floor) {
        JOptionPane.showMessageDialog(frame, "Arrived at Floor " + floor);
        updateRooms(floor);  // Update room availability on the floor when the elevator arrives
    }
}
