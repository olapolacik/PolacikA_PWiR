import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class DBCreateTable {
    private static Connection connection = null;
    public static void createTable()	{
        try{
            connection = DBConnector.connect();
            updateRecords();
        }
        catch(SQLException e){
            System.err.println("Error: " + e);
            while((e = e.getNextException()) != null){
            }
        }
        catch(IOException e){
            System.out.println("IO Error: " + e);
        }
        finally{
            DBConnector.close(connection);
        }
    }
    private static void updateRecords()
            throws SQLException, IOException {
        String query = "CREATE TABLE Titles "
                + "(title_id INTEGER not null primary key, title_name VARCHAR(50), "
                + "rating VARCHAR(5), price FLOAT, quantity INTEGER, "
                + "type_id INTEGER , category_id INTEGER)";
        //String query_delete = "DROP TABLE Titles"; //
        Statement statement = connection.createStatement();
        //statement.executeUpdate(query_delete);
        statement.executeUpdate(query);
        statement.close();
    }
}
