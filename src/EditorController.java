/**
 * MVC controller for the Gamebook Editor that handles all communication between EditorView (MVC view) and
 * DatabaseModel (MVC model).
 */
public class EditorController {
    private DatabaseModel databaseModel;
    private final EditorView editorView;

    /**
     * Constructs the controller.
     * @param databaseModel model that handles reading from and writing to a MySQL database
     * @param editorView view for the Gamebook Editor app
     */
    public EditorController(DatabaseModel databaseModel, EditorView editorView) {
        this.databaseModel = databaseModel;
        this.editorView = editorView;

        this.editorView.populatePagesTable(this.databaseModel.getAllPages());
    }
}
