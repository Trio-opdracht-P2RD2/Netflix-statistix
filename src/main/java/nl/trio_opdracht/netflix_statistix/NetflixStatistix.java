package nl.trio_opdracht.netflix_statistix;

import javax.swing.SwingUtilities;

import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.pages.AccountsWithOneProfile;
import nl.trio_opdracht.netflix_statistix.pages.AverageTimeWatched;
import nl.trio_opdracht.netflix_statistix.pages.FilmsWatched;
import nl.trio_opdracht.netflix_statistix.pages.LongestFilmUnder16;
import nl.trio_opdracht.netflix_statistix.pages.TimesFullyWatchedFilm;
import nl.trio_opdracht.netflix_statistix.ui.UserInterface;

/**
 * This is the main class of the app.
 * In this class, we connect the database, pages and UI classes and show the result.
 * To start the app, click on the green play button below.
 */
public class NetflixStatistix {
    public static void main(String[] args) {
        SQLConnection sqlConnection = new SQLConnection(Configuration.sqlAccountName);// Creates a connection class between Java and the database (see /database/)

        // Creates an UI (see /ui/) with several Pages (see /pages/), which use the same SQLConnection (see /database/) and runs it on a seperate thread.
        SwingUtilities.invokeLater(new UserInterface(
                new AverageTimeWatched(sqlConnection),
                new FilmsWatched(sqlConnection),
                new LongestFilmUnder16(sqlConnection),
                new AccountsWithOneProfile(sqlConnection),
                new TimesFullyWatchedFilm(sqlConnection)));
    }
}