package nl.trio_opdracht.netflix_statistix;

import javax.swing.SwingUtilities;

import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.pages.AverageTimeWatched;
import nl.trio_opdracht.netflix_statistix.pages.FilmsWatched;
import nl.trio_opdracht.netflix_statistix.ui.UserInterface;

public class NetflixStatistix {
    public static void main(String[] args) {
        SQLConnection sqlConnection = new SQLConnection(Configuration.sqlAccountName);
        SwingUtilities.invokeLater(new UserInterface(new AverageTimeWatched(sqlConnection), new FilmsWatched(sqlConnection)));
    }
}