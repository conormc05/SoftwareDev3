package ticketGM;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewEventsPageTest {

    private ViewEventsPage frame;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame = new ViewEventsPage());
    }

    @AfterEach
    void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(frame::dispose);
        }
    }

    @Test
    void testFrameTitle() {
        assertEquals("Available Events", frame.getTitle());
    }

    @Test
    void testFrameHasScrollPane() {
        Component comp = frame.getContentPane().getComponent(0);

        assertTrue(comp instanceof JScrollPane, "Main component should be a JScrollPane");
    }

    @Test
    void testScrollPaneContainsJList() {
        JScrollPane scrollPane = (JScrollPane) frame.getContentPane().getComponent(0);
        JViewport viewport = scrollPane.getViewport();
        Component view = viewport.getView();

        assertTrue(view instanceof JList, "ScrollPane should contain a JList");
    }

    @Test
    void testEventListContents() {
        JScrollPane scrollPane = (JScrollPane) frame.getContentPane().getComponent(0);
        JList<?> list = (JList<?>) scrollPane.getViewport().getView();

        ListModel<?> model = list.getModel();

        assertEquals(4, model.getSize(), "There should be 4 events in the list");

        assertEquals("Rock Concert - 25/11/2025 - €45", model.getElementAt(0));
        assertEquals("Football Match - 01/12/2025 - €60", model.getElementAt(1));
        assertEquals("Opera Night - 05/12/2025 - €80", model.getElementAt(2));
        assertEquals("Tech Expo - 10/12/2025 - €20", model.getElementAt(3));
    }

    @Test
    void testListSelectionMode() {
        JScrollPane scrollPane = (JScrollPane) frame.getContentPane().getComponent(0);
        JList<?> list = (JList<?>) scrollPane.getViewport().getView();

        assertEquals(ListSelectionModel.SINGLE_SELECTION, list.getSelectionMode());
    }
}
