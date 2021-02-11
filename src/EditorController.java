import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        this.refreshPages();

        // Set up listeners
        this.editorView.addPagesTableListener(new PagesTableListener());
        this.editorView.addCreatePageButtonListener(new CreatePageButtonListener());
        this.editorView.addSaveBodyButtonListener(new SaveBodyButtonListener());
        this.editorView.addLinksTableListener(new LinksTableListener());
        this.editorView.addCreateLinkButtonListener(new CreateLinkButtonListener());
        this.editorView.addWindowCloseListener(new WindowCloseListener());
    }

    /**
     * Updates the 'Pages' table to reflect the latest list of pages from the database.
     */
    private void refreshPages() {
        ArrayList<Page> pages = this.databaseModel.getAllPages();
        this.editorView.populatePagesTable(pages);
    }

    /**
     * Updates the all UI components related to the currently selected page to reflect the latest data from the
     * database.
     * @param pageId id of the selected page
     */
    private void populatePagePanel(int pageId) {
        this.currentPageId = pageId;

        Page page = this.databaseModel.getPage(this.currentPageId);
        this.editorView.setBodyContent(page.getBody());
        this.editorView.populateLinksTable(page.getLinks());
    }

    /**
     * Custom listener based on MouseListener that updates the UI after a page has been selected by the user.
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

            if (table.getSelectedRow() == -1 || index == -1) return;

            int pageId = databaseModel.getPagesCache().get(index).getId();
            populatePagePanel(pageId);
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
     * Custom listener based on ActionListener that detects when the 'Create Page' has been clicked by the user.
     */
    private class CreatePageButtonListener implements ActionListener {
        /**
         * Creates a new page after the 'Create Page' button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            databaseModel.createPage();
            refreshPages();

            // Select the last page
            editorView.selectPage(-1);
            ArrayList<Page> pagesCache = databaseModel.getPagesCache();
            int pageId = pagesCache.get(pagesCache.size() - 1).getId();
            populatePagePanel(pageId);
        }
    }

    /**
     * Custom listener based on ActionListener that detects when the 'Save' button for the page body has been clicked
     * by the user.
     */
    private class SaveBodyButtonListener implements ActionListener {
        /**
         * Saves the body text that the user has edited.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            databaseModel.updatePageBody(currentPageId, editorView.getBodyContent());
            populatePagePanel(currentPageId);
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

            if (mouseEvent.getClickCount() < 2 || table.getSelectedRow() == -1 || index == -1) return;

            Link link = databaseModel.getLinksCache().get(index);

            if (table.getSelectedColumn() == 0) {
                String newText = editorView.showInputDialog(
                        "Enter new link text", "Edit Link Text", link.getText());
                if (newText == null) return;
                databaseModel.updateLinkText(index, newText);
            } else {
                String userInput = editorView.showInputDialog(
                        "Enter new target page id", "Edit Link Target", String.valueOf(link.getToPageId()));
                if (userInput == null || !userInput.matches("\\d+")) return;
                databaseModel.updateLinkToPageId(index, Integer.parseInt(userInput));
            }

            populatePagePanel(currentPageId);
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
     * Custom listener based on ActionListener that detects when the 'Create Link' has been clicked by the user.
     */
    private class CreateLinkButtonListener implements ActionListener {
        /**
         * Creates a new link after the 'Create Link' button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            databaseModel.createLink(currentPageId);
            populatePagePanel(currentPageId);
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
            databaseModel.closeConnection();
            editorView.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
