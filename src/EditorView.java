import javax.swing.*;

/**
 * View for the editor. Exposes methods for writing to and reading from the UI.
 */
public class EditorView {
    private final JFrame frame;
    private JPanel panel;
    private JTextArea bodyTextArea;
    private JButton saveBodyButton;
    private JTable linksTable;
    private JButton deleteLinkButton;
    private JButton createLinkButton;
    private JTable pagesTable;

    /**
     * Constructs an editor view, packs the UI components and displays the
     * frame.
     * @param title window title
     */
    EditorView(String title) {
        this.frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    /**
     * @return main frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return text content of 'Page Body' text area
     */
    public String getBodyContent() {
        return bodyTextArea.getText();
    }

    /**
     * @param content text content of 'Page Body' text area
     */
    public void setBodyContent(String content) {
        bodyTextArea.setText(content);
    }

    /**
     * Displays a message dialog to the user.
     * @param message dialog message
     * @param title dialog title
     * @param messageType JOptionPane message type, such as JOptionPane.ERROR_MESSAGE
     */
    void showMessageDialog(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    /**
     * Displays an input dialog to the user, retrieving text input.
     * @param message dialog message above input field
     * @param title dialog title
     * @param initialValue placeholder value in input field
     * @return text inputted by user
     */
    String showInputDialog(String message, String title, String initialValue) {
        return (String) JOptionPane.showInputDialog(
                frame, message, title, JOptionPane.QUESTION_MESSAGE, null, null, initialValue);
    }
}
