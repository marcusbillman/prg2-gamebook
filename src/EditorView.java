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
}
