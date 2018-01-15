package nl.trio_opdracht.netflix_statistix.pages;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.DropDown;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class FilmsWatched extends Page {
    public FilmsWatched(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    /**
     * Executes the query and adds all profile names and numbers to the HashMap
     */
    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Abonneenummer, Profielnaam\n" +
                "FROM Profiel", results -> {
                    HashMap<String, Integer> accounts = new HashMap<>();
                    while(results.next()) accounts.put(results.getString("Profielnaam"), results.getInt("Abonneenummer"));

                    showResults(accounts);
                });
    }

    /**
     * Adds the accounts from the query to the dropdown
     * @param accounts a HashMap with the names of all accounts and account numbers
     * @throws SQLException notices the parent method that an error occurred
     */
    private void showResults(HashMap<String, Integer> accounts) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 10, 0, 0);

        DropDown accountsDropdown = new DropDown(accounts.keySet());
        accountsDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, item, accounts.getOrDefault(item, -1)));
        accountsDropdown.selectByIndex(0);

        ContainerView accountsContainer = new ContainerView(BoxLayout.X_AXIS);
        accountsContainer.setPadding(0, 0, 0, 10);
        accountsContainer.addChild(new TextView("Selecteer account: "));
        accountsContainer.addChild(accountsDropdown);
        getContentView().addChild(accountsContainer);

        getContentView().addChild(new TextView("Lijst met films die het geselecteerde profiel heeft gekeken: ", true));
        getContentView().addChild(results);
    }

    /**
     * Updates the content when a different account has been selected
     * @param results the view where the results have to get places in
     * @param profileName the name of the selected profile
     * @param abboneenummer the number of the selected profile
     */
    private void updateContent(ContainerView results, String profileName, int abboneenummer){
        results.removeAllChildren();
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Titel\n" +
                "FROM Bekeken\n" +
                "INNER JOIN Film ON Film.ID = Bekeken.Gezien\n" +
                "WHERE Abonneenummer = " + abboneenummer + " AND Profielnaam = '" + profileName + "'", result2 -> {
                    while (result2.next()) results.addChild(new TextView(result2.getString("Titel"))); // Add the results to the contentView.
                    if(results.getChildCount() == 0) results.addChild(new TextView("Geen bekeken films voor het geselecteerde profiel", true)); // Show a message that there are no results if there aren't any.
                });
    }

    @Override public String getTitle() {
        return "Bekeken films per profiel";
    }
}
