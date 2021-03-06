import java.sql.*;
import java.util.ArrayList;

/**
 * Model that handles reading from and writing to a MySQL database.
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
     * Constructs a DatabaseModel and establishes a connection to a MySQL database using the credentials in the
     * DatabaseLoginData class.
     */
    public DatabaseModel() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + DatabaseLoginData.address + ":" + DatabaseLoginData.port + "/" +
                            DatabaseLoginData.database +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.username, DatabaseLoginData.password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Fetches all pages from the database. Doesn't include links.
     * @return all pages in the database
     */
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

                pages.add(new Page(id, body, isEnding));
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        pagesCache = pages;
        return pages;
    }

    /**
     * Fetches a page with a given id from the database. Includes links pointing from that page.
     * @param pageId id of the desired page
     * @return the desired page, or null if the SQL query encountered an error
     */
    public Page getPage(int pageId) {
        Page page = null;

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM pages WHERE page_id = " + pageId;
            ResultSet resultSet = statement.executeQuery(query);

            // Get data from first result row
            resultSet.next();
            int id = resultSet.getInt("page_id");
            String body = resultSet.getString("body");
            boolean isEnding = resultSet.getBoolean("is_ending");

            // Get all links that point from this page
            ArrayList<Link> links = getLinksFromPage(id);

            // Create a Page object
            page = new Page(id, body, links, isEnding);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return page;
    }

    /**
     * Fetches all links that point from a given page.
     * @param fromPageId id of the page from which the link points
     * @return all links that point from the given page
     */
    public ArrayList<Link> getLinksFromPage(int fromPageId) {
        ArrayList<Link> links = new ArrayList<>();

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM links WHERE from_page_id = " + fromPageId;
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through the result set and populate the links ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("link_id");
                String text = resultSet.getString("text");
                int toPageId = resultSet.getInt("to_page_id");
                links.add(new Link(id, fromPageId, toPageId, text));
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        linksCache = links;
        return links;
    }

    /**
     * Inserts a blank page into the database.
     */
    public void createPage() {
        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "INSERT INTO pages (body, is_ending) VALUES ('', 0)";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inserts a blank link into the database. By default, the link points from and to the same page.
     * @param fromPageId id of the page from which the link should point
     */
    public void createLink(int fromPageId) {
        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "INSERT INTO links (from_page_id, to_page_id)" +
                    "VALUES (" + fromPageId + ", " + fromPageId + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the body text of a page in the database.
     * @param pageId id of the page
     * @param body new body text to update with
     */
    public void updatePageBody(int pageId, String body) {
        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "UPDATE pages SET body='" + body + "' WHERE page_id=" + pageId;
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the ending attribute of a page in the database.
     * @param pageId id of the page
     * @param isEnding whether to treat this page as an ending of the gamebook
     */
    public void updatePageIsEnding(int pageId, boolean isEnding) {
        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "UPDATE pages SET is_ending=" + (isEnding ? 1 : 0) + " WHERE page_id=" + pageId;
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the text of a link in the database.
     * @param index index of the link in the links cache
     * @param text new text to update with
     */
    public void updateLinkText(int index, String text) {
        Link link = linksCache.get(index);

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "UPDATE links SET text='" + text + "' WHERE link_id=" + link.getId();
            statement.executeUpdate(query);
            statement.close();

            link.setText(text);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the target page id of a link in the database.
     * @param index index of the link in the links cache
     * @param toPageId new target page id to update with
     */
    public void updateLinkToPageId(int index, int toPageId) {
        Link link = linksCache.get(index);

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "UPDATE links SET to_page_id='" + toPageId + "' WHERE link_id=" + link.getId();
            statement.executeUpdate(query);
            statement.close();

            link.setToPageId(toPageId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes a page from the database.
     * @param index index of the page in the pages cache
     */
    public void deletePage(int index) {
        Page page = pagesCache.get(index);

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "DELETE FROM pages WHERE page_id=" + page.getId();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes a link from the database.
     * @param index index of the link in the links cache
     */
    public void deleteLink(int index) {
        Link link = linksCache.get(index);

        try {
            // Setup statement and execute query
            Statement statement = connection.createStatement();
            String query = "DELETE FROM links WHERE link_id=" + link.getId();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
}
