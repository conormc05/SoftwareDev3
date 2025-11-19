package ticketGM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchEventsPage extends JFrame implements ActionListener {

    private JTextField searchField;
    private JTextArea resultArea;

    public SearchEventsPage() {
        setTitle("Search Events");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter keyword: "));
        searchField = new JTextField(15);
        topPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String keyword = searchField.getText().toLowerCase();
        resultArea.setText(""); 

        // Same sample event list (later replace with real data)
        String[] events = {
                "Rock Concert - 25/11/2025 - €45",
                "Football Match - 01/12/2025 - €60",
                "Opera Night - 05/12/2025 - €80",
                "Tech Expo - 10/12/2025 - €20"
        };

        boolean found = false;
        for (String event : events) {
            if (event.toLowerCase().contains(keyword)) {
                resultArea.append(event + "\n");
                found = true;
            }
        }

        if (!found) {
            resultArea.setText("No matching events found.");
        }
    }
}