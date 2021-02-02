import javax.swing.*;

/**
 * View for the editor. Exposes methods for writing to and reading from the UI.
 */
public class EditorView {
    private final JFrame frame;
    private JPanel panel;

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
}
