import javax.swing.*;
import java.awt.event.ActionListener;

public class PlayerView {
    private final JFrame frame;
    private JPanel panel;
    private JTextArea bodyTextArea;
    private JPanel buttonsPanel;

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

        this.buttonsPanel.setLayout(new BoxLayout(this.buttonsPanel, BoxLayout.Y_AXIS));

        frame.setVisible(true);
    }

    /**
     * @param content text content of body text area
     */
    public void setBodyContent(String content) {
        bodyTextArea.setText(content);
    }

    /**
     * Appends a link button to the buttons panel.
     * @param link link for which to append a button
     * @param actionListener listener to invoke when the button is clicked
     */
    public void generateLinkButton(Link link, ActionListener actionListener) {
        JButton button = new JButton();
        button.setText(link.getText());
        button.setName(String.valueOf(link.getToPageId()));
        button.addActionListener(actionListener);

        this.buttonsPanel.add(button);
        this.buttonsPanel.validate();
        this.buttonsPanel.repaint();
    }

    /**
     * Removes all link buttons from the buttons panel.
     */
    public void removeLinkButtons() {
        this.buttonsPanel.removeAll();
        this.buttonsPanel.validate();
        this.buttonsPanel.repaint();
    }
}
