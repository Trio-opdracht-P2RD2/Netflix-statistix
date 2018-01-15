package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class AccountsWithOneProfile extends Page {
    public AccountsWithOneProfile(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    /**
     * Executes a query on the database and shows all results.
     */
    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Naam\n" +
                "FROM Account\n" +
                "INNER JOIN Profiel ON Account.Abonneenummer = Profiel.Abonneenummer\n" +
                "GROUP BY Account.Abonneenummer, Account.Naam\n" +
                "HAVING COUNT(*) = 1", results -> {
                    getContentView().addChild(new TextView("Alle accounts met slechts één profiel: ", true));
                    while(results.next()) getContentView().addChild(new TextView(results.getString("Naam"))); // Add the results to the contentView.
                    if(getContentView().getChildCount() == 1) getContentView().addChild(new TextView("Er zijn geen accounts met slechts één profiel", true)); // Show a message that there are no results if there aren't any.
                });
    }

    @Override public String getTitle() {
        return "Accounts met één profiel";
    }
}
