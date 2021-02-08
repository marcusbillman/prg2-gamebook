import java.sql.*;
import java.util.ArrayList;

/**
 * Acts as a model that handles reading from and writing to a MySQL database.
 */
public class DatabaseModel {
    private Connection connection;
    private ArrayList<Page> pagesCache;
    private ArrayList<Link> linksCache;

    public ArrayList<Page> getPagesCache() {
        return pagesCache;
    }

    public ArrayList<Link> getLinksCache() {
        return linksCache;
    }

    /**
     * Establishes a connection to a MySQL database using the credentials in the DatabaseLoginData class.
     */
    public DatabaseModel() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://" + DatabaseLoginData.address + ":" + DatabaseLoginData.port + "/" +
                            DatabaseLoginData.database +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.username, DatabaseLoginData.password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Page> getAllPages() {
        ArrayList<Page> pages = new ArrayList<>();

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM pages";
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through the result set and populate the pages ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("page_id");
                String body = resultSet.getString("body");
                boolean isEnding = resultSet.getBoolean("is_ending");
                ArrayList<Link> links = getLinksFromPage(id);

                pages.add(new Page(id, body, links, isEnding));
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.pagesCache = pages;
        return pages;
    }

    public ArrayList<Link> getLinksFromPage(int fromPageId) {
        ArrayList<Link> links = new ArrayList<>();

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM links WHERE from_page_id = " + fromPageId;
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through the result set and populate the links ArrayList
            while (resultSet.next()) {
                String text = resultSet.getString("text");
                int toPageId = resultSet.getInt("to_page_id");
                links.add(new Link(fromPageId, toPageId, text));
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.linksCache = links;
        return links;
    }

    /**
     * Closes the database connection safely.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
