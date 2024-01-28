import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        //runTest();
        DBCreateTable.createTable();
        //DBInsert.insert();
        Connection connection = DBConnector.connect();
        DBSelect.select();

        DBConnector.close(connection);

    }
    public static void runTest() throws SQLException
    {
        Connection conn = null;
        try {
            conn = DBConnector.connect();
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Witaj, świecie!')");

            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
            {
                if (result.next())
                    System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
        finally {
            DBConnector.close(conn);
        }
    }
}