import java.sql.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBSelect {
    private static Connection connection = null;
    public static void select()	{
        try{
            connection = DBConnector.connect();
            updateRecords();
        } catch(SQLException e){
            System.out.println("Error: " + e);
            while((e = e.getNextException()) != null){
            }
        } catch(IOException e){
            System.out.println("IO Erro: " + e);
        }
        finally{
            DBConnector.close(connection);
        }
    }
    private static void updateRecords()
            throws SQLException, IOException {
        String query = "SELECT * FROM t_klient";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        System.out.println("Zawartosc calej tabeli:");

        while(rs.next()){
            System.out.print(rs.getInt("id_klienta") + ". ");
            System.out.print("Imie:" + rs.getString("imie"));
            System.out.println(", Nazwisko:" + rs.getString("nazwisko"));
            System.out.print("Numer telefonu:" + rs.getString("telefon"));
            System.out.println(", Miasto:" + rs.getString("miasto"));
        }

        /* =====================OUTPUT

        Zawartosc calej tabeli:
        1. Imie:Jan, Nazwisko:Kowalski
        Numer telefonu:624-45-56, Miasto:W-wa
        2. Imie:Tadeusz, Nazwisko:Malinowski
        Numer telefonu:624-42-33, Miasto:W-wa
        3. Imie:Krystyna, Nazwisko:Torbicka
        Numer telefonu:624-22-11, Miasto:W-wa
        4. Imie:Anna, Nazwisko:Marzec
        Numer telefonu:744-34-34, Miasto:B-stok
        5. Imie:Adam, Nazwisko:Kepinski
        Numer telefonu:756-34-33, Miasto:B-stok

         */


        rs = statement.executeQuery("SELECT imie,nazwisko  FROM t_klient"
                + " WHERE imie like 'A%' "
        );
        System.out.println("\n\rKlienci z imieniem na lietre A:");
        while(rs.next()){
            System.out.print("Imie: " + rs.getString("imie"));
            System.out.println(", nazwisko: " + rs.getString("nazwisko"));
        }

        /*
        ===============================OUTPUT
        Klienci z imieniem na lietre A:
        Imie:Anna, nazwisko:Marzec
        Imie:Adam, nazwisko:Kepinski
         */

        statement.close();
    }
}