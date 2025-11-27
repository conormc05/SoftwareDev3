package ticketGM;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testConstructorStoresValues() {
        Ticket t = new Ticket("Rock Concert", 3);

        assertEquals("Rock Concert", t.getEventName());
        assertEquals(3, t.getQuantity());
    }

    @Test
    void testZeroQuantityAllowed() {
        Ticket t = new Ticket("Football Match", 0);

        assertEquals("Football Match", t.getEventName());
        assertEquals(0, t.getQuantity());
    }

    @Test
    void testEventNameCanBeAnyString() {
        Ticket t = new Ticket("🎤 Special Event @ Dublin Arena!!", 5);

        assertEquals("🎤 Special Event @ Dublin Arena!!", t.getEventName());
        assertEquals(5, t.getQuantity());
    }

    @Test
    void testNegativeQuantity() {
        // Your class does not block negative values,
        // so we simply verify that it stores the number unchanged.
        Ticket t = new Ticket("Test Event", -4);

        assertEquals("Test Event", t.getEventName());
        assertEquals(-4, t.getQuantity());
    }
}
