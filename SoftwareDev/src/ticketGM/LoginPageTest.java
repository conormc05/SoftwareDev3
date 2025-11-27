package ticketGM;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    private LoginPage frame;

    @BeforeEach
    void setUp() throws Exception {
        // Build the frame on the Swing Event Dispatch Thread (EDT)
        SwingUtilities.invokeAndWait(() -> frame = new LoginPage());
    }

    @AfterEach
    void tearDown() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(() -> frame.dispose());
        }
    }

    // ---------- Helper methods to access private fields ----------

    private JTextField getUsernameField() throws Exception {
        Field f = LoginPage.class.getDeclaredField("username");
        f.setAccessible(true);
        return (JTextField) f.get(frame);
    }

    private JPasswordField getPasswordField() throws Exception {
        Field f = LoginPage.class.getDeclaredField("password");
        f.setAccessible(true);
        return (JPasswordField) f.get(frame);
    }

    private JButton getLoginButton() throws Exception {
        Field f = LoginPage.class.getDeclaredField("login");
        f.setAccessible(true);
        return (JButton) f.get(frame);
    }

    private JLabel getMessageLabel() throws Exception {
        Field f = LoginPage.class.getDeclaredField("message");
        f.setAccessible(true);
        return (JLabel) f.get(frame);
    }

    // ----------------------- Tests -----------------------

    @Test
    void testInitialState() throws Exception {
        assertNotNull(frame);

        JTextField userField = getUsernameField();
        JPasswordField passField = getPasswordField();
        JLabel msgLabel = getMessageLabel();

        assertEquals("", userField.getText(), "Username should start empty");
        assertEquals("", new String(passField.getPassword()), "Password should start empty");
        assertEquals("", msgLabel.getText(), "Message label should start empty");
    }

    @Test
    void testEmptyFieldsShowsErrorMessage() throws Exception {
        JTextField userField = getUsernameField();
        JPasswordField passField = getPasswordField();
        JButton loginButton = getLoginButton();

        // Ensure both fields are empty
        userField.setText("");
        passField.setText("");

        // Click Login on the EDT
        SwingUtilities.invokeAndWait(loginButton::doClick);

        JLabel msgLabel = getMessageLabel();
        assertEquals("Please enter both fields", msgLabel.getText());
        assertEquals(Color.RED, msgLabel.getForeground());
    }

    @Test
    void testMissingPasswordShowsErrorMessage() throws Exception {
        JTextField userField = getUsernameField();
        JPasswordField passField = getPasswordField();
        JButton loginButton = getLoginButton();

        // Username filled, password empty
        userField.setText("someUser");
        passField.setText("");

        SwingUtilities.invokeAndWait(loginButton::doClick);

        JLabel msgLabel = getMessageLabel();
        assertEquals("Please enter both fields", msgLabel.getText());
        assertEquals(Color.RED, msgLabel.getForeground());
    }
}
