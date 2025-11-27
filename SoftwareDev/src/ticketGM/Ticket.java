

package ticketGM;

public class Ticket {
    private String eventName;
    private int quantity;

    public Ticket(String eventName, int quantity) {
        this.eventName = eventName;
        this.quantity = quantity;
    }

    public String getEventName() {
        return eventName;
    }

    public int getQuantity() {
        return quantity;
    }
}
