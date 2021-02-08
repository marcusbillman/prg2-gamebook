import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        // Set up listeners
        this.editorView.addPagesTableListener(new PagesTableListener());
    }

    /**
     * Custom listener based on ActionListener that updates the UI after a page has been selected by the user.
     */
    private class PagesTableListener implements MouseListener {
        /**
         * Updates the UI after a page has been selected by the user.
         * @param mouseEvent event that invokes the listener
         */
        public void mouseClicked(MouseEvent mouseEvent) {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int index = table.rowAtPoint(point);

            if (table.getSelectedRow() != -1) {
                Page page = databaseModel.getPagesCache().get(index);
                currentPageId = page.getId();
                editorView.setBodyContent(page.getBody());
                editorView.populateLinksTable(page.getLinks());
            }
        }

        public void mousePressed(MouseEvent mouseEvent) {
        }

        public void mouseReleased(MouseEvent mouseEvent) {
        }

        public void mouseEntered(MouseEvent mouseEvent) {
        }

        public void mouseExited(MouseEvent mouseEvent) {
        }
    }
}
