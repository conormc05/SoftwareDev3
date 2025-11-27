package ticketGM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketStorageTest {

    private static final String FILE_NAME = "tickets.txt";

    @BeforeEach
    void setUp() throws Exception {
        // Clean or create the file before each test so tests are independent
        File file = new File(FILE_NAME);
        if (file.exists()) {
            // Overwrite with empty content
            try (FileWriter fw = new FileWriter(file, false)) {
                // just clear file
            }
        }
    }

    @Test
    void testAddTicketAndLoadTickets() {
        TicketStorage.addTicket("Rock Concert", 2);
        TicketStorage.addTicket("Football Match", 3);

        List<String> tickets = TicketStorage.loadTickets();
        assertEquals(2, tickets.size(), "There should be 2 tickets saved");

        assertEquals("Rock Concert;2", tickets.get(0));
        assertEquals("Football Match;3", tickets.get(1));
    }

    @Test
    void testRemoveTicketRemovesCorrectEntry() {
        // Seed some data
        TicketStorage.addTicket("Event A", 1);
        TicketStorage.addTicket("Event B", 2);
        TicketStorage.addTicket("Event C", 3);

        List<String> before = TicketStorage.loadTickets();
        assertEquals(3, before.size());

        // Remove index 1 (second ticket, "Event B;2")
        TicketStorage.removeTicket(1);

        List<String> after = TicketStorage.loadTickets();
        assertEquals(2, after.size(), "One ticket should have been removed");

        // Check remaining tickets are A and C
        assertEquals("Event A;1", after.get(0));
        assertEquals("Event C;3", after.get(1));
    }

    @Test
    void testRemoveTicketWithInvalidIndexDoesNothing() {
        TicketStorage.addTicket("Only Event", 5);

        List<String> before = TicketStorage.loadTickets();
        assertEquals(1, before.size());

        // Invalid indices: -1 and 5
        TicketStorage.removeTicket(-1);
        TicketStorage.removeTicket(5);

        List<String> after = TicketStorage.loadTickets();
        assertEquals(1, after.size(), "Invalid index should not change the list");
        assertEquals("Only Event;5", after.get(0));
    }

    @Test
    void testLoadTicketsWhenFileDoesNotExist() {
        // Delete the file if it exists
        File file = new File(FILE_NAME);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete tickets.txt for this test");
        }

        List<String> tickets = TicketStorage.loadTickets();
        assertNotNull(tickets);
        assertTrue(tickets.isEmpty(), "When file does not exist, loadTickets should return empty list");
    }
}
