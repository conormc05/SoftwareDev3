package ticketGM;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.Field;

public class BuyTicketsTest {

    private BuyTickets frame;

    @Before
    public void setUp() throws Exception {
        // Create the frame on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeAndWait(() -> frame = new BuyTickets());
    }

    @After
    public void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(() -> frame.dispose());
        }
    }

    // ---------- Helper methods to access private fields ----------

    private JSpinner getQuantitySpinner() throws Exception {
        Field f = BuyTickets.class.getDeclaredField("quantitySpinner");
        f.setAccessible(true);
        return (JSpinner) f.get(frame);
    }

    private JComboBox<String> getEventComboBox() throws Exception {
        Field f = BuyTickets.class.getDeclaredField("eventComboBox");
        f.setAccessible(true);
        return (JComboBox<String>) f.get(frame);
    }

    private JButton getBuyButton() throws Exception {
        Field f = BuyTickets.class.getDeclaredField("buyButton");
        f.setAccessible(true);
        return (JButton) f.get(frame);
    }

    // ----------------------- Tests -----------------------

    @Test
    public void testInitialState() throws Exception {
        assertNotNull("Frame should be created", frame);

        JComboBox<String> eventComboBox = getEventComboBox();
        assertNotNull("Event combo box should not be null", eventComboBox);
        assertTrue("There should be at least one event",
                eventComboBox.getItemCount() > 0);

        JSpinner spinner = getQuantitySpinner();
        assertNotNull("Quantity spinner should not be null", spinner);

        int initialValue = (Integer) spinner.getValue();
        assertEquals("Initial quantity should be 0", 0, initialValue);
    }

    @Test
    public void testBuyWithZeroQuantityDoesNotCrash() throws Exception {
        JSpinner spinner = getQuantitySpinner();
        JButton buyButton = getBuyButton();

        // Set quantity to 0
        spinner.setValue(0);

        // Click the Buy button on the EDT
        SwingUtilities.invokeAndWait(buyButton::doClick);

        // If we reach here with no exception, the test passes.
        // (We know it should show the "Invalid Amount" error dialog.)
        assertTrue(true);
    }

    @Test
    public void testChangeQuantityAndEventSelection() throws Exception {
        JSpinner spinner = getQuantitySpinner();
        JComboBox<String> eventComboBox = getEventComboBox();

        // Change quantity
        spinner.setValue(3);
        int value = (Integer) spinner.getValue();
        assertEquals("Quantity should be updated to 3", 3, value);

        // Change selected event
        eventComboBox.setSelectedIndex(1);
        String selectedEvent = (String) eventComboBox.getSelectedItem();
        assertNotNull("Selected event should not be null", selectedEvent);
    }
}
