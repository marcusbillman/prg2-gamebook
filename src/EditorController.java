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
        this.currentPageId = -1;

        // Populate UI
        ArrayList<Page> pages = this.databaseModel.getAllPages();
        this.editorView.populatePagesTable(pages);

        // Set up listeners
        this.editorView.addPagesTableListener(new PagesTableListener());
        this.editorView.addLinksTableListener(new LinksTableListener());
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

    /**
     * Custom listener based on MouseListener that detects when a page link has been clicked by the user, letting
     * them edit either the link text or the target page id
     */
    private class LinksTableListener implements MouseListener {
        /**
         * Lets the user edit either the link text or the target page id of a clicked link.
         * @param mouseEvent event that invokes the listener
         */
        public void mouseClicked(MouseEvent mouseEvent) {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int index = table.rowAtPoint(point);

            if (table.getSelectedRow() != -1) {
                Link link = databaseModel.getLinksCache().get(index);
                String newText = editorView.showInputDialog(
                        "Enter new link text", "Edit Link Text", link.getText());

                if (newText == null) return;
                databaseModel.updateLinkText(index, newText);
                refreshPageView();
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
