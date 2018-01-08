import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnection {
    public final static String SQLEXPRESS = "SQLEXPRESS";
    public final static String MSSQLSERVER = "MSSQLSERVER";

    public void init() {
        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
        String connectionUrl = "jdbc:sqlserver://localhost\\" + SQLEXPRESS + ";databaseName=Bibliotheek;integratedSecurity=true;";

        // Connection beheert informatie over de connectie met de database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;

        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "SELECT TOP 10 * FROM Boek";
            stmt = con.createStatement();
            // Voer de query uit op de database.
            rs = stmt.executeQuery(SQL);

            System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));
            System.out.print(String.format("| %7s | %-32s | %-24s |\n", "ISBN", "Titel", "Auteur"));
            System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (rs.next()) {
                // Vraag per row de kolommen in die row op.
                int ISBN = rs.getInt("ISBN");
                String title = rs.getString("Titel");
                String author = rs.getString("Auteur");

                // Print de kolomwaarden.
                // System.out.println(ISBN + " " + title + " " + author);

                // Met 'format' kun je de string die je print het juiste formaat geven, als je dat wilt.
                // %d = decimal, %s = string, %-32s = string, links uitgelijnd, 32 characters breed.
                System.out.format("| %7d | %-32s | %-24s | \n", ISBN, title, author);
            }
            System.out.println(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}
