package ticketGM;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SearchEventsPageTest {

    private SearchEventsPage frame;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame = new SearchEventsPage());
    }

    @AfterEach
    void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(frame::dispose);
        }
    }

    // --------- Reflection helpers ---------

    private JTextField getSearchField() throws Exception {
        Field f = SearchEventsPage.class.getDeclaredField("searchField");
        f.setAccessible(true);
        return (JTextField) f.get(frame);
    }

    private JTextArea getResultArea() throws Exception {
        Field f = SearchEventsPage.class.getDeclaredField("resultArea");
        f.setAccessible(true);
        return (JTextArea) f.get(frame);
    }

    // ---------------- Tests ----------------

    @Test
    void testInitialState() throws Exception {
        JTextField searchField = getSearchField();
        JTextArea resultArea = getResultArea();

        assertEquals("", searchField.getText(), "Search field should start empty");
        assertEquals("", resultArea.getText(), "Result area should start empty");
    }

    @Test
    void testSearchFindsMatchingEvents() throws Exception {
        JTextField searchField = getSearchField();
        JTextArea resultArea = getResultArea();

        // Search for "rock" which should match "Rock Concert - 25/11/2025 - €45"
        searchField.setText("rock");

        SwingUtilities.invokeAndWait(() -> frame.actionPerformed(null));

        String text = resultArea.getText();
        assertFalse(text.isEmpty(), "Result area should not be empty after search");
        assertTrue(text.toLowerCase().contains("rock concert"), "Should contain Rock Concert event");
        assertFalse(text.contains("No matching events found."),
                "Should not show 'No matching events found.' when there is a match");
    }

    @Test
    void testSearchNoMatchesShowsMessage() throws Exception {
        JTextField searchField = getSearchField();
        JTextArea resultArea = getResultArea();

        // Search for something that doesn't exist
        searchField.setText("ballet");

        SwingUtilities.invokeAndWait(() -> frame.actionPerformed(null));

        String text = resultArea.getText().trim();
        assertEquals("No matching events found.", text);
    }
}
