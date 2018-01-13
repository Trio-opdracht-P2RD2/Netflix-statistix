package nl.trio_opdracht.netflix_statistix.pages;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class AverageTimeWatched extends Page {
    private static final String query =
            "SELECT *\n" +
            "FROM Bekeken\n" +
            "INNER JOIN Film ON Film.ID = Bekeken.Gezien;";

    public AverageTimeWatched(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {
        getContentView().setLayout(BoxLayout.Y_AXIS);
        getSqlConnection().executeQuery(Configuration.databaseName, query, result -> {
            while (result.next()) getContentView().add(new TextView(result.getInt("ID") + ": " + result.getString("Titel") + ", " + result.getString("Genre")));
            getContentView().updateUI();
        });
    }

    @Override public String getTitle() {
        return "Average time watched";
    }
}
