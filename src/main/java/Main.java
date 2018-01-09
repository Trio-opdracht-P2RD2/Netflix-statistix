import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserInterface());

        printBooks();
    }

    private static void printBooks(){
        SQLConnection connection = new SQLConnection(SQLConnection.SQLAccountName.MSSQLSERVER);
        connection.executeQuery("SELECT TOP 10 * FROM Boek");
    }
}