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
    }
}
