import javax.swing.SwingUtilities;

import database.SQLAccountName;
import database.SQLConnection;
import ui.UserInterface;

public class NetflixStatistix {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserInterface());

        //printBooks();
    }

    private static void printBooks(){
        SQLConnection connection = new SQLConnection(SQLAccountName.MSSQLSERVER);
        connection.executeQuery("Bibliotheek", "SELECT TOP 10 * FROM Boek");
    }
}