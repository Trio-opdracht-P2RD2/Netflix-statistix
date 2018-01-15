package nl.trio_opdracht.netflix_statistix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * This class creates a connection with a database, receives results and closes the connection.
 */
public class SQLConnection {
    private String sqlAccountName;
    private Connection connection;
    private Statement statement;
    private String lastDatabase;

    /**
     * The following constructors set the account name. For configuration options, check out Configuration.java
     */
    public SQLConnection(SQLAccountName sqlAccountName){
        this.sqlAccountName = sqlAccountName.toString();
    }

    public SQLConnection(String customSqlAccountName){
        this.sqlAccountName = customSqlAccountName;
    }

    /**
     * Creates a url to the database
     * @param databaseName The name of the database where we want to connect with.
     * @return a String, which is the URL to the database
     */
    private String createConnectionUrl(String databaseName){
        return "jdbc:sqlserver://localhost\\" + sqlAccountName + ";databaseName=" + databaseName + ";integratedSecurity=true;";
    }

    /**
     * Executes a SQL Query, but does nothing with the result (for UPDATE, DROP).
     * @param database The database where we want to connect with
     * @param sql The SQL Statement we want to execute
     */
    public void executeQuery(String database, String sql) {
        executeQuery(database, sql, null);
    }

    /**
     * Executes a SQL Query and sends the results to the listener
     * @param database The database where we want to connect with
     * @param sql The SQL Statement we want to execute
     * @param resultListener The callback, which gets executed when the result is received. When finished, we can automatically close the ResultSet here again.
     */
    public void executeQuery(String database, String sql, QueryResultListener resultListener) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Imports the driver.
            if(!Objects.equals(lastDatabase, database)){ // Reconnects if the database was changed, or when this is the first time connecting.
                disconnect();
                connectToDatabase(database);
                lastDatabase = database;
            }
            try(ResultSet resultSet = statement.executeQuery(sql)){// Executes the query on the database, receives the results and closes it again when not used.
                if(resultListener != null) resultListener.onQueryResultReceived(resultSet);// When not null, send the results to the listener.
            }
        } catch (ClassNotFoundException e){
            System.out.println("The driver was not found, please add it to the project! Check out File > Project Structure > Libraries.");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace(); // Something went wrong!
        }
    }

    /**
     * Connects with the requested database
     * @param database The database where we want to connect with
     * @throws SQLException when something goes wrong, notify the parent method
     */
    private void connectToDatabase(String database) throws SQLException {
        try {
            connection = DriverManager.getConnection(createConnectionUrl(database)); // Connects with the database
        } catch (Exception e){
            System.out.println("Could not connect with the database. Have you entered the correct account name in Configuration.java?");
            System.exit(0);
        }
        statement = connection.createStatement(); // Generate a statement where we can execute queries on
    }

    /**
     * Tries to close all connections
     */
    public void disconnect(){
        if(statement != null) try{ statement.close(); } catch (SQLException ignored){}
        if(connection != null) try{ connection.close(); } catch (SQLException ignored){}
    }
}