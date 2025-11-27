package ticketGM;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ManageTicketsPageTest {

    private ManageTicketsPage frame;

    // ---------- Helpers to access private fields ----------

    private DefaultListModel<String> getListModel() throws Exception {
        Field f = ManageTicketsPage.class.getDeclaredField("listModel");
        f.setAccessible(true);
        return (DefaultListModel<String>) f.get(frame);
    }

    private JList<String> getTicketList() throws Exception {
        Field f = ManageTicketsPage.class.getDeclaredField("ticketList");
        f.setAccessible(true);
        return (JList<String>) f.get(frame);
    }

    private JButton getDeleteButton() throws Exception {
        Field f = ManageTicketsPage.class.getDeclaredField("deleteButton");
        f.setAccessible(true);
        return (JButton) f.get(frame);
    }

    private JButton getDownloadButton() throws Exception {
        Field f = ManageTicketsPage.class.getDeclaredField("downloadButton");
        f.setAccessible(true);
        return (JButton) f.get(frame);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(() -> frame.dispose());
        }
    }

    // ----------------------- Tests -----------------------

    @Test
    void testEmptyTicketsShowsPlaceholderAndDisablesControls() throws Exception {
        // This assumes TicketStorage is empty at the start of the test run.
        SwingUtilities.invokeAndWait(() -> frame = new ManageTicketsPage());

        DefaultListModel<String> model = getListModel();
        JList<String> list = getTicketList();
        JButton deleteButton = getDeleteButton();
        JButton downloadButton = getDownloadButton();

        assertEquals(1, model.size(), "List should contain one placeholder entry");
        assertEquals("No tickets purchased yet.", model.get(0));

        assertFalse(list.isEnabled(), "Ticket list should be disabled when there are no tickets");
        assertFalse(deleteButton.isEnabled(), "Delete button should be disabled when there are no tickets");
        assertFalse(downloadButton.isEnabled(), "Download button should be disabled when there are no tickets");
    }

    @Test
    void testTicketsLoadedWhenStorageHasEntries() throws Exception {
        // Add some tickets to storage first
        TicketStorage.addTicket("Rock Music Festival", 2);
        TicketStorage.addTicket("Football Championship", 3);

        SwingUtilities.invokeAndWait(() -> frame = new ManageTicketsPage());

        DefaultListModel<String> model = getListModel();
        JList<String> list = getTicketList();
        JButton deleteButton = getDeleteButton();
        JButton downloadButton = getDownloadButton();

        assertTrue(model.size() >= 2, "There should be at least two tickets in the list");

        // Check that strings are formatted as expected
        String firstEntry = model.get(0);
        assertTrue(firstEntry.contains("Event:"), "Entry should contain 'Event:'");
        assertTrue(firstEntry.contains("Quantity:"), "Entry should contain 'Quantity:'");

        assertTrue(list.isEnabled(), "Ticket list should be enabled when tickets exist");
        assertTrue(deleteButton.isEnabled(), "Delete button should be enabled when tickets exist");
        assertTrue(downloadButton.isEnabled(), "Download button should be enabled when tickets exist");
    }
}
