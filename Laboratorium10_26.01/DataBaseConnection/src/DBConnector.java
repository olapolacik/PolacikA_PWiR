
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String URL = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static String user = "root";
    private static String password = "ola16082002";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Połaczono");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            while ((e = e.getNextException()) != null) {
            }
            System.exit(-1);
        }
    }

}