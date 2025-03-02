import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ElevatorAccessGUI {
    private JFrame frame;
    private JButton btnUseElevator, btnUseComputer, btnRefresh;
    private JComboBox<String> cardComboBox; // สำหรับเลือกบัตร
    private BaseCard selectedCard;
    private CardManage manager;
    private AccessFacade accessFacade;

    public ElevatorAccessGUI(CardManage manager, AccessFacade accessFacade) {
        this.manager = manager;
        this.accessFacade = accessFacade;

        frame = new JFrame("Elevator Access");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // สร้าง combo box สำหรับแสดงรายการ Card ID
        cardComboBox = new JComboBox<>();
        refreshCardComboBox();

        // ปุ่มใช้งาน
        btnUseElevator = new JButton("Use Elevator");
        btnUseComputer = new JButton("Use Computer");
        btnRefresh = new JButton("Refresh Cards");

        // Panel สำหรับ combo box และปุ่ม refresh
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Card: "));
        topPanel.add(cardComboBox);
        topPanel.add(btnRefresh);

        // Panel สำหรับปุ่ม
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(btnUseElevator);
        buttonPanel.add(btnUseComputer);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // เมื่อเปลี่ยนรายการใน combo box ให้ตั้งค่า selectedCard
        cardComboBox.addActionListener(e -> {
            String selectedId = (String) cardComboBox.getSelectedItem();
            if (selectedId != null) {
                selectedCard = manager.getAllCards().stream()
                        .filter(c -> c.getCardId().equals(selectedId))
                        .findFirst()
                        .orElse(null);
            }
        });

        // ปุ่ม Refresh เพื่อรีเฟรช combo box เมื่อมีการเพิ่มบัตรใหม่
        btnRefresh.addActionListener(e -> {
            refreshCardComboBox();
            JOptionPane.showMessageDialog(frame, "Cards refreshed.");
        });

        // ปุ่ม Use Computer: ให้เปิด AdminPanel หากบัตรที่เลือกเป็น AdminCard
        btnUseComputer.addActionListener(e -> {
            if (selectedCard != null && selectedCard instanceof AdminCard) {
                new AdminPanelGUI(manager, accessFacade, () -> refreshCardComboBox());
            } else {
                JOptionPane.showMessageDialog(frame, "Only Admin can use the computer!");
            }
        });

        // ปุ่ม Use Elevator: เปิด ElevatorControlGUI สำหรับบัตรที่เลือก
        btnUseElevator.addActionListener(e -> {
            if (selectedCard != null) {
                new ElevatorControlGUI(selectedCard, accessFacade);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a card first!");
            }
        });
    }

    // Method เพื่อรีเฟรชรายการใน combo box จาก CardManage
    private void refreshCardComboBox() {
        cardComboBox.removeAllItems();
        List<String> cardIds = manager.getAllCards().stream()
                .map(BaseCard::getCardId)
                .collect(Collectors.toList());
        for (String id : cardIds) {
            cardComboBox.addItem(id);
        }
    }
}
