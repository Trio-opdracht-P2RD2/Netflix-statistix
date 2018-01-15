package nl.trio_opdracht.netflix_statistix;

import java.awt.Color;

import nl.trio_opdracht.netflix_statistix.database.SQLAccountName;

/**
 * This class contains some static values used in the project.
 * This class makes it easy to edit those values.
 */
public class Configuration {
    /**
     * This is the name of your Microsoft SQL Server account.
     * This can differ for everyone.
     * If you have configured MS SQL Server with default settings, use SQLAccountName.MSSQLSERVER
     * If you have configured MS SQL Server with the installation guide of Avans, use SQLAccountName.SQLEXPRESS
     * If you use a different name, change SQLAccountName to String and add the correct name between quotes ""
     */
    public final static SQLAccountName sqlAccountName = SQLAccountName.SQLEXPRESS;

    /**
     * Name of the app, which is shown as title and in the bottom view.
     */
    public final static String appName = "Netflix Statistix";

    /**
     * Name of the database we use.
     */
    public final static String databaseName = "NetflixStatistix";

    /**
     * Creators of the app and database.
     */
    public final static String authors = "Informatica 1A - Marc, Björn, Thomas";
    public final static String authorsLong = "Marc Bouwman (2125922), \nBjörn van Bergen (2128132), \nThomas Buurstede (2122226)";

    /**
     * Path to the sources folder, including the resources.
     */
    public final static String projectPath = System.getProperty("user.dir") + "\\src\\main";

    /**
     * The color of the background of the app.
     * Other parts of the app, like the menu, will use tints of this color.
     */
    public static Color backgroundColor = Color.decode("#EEEEEE");

    /**
     * Font that every view of this app will use, currently the default font of Windows.
     * Also try Arial, or Calibri (this font doesn't show very nicely, but it makes it easier to see it works)
     */
    public final static String defaultFont = "Segoe UI";
}
