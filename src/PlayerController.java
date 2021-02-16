import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MVC controller for the Gamebook Player that handles all communication between PlayerView (MVC view) and
 * DatabaseModel (MVC model).
 */
public class PlayerController {
    private DatabaseModel databaseModel;
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

        this.populateUI();
    }

    /**
     * Populates the UI with the latest page body and links from the database.
     */
    public void populateUI() {
        Page page = this.databaseModel.getPage(1);

        this.playerView.setBodyContent(page.getBody());
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
            System.out.println(button.getName());
        }
    }
}
