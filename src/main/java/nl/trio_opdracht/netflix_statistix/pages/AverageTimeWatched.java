package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;

public class AverageTimeWatched extends Page {
    public AverageTimeWatched(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName, "", result -> {
            // TODO: Add views
        });
    }

    @Override public String getTitle() {
        return "Average time watched";
    }
}
