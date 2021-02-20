import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
