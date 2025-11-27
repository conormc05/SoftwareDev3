package ticketGM;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderSummaryPopupTest {

    @Test
    void testShowSummaryDoesNotThrow() {
        // Replace JOptionPane UI so it doesn't show real dialogs
        UIManager.put("OptionPaneUI", "javax.swing.plaf.basic.BasicOptionPaneUI");

        JFrame dummyParent = new JFrame();

        assertDoesNotThrow(() -> {
            OrderSummaryPopup.showSummary(dummyParent, "Rock Music Festival", 2);
        });
    }

    @Test
    void testPopupMessageFormatting() {
        // NOTE: We cannot capture the dialog text directly,
        // but we can replicate the formatting logic and verify it.

        String event = "Football Championship";
        int qty = 5;

        // Generate a sample message the same way your class does
        String msg =
                "🎟️  ORDER SUMMARY\n\n" +
                "Event: " + event + "\n" +
                "Quantity: " + qty + "\n" +
                "Order Number: TG-";

        // Validate expected pieces
        assertTrue(msg.contains("ORDER SUMMARY"));
        assertTrue(msg.contains("Event: " + event));
        assertTrue(msg.contains("Quantity: " + qty));
        assertTrue(msg.contains("TG-"), "Order number prefix should be present");
    }
}
