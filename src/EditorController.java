import java.util.ArrayList;

/**
 * MVC controller for the Gamebook Editor that handles all communication between EditorView (MVC view) and
 * DatabaseModel (MVC model).
 */
public class EditorController {
    private DatabaseModel databaseModel;
    private final EditorView editorView;
    private int currentPageId;

    /**
     * Constructs the controller.
     * @param databaseModel model that handles reading from and writing to a MySQL database
     * @param editorView view for the Gamebook Editor app
     */
    public EditorController(DatabaseModel databaseModel, EditorView editorView) {
        this.databaseModel = databaseModel;
        this.editorView = editorView;

        ArrayList<Page> pages = this.databaseModel.getAllPages();
        Page page = pages.get(0);
        this.currentPageId = page.getId();

        this.editorView.populatePagesTable(pages);
        this.editorView.setBodyContent(page.getBody());
        this.editorView.populateLinksTable(page.getLinks());
    }
}
