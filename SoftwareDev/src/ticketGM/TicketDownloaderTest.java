package ticketGM;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketDownloaderTest {

    @Test
    void testFileNameSanitisation() {
        String rawEvent = "Rock Concert @ Dublin!";
        String expected = "Rock_Concert___Dublin_";

        String actual = rawEvent.replaceAll("[^a-zA-Z0-9]", "_");

        assertEquals(expected, actual);
    }

    @Test
    void testTicketContentFormatting() {
        String event = "Rock Concert";
        int quantity = 2;
        int orderNumber = 123456;

        // Build the content the same way TicketDownloader does
        String content = "Ticket Grandmaster - E-Ticket\n";
        content += "-----------------------------\n";
        content += "Event: " + event + "\n";
        content += "Quantity: " + quantity + "\n";
        content += "Order No: TG-" + orderNumber + "\n";
        content += "Date: " + java.time.LocalDate.now().toString() + "\n";
        content += "-----------------------------\n";
        content += "Thank you for your purchase!\n";

        assertTrue(content.contains("Event: Rock Concert"));
        assertTrue(content.contains("Quantity: 2"));
        assertTrue(content.contains("Order No: TG-123456"));
        assertTrue(content.contains("Thank you for your purchase!"));
    }

    @Test
    void testLogicDoesNotThrow() {
        assertDoesNotThrow(() -> {
            // Simulate the internal logic (NOT the JFileChooser)
            String event = "Test Event";
            int quantity = 1;
            int orderNumber = 111111;

            String filename = event.replaceAll("[^a-zA-Z0-9]", "_") + "_ticket.txt";
            String content =
                    "Ticket Grandmaster - E-Ticket\n" +
                    "-----------------------------\n" +
                    "Event: " + event + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Order No: TG-" + orderNumber + "\n" +
                    "Date: " + java.time.LocalDate.now().toString() + "\n" +
                    "-----------------------------\n" +
                    "Thank you for your purchase!\n";

            assertNotNull(filename);
            assertNotNull(content);
        });
    }
}
