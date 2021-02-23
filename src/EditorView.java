import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * View for the editor. Exposes methods for writing to and reading from the UI.
 */
@SuppressWarnings("SameParameterValue")
public class EditorView {
    private final JFrame frame;
    private JPanel panel;

    private JPanel leftPanel;
    private JTable pagesTable;
    private JButton createPageButton;
    private JButton deletePageButton;
    private JButton refreshButton;

    private JPanel rightPanel;
    private JTextArea bodyTextArea;
    private JButton saveBodyButton;
    private JTable linksTable;
    private JButton createLinkButton;
    private JButton deleteLinkButton;
    private JCheckBox endingCheckBox;

    /**
     * Constructs an editor view, sets up UI components and displays the frame.
     * @param title window title
     */
    EditorView(String title) {
        frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();

        // Set up pages table
        DefaultTableModel pagesTableModel = new DefaultTableModel();
        pagesTableModel.addColumn("ID");
        pagesTableModel.addColumn("Body");
        pagesTable.setModel(pagesTableModel);
        pagesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        pagesTable.getColumnModel().getColumn(1).setPreferredWidth(1000);
        pagesTable.setDefaultEditor(Object.class, null); // Disable editing for the table
        pagesTable.getTableHeader().setReorderingAllowed(false); // Disable reordering columns by dragging
        pagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Disable multi-row selection

        // Set up links table
        DefaultTableModel linksTableModel = new DefaultTableModel();
        linksTableModel.addColumn("Text");
        linksTableModel.addColumn("Target Page ID");
        linksTable.setModel(linksTableModel);
        linksTable.setDefaultEditor(Object.class, null); // Disable editing for the table
        linksTable.getTableHeader().setReorderingAllowed(false); // Disable reordering columns by dragging
        linksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Disable multi-row selection

        frame.setVisible(true);
    }

    /**
     * @return main frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return text content of the "Page Body" text area
     */
    public String getBodyContent() {
        return bodyTextArea.getText();
    }

    /**
     * @param content text content of the "Page Body" text area
     */
    public void setBodyContent(String content) {
        bodyTextArea.setText(content);
    }

    /**
     * @return whether the "Page is Ending" checkbox is checked
     */
    public boolean isEndingChecked() {
        return endingCheckBox.isSelected();
    }

    /**
     * @param checked whether the "Page is Ending" checkbox should be checked
     */
    public void setEndingChecked(boolean checked) {
        endingCheckBox.setSelected(checked);
    }

    /**
     * Populates the "Pages" table with all pages and their id and body.
     * @param pages pages to populate with
     */
    public void populatePagesTable(ArrayList<Page> pages) {
        DefaultTableModel tableModel = (DefaultTableModel) pagesTable.getModel();
        tableModel.setRowCount(0);

        for (Page page : pages) {
            tableModel.addRow(new String[]{
                    String.valueOf(page.getId()),
                    page.getBody(),
            });
        }
    }

    /**
     * @return index of the selected row in the "Pages" table
     */
    public int getSelectedPage() {
        return pagesTable.getSelectedRow();
    }

    /**
     * Visually selects a row in the "Pages" table, indicating the currently selected page.
     * @param index index of the row in the "Pages" table
     */
    public void setSelectedPage(int index) {
        try {
            pagesTable.setRowSelectionInterval(index, index);
        } catch (Exception ignored) {
        }
    }

    /**
     * @return index of the selected row in the "Page Links" table
     */
    public int getSelectedLink() {
        return linksTable.getSelectedRow();
    }

    /**
     * Visually selects a row in the "Page Links" table, indicating the currently selected link.
     * @param index index of the row in the "Page Links" table
     */
    public void setSelectedLink(int index) {
        try {
            if (index == -1 || index >= linksTable.getRowCount()) index = linksTable.getRowCount() -1;
            linksTable.setRowSelectionInterval(index, index);
        } catch (Exception ignored) {
        }
    }

    /**
     * Populates the "Page Links" table with all pages and their id and body.
     * @param links links to populate with
     */
    public void populateLinksTable(ArrayList<Link> links) {
        DefaultTableModel tableModel = (DefaultTableModel) linksTable.getModel();
        tableModel.setRowCount(0);

        if (links == null) return;

        for (Link link : links) {
            tableModel.addRow(new String[]{
                    link.getText(),
                    String.valueOf(link.getToPageId())
            });
        }
    }

    /**
     * Sets the enabled state for all UI elements that require a selected page in order to function.
     * @param isEnabled enabled state
     */
    public void setPageRelatedEnabled(boolean isEnabled) {
        deletePageButton.setEnabled(isEnabled);
        bodyTextArea.setEnabled(isEnabled);
        saveBodyButton.setEnabled(isEnabled);
        endingCheckBox.setEnabled(isEnabled);
        linksTable.setEnabled(isEnabled);
        createLinkButton.setEnabled(isEnabled);
        deleteLinkButton.setEnabled(isEnabled);
    }

    /**
     * Sets the enabled state for the "Delete Link" button.
     * @param isEnabled enabled state for the "Delete Link" button
     */
    public void setDeleteLinkButtonEnabled(boolean isEnabled) {
        deleteLinkButton.setEnabled(isEnabled);
    }

    /**
     * Adds a listener for detecting when the user clicks a page in the "Pages" table.
     * @param listSelectionListener listener that gets attached when this method is called from EditorController
     */
    public void addPagesTableListener(ListSelectionListener listSelectionListener) {
        pagesTable.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Create Page" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addCreatePageButtonListener(ActionListener actionListener) {
        createPageButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Delete Page" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addDeletePageButtonListener(ActionListener actionListener) {
        deletePageButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Refresh" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addRefreshButtonListener(ActionListener actionListener) {
        refreshButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Save" button for the page body.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addSaveBodyButtonListener(ActionListener actionListener) {
        saveBodyButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Page is Ending" checkbox.
     * @param mouseListener listener that gets attached when this method is called from EditorController
     */
    public void addEndingCheckBoxListener(MouseListener mouseListener) {
        endingCheckBox.addMouseListener(mouseListener);
    }

    /**
     * Adds a listener for detecting when the user clicks a link in the "Page Links" table.
     * @param mouseListener listener that gets attached when this method is called from EditorController
     */
    public void addLinksTableListener(MouseListener mouseListener) {
        linksTable.addMouseListener(mouseListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Create Link" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addCreateLinkButtonListener(ActionListener actionListener) {
        createLinkButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user clicks the "Delete Link" button.
     * @param actionListener listener that gets attached when this method is called from EditorController
     */
    public void addDeleteLinkButtonListener(ActionListener actionListener) {
        deleteLinkButton.addActionListener(actionListener);
    }

    /**
     * Adds a listener for detecting when the user closes the application window.
     * @param windowListener listener that gets attached when this method is called from EditorController
     */
    public void addWindowCloseListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);
    }

    /**
     * Displays a message dialog to the user.
     * @param message dialog message
     * @param title dialog title
     * @param messageType JOptionPane message type, such as JOptionPane.ERROR_MESSAGE
     */
    void showMessageDialog(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    /**
     * Displays an input dialog to the user, retrieving text input.
     * @param message dialog message above input field
     * @param title dialog title
     * @param initialValue placeholder value in input field
     * @return text inputted by user
     */
    String showInputDialog(String message, String title, String initialValue) {
        return (String) JOptionPane.showInputDialog(
                frame, message, title, JOptionPane.QUESTION_MESSAGE, null, null, initialValue);
    }
}
