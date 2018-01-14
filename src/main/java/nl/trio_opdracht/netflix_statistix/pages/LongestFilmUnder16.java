package nl.trio_opdracht.netflix_statistix.pages;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class LongestFilmUnder16 extends Page {
    public LongestFilmUnder16(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {
        getContentView().setLayout(BoxLayout.Y_AXIS);
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT TOP 1 Titel\n" +
                "FROM Film\n" +
                "WHERE CAST(SUBSTRING(Leeftijdsindicatie, 0, CHARINDEX(' jaar en ouder', Leeftijdsindicatie)) AS INT) < 16\n" +
                "ORDER BY Tijdsduur DESC", results -> {
                    while(results.next()) getContentView().add(new TextView(results.getString("Titel")));
                });
    }

    @Override public String getTitle() {
        return "Langste film (16- jaar)";
    }
}
