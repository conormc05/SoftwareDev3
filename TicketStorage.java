package ticketGM;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketStorage {

    private static final String FILE_NAME = "tickets.txt";

    // Load tickets from file
    public static List<Ticket> loadTickets() {
        List<Ticket> tickets = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) return tickets;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.contains(";")) continue;

                String[] parts = line.split(";");
                String eventName = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                tickets.add(new Ticket(eventName, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }

    // Save all tickets back to file
    private static void saveTickets(List<Ticket> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Ticket t : tickets) {
                writer.write(t.getEventName() + ";" + t.getQuantity());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a new ticket
    public static void addTicket(String eventName, int quantity) {
        List<Ticket> tickets = loadTickets();
        tickets.add(new Ticket(eventName, quantity));
        saveTickets(tickets);
    }
}
