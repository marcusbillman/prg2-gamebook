/**
 * Contains the main() method which is the entry point for the Gamebook Editor app.
 */
public class EditorMain {
    /**
     * Entry point for the Gamebook Editor app.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        DatabaseModel databaseModel = new DatabaseModel();
        EditorView editorView = new EditorView("Gamebook Editor");
        new EditorController(databaseModel, editorView);
    }
}
