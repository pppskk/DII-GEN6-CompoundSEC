import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminPanelGUI {
    private JFrame frame;
    private JTable cardTable, logTable;
    private DefaultTableModel cardTableModel, logTableModel;
    private CardManage cardManager;
    private AccessFacade accessFacade;
    private Runnable onCardUpdate; // Callback to notify updates

    // Modified constructor to accept a callback
    public AdminPanelGUI(CardManage cardManager, AccessFacade accessFacade, Runnable onCardUpdate) {
        this.cardManager = cardManager;
        this.accessFacade = accessFacade;
        this.onCardUpdate = onCardUpdate;

        frame = new JFrame("Admin Panel");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table to display card data
        cardTableModel = new DefaultTableModel(new String[]{"Card ID", "Floors", "Rooms"}, 0);
        cardTable = new JTable(cardTableModel);
        updateCardTable();
        JScrollPane cardScrollPane = new JScrollPane(cardTable);

        // Log table to record transactions with date and time
        logTableModel = new DefaultTableModel(new String[]{"Date", "Time", "Transaction"}, 0);
        logTable = new JTable(logTableModel);
        JScrollPane logScrollPane = new JScrollPane(logTable);
        logScrollPane.setPreferredSize(new Dimension(800, 200));

        // Panel for action buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Card");
        JButton removeButton = new JButton("Remove Card");
        JButton refreshButton = new JButton("Refresh");

        addButton.addActionListener(e -> addCard());
        removeButton.addActionListener(e -> removeCard());
        refreshButton.addActionListener(e -> updateCardTable());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(refreshButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(cardScrollPane, BorderLayout.CENTER);
        frame.add(logScrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateCardTable() {
        cardTableModel.setRowCount(0);
        for (BaseCard card : cardManager.getAllCards()) {
            String floors = card.getFloorAccess().toString();
            String rooms = (card instanceof RoomCard) ? ((RoomCard) card).getRoomAccess().toString() : "N/A";
            cardTableModel.addRow(new Object[]{card.getCardId(), floors, rooms});
        }
    }

    private void addCard() {
        String baseId = JOptionPane.showInputDialog("Enter Base Card ID (will be encrypted):");
        if (baseId == null || baseId.isEmpty()) return;

        // Generate encrypted card ID using time-based encryption
        String encryptedId = TimeEncryp.encrypt(baseId);

        String floorInput = JOptionPane.showInputDialog("Enter Floors (comma separated):");
        String roomInput = JOptionPane.showInputDialog("Enter Rooms (comma separated, optional):");

        List<Integer> floors = Arrays.stream(floorInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        List<String> rooms = (roomInput == null || roomInput.isEmpty()) ? List.of() : Arrays.asList(roomInput.split(","));

        if (!rooms.isEmpty()) {
            cardManager.addCard(new RoomCard(encryptedId, floors, rooms));
            logAction("Added RoomCard with Encrypted ID: " + encryptedId);
        } else {
            cardManager.addCard(new Card(encryptedId, floors));
            logAction("Added Card with Encrypted ID: " + encryptedId);
        }
        updateCardTable();

        // Notify ElevatorAccessGUI that a card update has occurred.
        if (onCardUpdate != null) {
            onCardUpdate.run();
        }
    }

    private void removeCard() {
        String cardId = JOptionPane.showInputDialog("Enter Card ID to Remove:");
        if (cardId != null && !cardId.isEmpty()) {
            cardManager.removeCard(cardId);
            logAction("Removed Card with ID: " + cardId);
            updateCardTable();
            if (onCardUpdate != null) {
                onCardUpdate.run();
            }
        }
    }

    private void logAction(String action) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[] parts = sdf.format(new Date()).split(" ");
        String date = parts[0];
        String time = parts[1];
        logTableModel.addRow(new Object[]{date, time, action});
    }
}
