import javax.swing.*;

public class PlayerView {
    private final JFrame frame;
    private JPanel panel;

    /**
     * Constructs a player view, packs the UI components and displays the
     * frame.
     * @param title window title
     */
    PlayerView(String title) {
        this.frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }
}
