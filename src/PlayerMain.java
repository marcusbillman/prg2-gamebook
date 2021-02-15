/**
 * Contains the main() method which is the entry point for the Gamebook Player app
 */
public class PlayerMain {
    /**
     * Entry point for the Gamebook Player app
     * @param args unused arguments
     */
    public static void main(String[] args) {
        DatabaseModel databaseModel = new DatabaseModel();
        PlayerView playerView = new PlayerView("Gamebook Player");
        new PlayerController(databaseModel, playerView);
    }
}
