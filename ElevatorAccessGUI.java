import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ElevatorAccessGUI {
    private JFrame frame;
    private JButton adminButton, customerButton, btnUseElevator;
    private BaseCard selectedCard;
    private CardManage manager;
    private AccessFacade accessFacade;

    public ElevatorAccessGUI(CardManage manager, AccessFacade accessFacade) {
        this.manager = manager;
        this.accessFacade = accessFacade;

        frame = new JFrame("Elevator Access");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        adminButton = new JButton("Admin Card");
        customerButton = new JButton("Customer Card");
        btnUseElevator = new JButton("Use Elevator");
        btnUseElevator.setEnabled(false);

        frame.add(adminButton);
        frame.add(customerButton);
        frame.add(btnUseElevator);
        frame.setVisible(true);

        adminButton.addActionListener(e -> selectCard("Admin"));
        customerButton.addActionListener(e -> selectCard("Customer"));
        btnUseElevator.addActionListener(e -> new ElevatorControlGUI(selectedCard, accessFacade));
    }

    private void selectCard(String cardId) {
        BaseCard card = manager.getCardById(cardId);
        if (card != null) {
            selectedCard = card;
            JOptionPane.showMessageDialog(frame, cardId + " Card Selected! ✅");
            btnUseElevator.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(frame, "❌ " + cardId + " Card Not Found!");
        }
    }
}