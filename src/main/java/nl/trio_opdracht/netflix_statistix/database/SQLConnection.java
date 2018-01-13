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
        // Connection beheert informatie over de connectie met de nl.trio_opdracht.netflix_statistix.database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de nl.trio_opdracht.netflix_statistix.database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;

        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de nl.trio_opdracht.netflix_statistix.database.
            con = DriverManager.getConnection(createConnectionUrl(database));

            // Stel een SQL query samen.
            stmt = con.createStatement();
            // Voer de query uit op de nl.trio_opdracht.netflix_statistix.database.
            rs = stmt.executeQuery(sql);

            if(resultListener != null) resultListener.onQueryResultReceived(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}