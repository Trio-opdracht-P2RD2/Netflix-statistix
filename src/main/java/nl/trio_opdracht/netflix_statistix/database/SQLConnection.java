package nl.trio_opdracht.netflix_statistix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnection {
    private String sqlAccountName;

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
            try(Connection connection = DriverManager.getConnection(createConnectionUrl(database))){
                try(Statement statement = connection.createStatement()){
                    try(ResultSet resultSet = statement.executeQuery(sql)){
                        if(resultListener != null) resultListener.onQueryResultReceived(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}