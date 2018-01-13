import javax.swing.SwingUtilities;

import database.SQLAccountName;
import database.SQLConnection;
import ui.UserInterface;

public class NetflixStatistix {
    public static void main(String[] args) {
        System.out.println("Welcome to Netflix Statistix!");
        SwingUtilities.invokeLater(new UserInterface());

        //printBooks();
    }

    private static void printBooks(){
        System.out.println();
        new SQLConnection(SQLAccountName.MSSQLSERVER).executeQuery("Bibliotheek", "SELECT TOP 10 * FROM Boek", result -> {
            System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));
            System.out.print(String.format("| %7s | %-32s | %-24s |\n", "ISBN", "Titel", "Auteur"));
            System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (result.next()) {
                // Vraag per row de kolommen in die row op.
                int ISBN = result.getInt("ISBN");
                String title = result.getString("Titel");
                String author = result.getString("Auteur");

                // Met 'format' kun je de string die je print het juiste formaat geven, als je dat wilt.
                // %d = decimal, %s = string, %-32s = string, links uitgelijnd, 32 characters breed.
                System.out.format("| %7d | %-32s | %-24s | \n", ISBN, title, author);
            }
            System.out.println(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));
        });
    }
}