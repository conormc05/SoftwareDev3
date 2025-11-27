package ticketGM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyTickets extends JFrame implements ActionListener {

    private JComboBox<String> eventComboBox;
    private JSpinner quantitySpinner;
    private JButton buyButton;
    private JButton backButton;

    public BuyTickets() {
        setTitle("Ticket GM - Buy Tickets");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        mainPanel.setBackground(new Color(250,250,250));

        JLabel titleLabel = new JLabel("Buy Tickets");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel);

        JLabel eventLabel = new JLabel("Select an Event:");
        eventLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(eventLabel);

        String[] events = {
                "🎵 Rock Music Festival",
                "🎻 Classical Orchestra Night",
                "⚽ Football Championship",
                "🏀 Basketball Finals",
                "🎤 Pop Concert"
        };

        eventComboBox = new JComboBox<>(events);
        eventComboBox.setMaximumSize(new Dimension(300, 30));
        eventComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(eventComboBox);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel quantityLabel = new JLabel("Select Quantity:");
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 10, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setMaximumSize(new Dimension(100, 30));
        quantitySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(quantitySpinner);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250, 250, 250));

        buyButton = new JButton("Buy Now");
        buyButton.addActionListener(this);
        buttonPanel.add(buyButton);

        backButton = new JButton("Back to Menu");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel);
        add(mainPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {

            String selectedEvent = (String) eventComboBox.getSelectedItem();
            int quantity = (int) quantitySpinner.getValue();

            if (quantity == 0) {
                JOptionPane.showMessageDialog(this, "Invalid Amount", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TicketStorage.addTicket(selectedEvent, quantity);

            // Show order summary
            OrderSummaryPopup.showSummary(this, selectedEvent, quantity);

            // Download ticket PDF with QR code
            int orderNumber = (int)(Math.random() * 900000 + 100000);
            TicketDownloader.downloadTicket(this, selectedEvent, quantity, orderNumber);

        } else if (e.getSource() == backButton) {
            dispose();
        }
    }
}