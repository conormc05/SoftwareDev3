package ticketGM;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewMyTicketsPage extends JFrame {

    public ViewMyTicketsPage() {
        setTitle("My Tickets");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Ticket> tickets = TicketStorage.loadTickets();

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (tickets.isEmpty()) {
            area.setText("You have no purchased tickets.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Ticket t : tickets) {
                sb.append("Event: ").append(t.getEventName())
                  .append("\nQuantity: ").append(t.getQuantity())
                  .append("\n-----------------------\n");
            }
            area.setText(sb.toString());
        }

        add(new JScrollPane(area), BorderLayout.CENTER);

        setVisible(true);
    }
}
