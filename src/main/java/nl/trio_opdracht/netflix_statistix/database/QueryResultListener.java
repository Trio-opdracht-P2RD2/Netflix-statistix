package nl.trio_opdracht.netflix_statistix.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface notices the requesting method that the results of the database query have been received.
 */
public interface QueryResultListener {
    void onQueryResultReceived(ResultSet result) throws SQLException;
}
