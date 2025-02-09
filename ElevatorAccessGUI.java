import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ElevatorAccessGUI {
    private JFrame frame;
    private JButton adminButton, customerButton, computerButton, elevatorButton;
    private CardManage manager;
    private BaseCard selectedCard;

    public ElevatorAccessGUI() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        manager = new CardManage();
        manager.addCard(new Card("Admin", Arrays.asList(1, 2, 3, 4, 5))); // Admin full access
        manager.addCard(new Card("Customer", Arrays.asList(1, 2))); // Customer limited access

        frame = new JFrame("Elevator Access System");
        frame.setSize(400, 300);
        //frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(3, 2));
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        adminButton = new JButton("Admin Card");
        customerButton = new JButton("Customer Card");
        computerButton = new JButton("Use Computer");
        elevatorButton = new JButton("Use Elevator");

        adminButton.setBackground(Color.GREEN);
        adminButton.setForeground(Color.WHITE);
        adminButton.setOpaque(true);
        adminButton.setBorderPainted(false);

        customerButton.setBackground(Color.BLUE);
        customerButton.setForeground(Color.WHITE);

        computerButton.setBackground(Color.RED);
        computerButton.setForeground(Color.WHITE);
        computerButton.setEnabled(false);

        elevatorButton.setBackground(Color.ORANGE);
        elevatorButton.setForeground(Color.WHITE);
        elevatorButton.setEnabled(false);

        frame.add(adminButton);
        frame.add(customerButton);
        frame.add(computerButton);
        frame.add(elevatorButton);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = manager.getCardById("Admin");
                JOptionPane.showMessageDialog(frame, "Admin Card Selected!\nAccess to All Floors");
                computerButton.setEnabled(true);
                elevatorButton.setEnabled(true);
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = manager.getCardById("Customer");
                JOptionPane.showMessageDialog(frame, "Customer Card Selected!\nLimited Access");
                computerButton.setEnabled(true);
                elevatorButton.setEnabled(true);
            }
        });

        computerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCard != null && selectedCard.getCardId().equals("Admin")) {
                    JOptionPane.showMessageDialog(frame, "✅ Access Granted to Computer!");
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Access Denied! (Only Admins)");
                }
            }
        });

        elevatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCard != null) {
                    new ElevatorPanel(selectedCard); // เปิดหน้าลิฟต์ใหม่
                } else {
                    JOptionPane.showMessageDialog(frame, "⚠️ Please select a card first!");
                }
            }
        });

        frame.setVisible(true);
    }
}
