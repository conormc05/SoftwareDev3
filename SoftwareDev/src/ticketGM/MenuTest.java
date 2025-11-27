package ticketGM;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu frame;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame = new Menu());
    }

    @AfterEach
    void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(frame::dispose);
        }
    }

    // ----- Reflection helpers -----

    private JButton getButton(String fieldName) throws Exception {
        Field f = Menu.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        return (JButton) f.get(frame);
    }


    // ---------------- Tests ----------------

    @Test
    void testButtonsAreCreated() throws Exception {
        assertNotNull(getButton("viewEventsButton"));
        assertNotNull(getButton("searchEventsButton"));
        assertNotNull(getButton("buyTicketsButton"));
        assertNotNull(getButton("viewMyTicketsButton"));
        assertNotNull(getButton("exitButton"));
    }

    @Test
    void testButtonsHaveActionListeners() throws Exception {
        JButton view = getButton("viewEventsButton");
        JButton search = getButton("searchEventsButton");
        JButton buy = getButton("buyTicketsButton");
        JButton manage = getButton("viewMyTicketsButton");
        JButton exit = getButton("exitButton");

        assertTrue(view.getActionListeners().length >= 1);
        assertTrue(search.getActionListeners().length >= 1);
        assertTrue(buy.getActionListeners().length >= 1);
        assertTrue(manage.getActionListeners().length >= 1);
        assertTrue(exit.getActionListeners().length >= 1);
    }

    @Test
    void testButtonClicksDoNotCrash() throws Exception {
        JButton view = getButton("viewEventsButton");
        JButton search = getButton("searchEventsButton");
        JButton buy = getButton("buyTicketsButton");
        JButton manage = getButton("viewMyTicketsButton");
        JButton exit = getButton("exitButton");

        SwingUtilities.invokeAndWait(view::doClick);
        SwingUtilities.invokeAndWait(search::doClick);
        SwingUtilities.invokeAndWait(buy::doClick);
        SwingUtilities.invokeAndWait(manage::doClick);

        // Exit button opens a confirm dialog → do not click it in automated tests.
        assertTrue(true, "Buttons clicked safely");
    }
}
