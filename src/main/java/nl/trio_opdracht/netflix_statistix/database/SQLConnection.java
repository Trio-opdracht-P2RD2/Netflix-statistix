package nl.trio_opdracht.netflix_statistix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SQLConnection {
    private String sqlAccountName;
    private Connection connection;
    private Statement statement;
    private String lastDatabase;

    public SQLConnection(SQLAccountName sqlAccountName){
        this.sqlAccountName = sqlAccountName.toString();
    }

    public SQLConnection(String customSqlAccountName){
        this.sqlAccountName = customSqlAccountName;
    }

    private String createConnectionUrl(String databaseName){
        return "jdbc:sqlserver://localhost\\" + sqlAccountName + ";databaseName=" + databaseName + ";integratedSecurity=true;";
    }

    public void executeQuery(String database, String sql) {
        executeQuery(database, sql, null);
    }

    public void executeQuery(String database, String sql, QueryResultListener resultListener) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if(!Objects.equals(lastDatabase, database)){
                disconnect();
                connectToDatabase(database);
                lastDatabase = database;
            }
            try(ResultSet resultSet = statement.executeQuery(sql)){
                if(resultListener != null) resultListener.onQueryResultReceived(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase(String database) throws SQLException {
        connection = DriverManager.getConnection(createConnectionUrl(database));
        statement = connection.createStatement();
    }

    public void disconnect(){
        if(statement != null) try{ statement.close(); } catch (SQLException ignored){}
        if(connection != null) try{ connection.close(); } catch (SQLException ignored){}
    }
}