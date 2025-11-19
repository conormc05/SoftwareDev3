package ticketGM;

import javax.swing.*;

public class OrderSummaryPopup {

    public static void showSummary(JFrame parent, String eventName, int quantity) {
        int orderNumber = (int)(Math.random() * 900000 + 100000);

        String message =
                "🎟️  ORDER SUMMARY\n\n" +
                "Event: " + eventName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Order Number: TG-" + orderNumber + "\n\n" +
                "Thank you for your purchase!";

        JOptionPane.showMessageDialog(parent, message, "Order Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
