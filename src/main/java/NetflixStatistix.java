import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import database.SQLAccountName;
import database.SQLConnection;
import ui.UserInterface;

public class NetflixStatistix {
    public static void main(String[] args) {
        System.out.println("Welcome to Netflix Statistix!");

        SQLConnection sqlConnection = new SQLConnection(SQLAccountName.MSSQLSERVER);

        HashMap<String, ActionListener> menuItems = new HashMap<>();
        menuItems.put("Print movies", actionEvent -> printMovies(sqlConnection));

        SwingUtilities.invokeLater(new UserInterface(menuItems));
    }

    private static void printMovies(SQLConnection sqlConnection){
        System.out.println();
        sqlConnection.executeQuery(
                "NetflixStatistix",
                "SELECT *\n" +
                "FROM Bekeken\n" +
                "INNER JOIN Film ON Film.ID = Bekeken.Gezien;",
                result -> {
                    System.out.println(String.format("| %7s | %-32s | %-24s |", " ", " ", " ").replace(" ", "-"));
                    System.out.println(String.format("| %7s | %-32s | %-24s |", "ID", "Titel", "Genre"));
                    System.out.println(String.format("| %7s | %-32s | %-24s |", " ", " ", " ").replace(" ", "-"));

                    // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
                    while (result.next()) {
                        // Vraag per row de kolommen in die row op.
                        int ISBN = result.getInt("ID");
                        String title = result.getString("Titel");
                        String author = result.getString("Genre");

                        // Met 'format' kun je de string die je print het juiste formaat geven, als je dat wilt.
                        // %d = decimal, %s = string, %-32s = string, links uitgelijnd, 32 characters breed.
                        System.out.format("| %7d | %-32s | %-24s | \n", ISBN, title, author);
                    }
                    System.out.println(String.format("| %7s | %-32s | %-24s |", " ", " ", " ").replace(" ", "-"));
                });
    }
}