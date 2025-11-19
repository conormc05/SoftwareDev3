package ticketGM;

import javax.swing.*;
import java.io.*;

public class TicketDownloader {

    public static void downloadTicket(JFrame parent, String event, int quantity, int orderNumber) {
        try {
            // Show file save dialog
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Ticket TXT");
            fileChooser.setSelectedFile(new File(event.replaceAll("[^a-zA-Z0-9]", "_") + "_ticket.txt"));

            int userSelection = fileChooser.showSaveDialog(parent);
            if (userSelection != JFileChooser.APPROVE_OPTION) return;

            File txtFile = fileChooser.getSelectedFile();

            // Create ticket content
            String content = "Ticket Grandmaster - E-Ticket\n";
            content += "-----------------------------\n";
            content += "Event: " + event + "\n";
            content += "Quantity: " + quantity + "\n";
            content += "Order No: TG-" + orderNumber + "\n";
            content += "Date: " + java.time.LocalDate.now().toString() + "\n";
            content += "-----------------------------\n";
            content += "Thank you for your purchase!\n";

            // Write TXT file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
                writer.write(content);
            }

            JOptionPane.showMessageDialog(parent,
                    "Ticket saved as TXT:\n" + txtFile.getAbsolutePath(),
                    "Download Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,
                    "Error saving ticket: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
