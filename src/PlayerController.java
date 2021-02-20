import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * MVC controller for the Gamebook Player that handles all communication between PlayerView (MVC view) and
 * DatabaseModel (MVC model).
 */
public class PlayerController {
    private final DatabaseModel databaseModel;
    private final PlayerView playerView;
    private int currentPageId;

    /**
     * Constructs the controller.
     * @param databaseModel model that handles reading from and writing to a MySQL database
     * @param playerView view for the Gamebook Player app
     */
    public PlayerController(DatabaseModel databaseModel, PlayerView playerView) {
        this.databaseModel = databaseModel;
        this.playerView = playerView;
        this.currentPageId = -1;

        this.goToPage(1);

        this.playerView.addWindowCloseListener(new PlayerController.WindowCloseListener());
    }

    /**
     * Populates the UI with the latest page body and links from the database.
     */
    public void goToPage(int pageId) {
        Page page = this.databaseModel.getPage(pageId);

        this.currentPageId = pageId;

        this.playerView.setBodyContent(page.getBody());

        this.playerView.removeLinkButtons();
        for (Link link : page.getLinks()) {
            this.playerView.generateLinkButton(link, new LinkButtonListener());
        }

        if (page.isEnding()) {
            this.playerView.setEndButtonsVisible(true);
        } else {
            this.playerView.setEndButtonsVisible(false);
        }
    }

    /**
     * Custom listener based on ActionListener that detects when any link button has been clicked by the user.
     */
    private class LinkButtonListener implements ActionListener {
        /**
         * Visits the page that corresponds to the link button that was clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            int pageId = Integer.parseInt(button.getName());

            goToPage(pageId);
        }
    }

    /**
     * Custom listener based on WindowListener that detects when the user closes the application window, safely
     * exiting the application.
     */
    private class WindowCloseListener implements WindowListener {
        public void windowOpened(WindowEvent windowEvent) {
        }

        /**
         * Exits the program safely when the window is closed by the user. Closes the database connection.
         * @param windowEvent event that invokes the listener
         */
        public void windowClosing(WindowEvent windowEvent) {
            databaseModel.closeConnection();
            playerView.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void windowClosed(WindowEvent windowEvent) {
        }

        public void windowIconified(WindowEvent windowEvent) {
        }

        public void windowDeiconified(WindowEvent windowEvent) {
        }

        public void windowActivated(WindowEvent windowEvent) {
        }

        public void windowDeactivated(WindowEvent windowEvent) {
        }
    }
}
