package ticketGM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageTicketsPage extends JFrame implements ActionListener {

    private JList<String> ticketList;
    private JButton deleteButton;
    private JButton downloadButton;
    private DefaultListModel<String> listModel;

    public ManageTicketsPage() {
        setTitle("Manage Tickets");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Initialize components first ---
        listModel = new DefaultListModel<>();
        ticketList = new JList<>(listModel);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        deleteButton = new JButton("Delete Ticket");
        deleteButton.addActionListener(this);

        downloadButton = new JButton("Download Ticket");
        downloadButton.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(ticketList);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(downloadButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Now load tickets ---
        loadTickets();

        setVisible(true);
    }

    private void loadTickets() {
        listModel.clear();
        List<String> tickets = TicketStorage.loadTickets();

        if (tickets.isEmpty()) {
            listModel.addElement("No tickets purchased yet.");
            ticketList.setEnabled(false);
            deleteButton.setEnabled(false);
            downloadButton.setEnabled(false);
        } else {
            ticketList.setEnabled(true);
            deleteButton.setEnabled(true);
            downloadButton.setEnabled(true);

            for (String t : tickets) {
                String[] parts = t.split(";");
                String event = parts[0];
                String qty = parts[1];
                listModel.addElement("Event: " + event + " | Quantity: " + qty);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = ticketList.getSelectedIndex();
        if (index == -1) return;

        if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this ticket?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                TicketStorage.removeTicket(index);
                loadTickets();
            }
        } else if (e.getSource() == downloadButton) {
            String selected = TicketStorage.loadTickets().get(index);
            String[] parts = selected.split(";");
            String event = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            int orderNumber = (int)(Math.random() * 900000 + 100000);

            TicketDownloader.downloadTicket(this, event, quantity, orderNumber);
        }
    }
}
