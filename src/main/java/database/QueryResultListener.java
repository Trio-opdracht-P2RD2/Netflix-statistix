package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryResultListener {
    void onQueryResultReceived(ResultSet result) throws SQLException;
}
