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
        manager = new CardManage();
        manager.addCard(new Card("Admin", Arrays.asList(1, 2, 3, 4, 5))); // Admin full access
        manager.addCard(new Card("Customer", Arrays.asList(1, 2))); // Customer limited access

        frame = new JFrame("Elevator Access System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        adminButton = new JButton("Admin Card");
        customerButton = new JButton("Customer Card");
        computerButton = new JButton("Use Computer");
        elevatorButton = new JButton("Use Elevator");

        frame.add(adminButton);
        frame.add(customerButton);
        frame.add(computerButton);
        frame.add(elevatorButton);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = manager.getCardById("Admin");
                JOptionPane.showMessageDialog(frame, "Admin Card Selected!\nAccess to All Floors");
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = manager.getCardById("Customer");
                JOptionPane.showMessageDialog(frame, "Customer Card Selected!\nLimited Access");
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
                    String input = JOptionPane.showInputDialog(frame, "Enter Floor Number:");
                    if (input != null) {
                        int floor = Integer.parseInt(input);
                        if (selectedCard.getFloorAccess().contains(floor)) {
                            JOptionPane.showMessageDialog(frame, "✅ Access Granted to Floor " + floor);
                        } else {
                            JOptionPane.showMessageDialog(frame, "❌ Access Denied!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "⚠️ Please select a card first!");
                }
            }
        });

        frame.setVisible(true);
    }
}
