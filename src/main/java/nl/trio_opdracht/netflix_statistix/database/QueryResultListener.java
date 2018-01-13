package nl.trio_opdracht.netflix_statistix.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryResultListener {
    void onQueryResultReceived(ResultSet result) throws SQLException;
}
