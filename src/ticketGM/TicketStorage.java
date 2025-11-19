package ticketGM;

import java.io.*;
import java.util.*;

public class TicketStorage {

    private static final String FILE_NAME = "tickets.txt";

    public static void addTicket(String event, int quantity) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(event + ";" + quantity + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeTicket(int index) {
        List<String> tickets = loadTickets();
        if (index >= 0 && index < tickets.size()) {
            tickets.remove(index);
            try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
                for (String t : tickets) fw.write(t + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> loadTickets() {
        List<String> tickets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) tickets.add(line);
        } catch (IOException e) {
            // File may not exist yet
        }
        return tickets;
    }
}
