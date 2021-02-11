import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        this.editorView.addDeletePageButtonListener(new DeletePageButtonListener());
        this.editorView.addSaveBodyButtonListener(new SaveBodyButtonListener());
        this.editorView.addEndingCheckBoxListener(new EndingCheckBoxListener());
        this.editorView.addLinksTableListener(new LinksTableListener());
        this.editorView.addCreateLinkButtonListener(new CreateLinkButtonListener());
        this.editorView.addDeleteLinkButtonListener(new DeleteLinkButtonListener());
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
     * Updates all UI components related to the currently selected page to reflect the latest data from the database.
     */
    private void refreshCurrentPage() {
        Page page = this.databaseModel.getPage(this.currentPageId);
        this.editorView.setBodyContent(page.getBody());
        this.editorView.setEndingChecked(page.isEnding());
        this.editorView.populateLinksTable(page.getLinks());
    }

    /**
     * Visually selects a desired page in the 'Pages' table and populates the right panel with page data.
     * @param index index of the page to select based on the pages cache
     */
    private void selectPage(int index) {
        ArrayList<Page> pagesCache = databaseModel.getPagesCache();
        if (index == -1 || index >= pagesCache.size()) index = pagesCache.size() - 1;

        this.currentPageId = pagesCache.get(index).getId();
        this.editorView.setSelectedPage(index);
        refreshCurrentPage();
    }

    /**
     * Custom listener based on ListSelectionListener that updates the UI after a page has been selected by the user.
     */
    private class PagesTableListener implements ListSelectionListener {
        /**
         * Updates the UI after a page has been selected by the user.
         * @param listSelectionEvent event that invokes the listener
         */
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            DefaultListSelectionModel listSelectionModel = (DefaultListSelectionModel) listSelectionEvent.getSource();
            int index = listSelectionModel.getLeadSelectionIndex();

            if (index == -1) return;

            selectPage(index);
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
            selectPage(-1);
        }
    }

    /**
     * Custom listener based on ActionListener that detects when the 'Delete Page' has been clicked by the user.
     */
    private class DeletePageButtonListener implements ActionListener {
        /**
         * Deletes the selected page after the 'Delete Page' button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            int index = editorView.getSelectedPage();

            if (index == -1) return;

            databaseModel.deletePage(index);
            refreshPages();
            selectPage(index);
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
            refreshPages();
            refreshCurrentPage();
        }
    }

    /**
     * Custom listener based on MouseListener that detects when the 'Page is Ending' checkbox has been clicked by the
     * user.
     */
    private class EndingCheckBoxListener implements MouseListener {
        /**
         * Updates the isEnding attribute of the current page when the 'Page is Ending' checkbox has been clicked by
         * the user.
         * @param mouseEvent event that invokes the listener
         */
        public void mouseClicked(MouseEvent mouseEvent) {
            JCheckBox checkBox = (JCheckBox) mouseEvent.getSource();
            databaseModel.updatePageIsEnding(currentPageId, checkBox.isSelected());
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

            refreshCurrentPage();
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
            refreshCurrentPage();
        }
    }

    /**
     * Custom listener based on ActionListener that detects when the 'Delete Link' has been clicked by the user.
     */
    private class DeleteLinkButtonListener implements ActionListener {
        /**
         * Deletes the selected link after the 'Delete Link' button has been clicked by the user.
         * @param actionEvent event that invokes the listener
         */
        public void actionPerformed(ActionEvent actionEvent) {
            int index = editorView.getSelectedLink();

            if (index == -1) return;

            databaseModel.deleteLink(index);
            refreshCurrentPage();
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
