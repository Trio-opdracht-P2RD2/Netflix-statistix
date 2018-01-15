package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.database.SQLConnection;

public class EmptyPage extends Page {
    public EmptyPage(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {

    }

    @Override public String getTitle() {
        return null;
    }
}
