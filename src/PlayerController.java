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
     * Constructs the controller, populates the UI and sets up listeners.
     * @param databaseModel model that handles reading from and writing to a MySQL database
     * @param playerView view for the Gamebook Player app
     */
    public PlayerController(DatabaseModel databaseModel, PlayerView playerView) {
        this.databaseModel = databaseModel;
        this.playerView = playerView;
        currentPageId = -1;

        goToPage(1);

        // Set up listeners
        this.playerView.addPlayAgainButtonListener(new PlayerController.PlayAgainButtonListener());
        this.playerView.addQuitButtonListener(new PlayerController.QuitButtonListener());
        this.playerView.addWindowCloseListener(new PlayerController.WindowCloseListener());
    }

    /**
     * Populates the UI with the latest page body and links from the database.
     */
    public void goToPage(int pageId) {
        Page page = databaseModel.getPage(pageId);

        currentPageId = pageId;

        playerView.setBodyContent(page.getBody());

        playerView.removeLinkButtons();
        for (Link link : page.getLinks()) {
            playerView.generateLinkButton(link, new LinkButtonListener());
        }

        playerView.setEndButtonsVisible(page.isEnding());
    }

    /**
     * Exits the program safely by closing the database connection.
     */
    private void quit() {
        databaseModel.closeConnection();
        playerView.getFrame().dispose();
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
     * Custom listener based on ActionListener that detects when the "Play Again" button has been clicked by the user.
     */
    private class PlayAgainButtonListener implements ActionListener {
        /**
         * Visits the first page of the gamebook after the "Play Again" button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            goToPage(1);
        }
    }

    /**
     * Custom listener based on ActionListener that detects when the "Quit" button has been clicked by the user.
     */
    private class QuitButtonListener implements ActionListener {
        /**
         * Visits the first page of the gamebook after the "Quit" button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            quit();
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
            quit();
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
