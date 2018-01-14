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

    @Override protected void show() {
        getContentView().setLayout(BoxLayout.Y_AXIS);
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Serie\n" +
                "FROM Serie", seriesResult -> {
                    ArrayList<String> series = new ArrayList<>();
                    series.add("Selecteer serie");
                    while (seriesResult.next()) series.add(seriesResult.getString("Serie"));

                    getSqlConnection().executeQuery(Configuration.databaseName,
                            "SELECT Abonneenummer, Profielnaam\n" +
                            "FROM Profiel", accountsResult -> {
                                HashMap<String, Integer> accounts = new HashMap<>();
                                while(accountsResult.next()) accounts.put(accountsResult.getString("Profielnaam"), accountsResult.getInt("Abonneenummer"));

                                showResults(series, accounts);
                            });
                });
    }

    private void showResults(ArrayList<String> series, HashMap<String, Integer> accounts) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 25, 0, 0);

        DropDown seriesDropdown = new DropDown(series);

        ArrayList<String> profileNames = new ArrayList<>();
        profileNames.add("Alle accounts");
        profileNames.addAll(accounts.keySet());
        DropDown accountsDropdown = new DropDown(profileNames);

        seriesDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, item, accountsDropdown.getSelectedItem(), accounts.getOrDefault(accountsDropdown.getSelectedItem(), -1)));
        accountsDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, seriesDropdown.getSelectedItem(), item, accounts.getOrDefault(item, -1)));

        seriesDropdown.selectByIndex(0);
        accountsDropdown.selectByIndex(0);

        getContentView().addChild(seriesDropdown);
        getContentView().addChild(new TextView(" "));
        getContentView().addChild(accountsDropdown);
        getContentView().addChild(results);
    }

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
