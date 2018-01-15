package nl.trio_opdracht.netflix_statistix.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.DropDown;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class AverageTimeWatched extends Page {
    public AverageTimeWatched(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    /**
     * Executes two queries on the database and adds the results in a ArrayList and HashMap.
     */
    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Serie\n" +
                "FROM Serie", seriesResult -> {
                    ArrayList<String> series = new ArrayList<>();
                    series.add("Selecteer serie");
                    while (seriesResult.next()) series.add(seriesResult.getString("Serie"));

                    getSqlConnection().executeQuery(Configuration.databaseName,
                            "SELECT Abonneenummer, Profielnaam\n" +
                            "FROM Profiel", accountsResult -> { // We can't access seriesResult here, because it's already closed, but we can access the ArrayList series.
                                HashMap<String, Integer> accounts = new HashMap<>();
                                while(accountsResult.next()) accounts.put(accountsResult.getString("Profielnaam"), accountsResult.getInt("Abonneenummer"));

                                showResults(series, accounts);
                            });
                });
    }

    /**
     * Shows the results of the query in two dropdowns
     * @param series an array of the names of all series
     * @param accounts a HashMap with the names of all accounts and account numbers
     * @throws SQLException notices the parent method that an error occurred
     */
    private void showResults(ArrayList<String> series, HashMap<String, Integer> accounts) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 25, 0, 0);

        DropDown seriesDropdown = new DropDown(series);

        // Adds all profile names to the dropdown, including a "All accounts" option
        ArrayList<String> profileNames = new ArrayList<>();
        profileNames.add("Alle accounts");
        profileNames.addAll(accounts.keySet());
        DropDown accountsDropdown = new DropDown(profileNames);

        // Add a listener, which updates the content when a different item is selected
        seriesDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, item, accountsDropdown.getSelectedItem(), accounts.getOrDefault(accountsDropdown.getSelectedItem(), -1)));
        accountsDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, seriesDropdown.getSelectedItem(), item, accounts.getOrDefault(item, -1)));

        // Select the first item
        seriesDropdown.selectByIndex(0);
        accountsDropdown.selectByIndex(0);

        getContentView().addChild(seriesDropdown);
        getContentView().addChild(new TextView(" ")); // Add empty space between the dropdowns
        getContentView().addChild(accountsDropdown);
        getContentView().addChild(results);
    }

    /**
     * Updates the content when a different serie or account has been selected
     * @param results the view where all results have to get added to
     * @param serie The serie that has been selected
     * @param profileName The name of the selected profile
     * @param abboneenummer The number belonging to the selected profile
     */
    private void updateContent(ContainerView results, String serie, String profileName, int abboneenummer){
        results.removeAllChildren();
        if(!Objects.equals(serie, "Selecteer serie")) getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Titel, Seizoen, AVG(Percentage) AS 'AverageWatched'\n" +
                "FROM Aflevering\n" +
                "INNER JOIN Bekeken ON Bekeken.Gezien = Aflevering.ID\n" +
                "WHERE Serie = '" + serie + "'" + (!Objects.equals(profileName, "Alle accounts") ? " AND Abonneenummer = " + abboneenummer + " AND Profielnaam = '" + profileName + "'" : "") + "\n" +
                "GROUP BY ID, Titel, Seizoen\n" +
                "ORDER BY Seizoen", result2 -> {
                    while (result2.next()) results.addChild(new TextView(result2.getString("Seizoen") + " - " + result2.getString("Titel") + ": " + result2.getInt("AverageWatched") + "%"));
                    if(results.getChildCount() == 0) results.addChild(new TextView("Geen bekeken afleveringen voor het geselecteerde profiel"));
                });
        else results.addChild(new TextView("Geen serie geselecteerd"));
    }

    @Override public String getTitle() {
        return "Gemiddeld % gekeken per aflevering";
    }
}
