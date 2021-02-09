import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * View for the editor. Exposes methods for writing to and reading from the UI.
 */
public class EditorView {
    private final JFrame frame;
    private JPanel panel;

    private JPanel leftPanel;
    private JTable pagesTable;
    private JButton createPageButton;
    private JButton deletePageButton;

    private JPanel rightPanel;
    private JTextArea bodyTextArea;
    private JButton saveBodyButton;
    private JTable linksTable;
    private JButton createLinkButton;
    private JButton deleteLinkButton;

    /**
     * Constructs an editor view, packs the UI components and displays the
     * frame.
     * @param title window title
     */
    EditorView(String title) {
        this.frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();

        // Set up columns for pages table
        DefaultTableModel pagesTableModel = new DefaultTableModel();
        pagesTableModel.addColumn("ID");
        pagesTableModel.addColumn("Body");
        this.pagesTable.setModel(pagesTableModel);
        this.pagesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.pagesTable.getColumnModel().getColumn(1).setPreferredWidth(1000);
        this.pagesTable.setDefaultEditor(Object.class, null); // Disable editing for the table

        // Set up columns for links table
        DefaultTableModel linksTableModel = new DefaultTableModel();
        linksTableModel.addColumn("Text");
        linksTableModel.addColumn("To Page ID");
        this.linksTable.setModel(linksTableModel);
        this.linksTable.setDefaultEditor(Object.class, null); // Disable editing for the table

        frame.setVisible(true);
    }

    /**
     * @return main frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return text content of 'Page Body' text area
     */
    public String getBodyContent() {
        return bodyTextArea.getText();
    }

    /**
     * @param content text content of 'Page Body' text area
     */
    public void setBodyContent(String content) {
        bodyTextArea.setText(content);
    }

    /**
     * Populates the 'Pages' table with all pages and their id and body.
     * @param pages pages to populate with
     */
    public void populatePagesTable(ArrayList<Page> pages) {
        System.out.println("populating");
        DefaultTableModel tableModel = (DefaultTableModel) this.pagesTable.getModel();
        tableModel.setRowCount(0);

        for (Page page : pages) {
            tableModel.addRow(new String[]{
                    String.valueOf(page.getId()),
                    page.getBody(),
            });
        }
    }

    /**
     * Populates the 'Page Links' table with all pages and their id and body.
     * @param links links to populate with
     */
    public void populateLinksTable(ArrayList<Link> links) {
        DefaultTableModel tableModel = (DefaultTableModel) this.linksTable.getModel();
        tableModel.setRowCount(0);

        for (Link link : links) {
            tableModel.addRow(new String[]{
                    link.getText(),
                    String.valueOf(link.getToPageId())
            });
        }
    }

    /**
     * Adds a listener for detecting when the user clicks a page in the 'Pages' table.
     * @param mouseListener listener that gets attached when this method is called from EditorController
     */
    public void addPagesTableListener(MouseListener mouseListener) {
        this.pagesTable.addMouseListener(mouseListener);
    }

    /**
     * Adds a listener for detecting when the user clicks a link in the 'Page Links' table.
     * @param mouseListener listener that gets attached when this method is called from EditorController
     */
    public void addLinksTableListener(MouseListener mouseListener) {
        this.linksTable.addMouseListener(mouseListener);
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
