package pages;

import database.SQLConnection;

public class AverageTimeWatched extends Page {
    public AverageTimeWatched(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override public void showPage() {
        //
    }

    @Override public String getTitle() {
        return "Average time watched";
    }
}
