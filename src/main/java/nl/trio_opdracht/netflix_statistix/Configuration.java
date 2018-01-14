package nl.trio_opdracht.netflix_statistix;

import java.awt.Color;

import nl.trio_opdracht.netflix_statistix.database.SQLAccountName;

public class Configuration {
    public final static SQLAccountName sqlAccountName = SQLAccountName.MSSQLSERVER;

    public final static String appName = "Netflix Statistix";

    public final static String databaseName = "NetflixStatistix";

    public final static String authors = "Informatica 1A - Marc, Björn, Thomas";

    public final static String projectPath = System.getProperty("user.dir") + "\\src\\main";

    public final static Color backgroundColor = Color.decode("#EEEEEE");

    public final static Color tintColor = Color.decode("#E50914");

    public final static String defaultFont = "Segoe UI";
}
