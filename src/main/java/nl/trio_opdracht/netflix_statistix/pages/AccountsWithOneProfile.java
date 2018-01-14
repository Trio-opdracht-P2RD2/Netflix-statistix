package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class AccountsWithOneProfile extends Page {
    public AccountsWithOneProfile(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Naam\n" +
                "FROM Account\n" +
                "INNER JOIN Profiel ON Account.Abonneenummer = Profiel.Abonneenummer\n" +
                "GROUP BY Account.Abonneenummer, Account.Naam\n" +
                "HAVING COUNT(*) = 1", results -> {
                    while(results.next()) getContentView().add(new TextView(results.getString("Naam")));
                    if(getContentView().getComponentCount() == 0) getContentView().add(new TextView("Er zijn geen accounts met slechts één profiel"));
                    getContentView().updateUI();
                });
    }

    @Override public String getTitle() {
        return "Accounts met één profiel";
    }
}
