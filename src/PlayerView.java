import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class PlayerView {
    private final JFrame frame;
    private JPanel panel;
    private JTextArea bodyTextArea;
    private JPanel buttonsPanel;
    private JButton playAgainButton;
    private JButton quitButton;

    /**
     * Constructs a player view, sets up UI components and displays the frame.
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
     * @return main frame
     */
    public JFrame getFrame() {
        return frame;
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

        String linkText = link.getText();
        if (linkText == null || linkText.length() < 1) linkText = "Next";
        button.setText(linkText);

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

    /**
     * Sets the visible state of the "Play Again" and "Quit" buttons.
     * @param isVisible visible state
     */
    public void setEndButtonsVisible(boolean isVisible) {
        this.playAgainButton.setVisible(isVisible);
        this.quitButton.setVisible(isVisible);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Play Again" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addPlayAgainButtonListener(ActionListener actionListener) {
        this.playAgainButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Quit" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addQuitButtonListener(ActionListener actionListener) {
        this.quitButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user closes the application window.
     * @param windowListener listener that gets attached when this method is called from EditorController
     */
    public void addWindowCloseListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);
    }
}
