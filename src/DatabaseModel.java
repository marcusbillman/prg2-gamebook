import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Acts as a model that handles reading from and writing to a MySQL database.
 */
public class DatabaseModel {
    private Connection connection;

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
