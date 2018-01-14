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

    @Override protected void show() {
        getContentView().setLayout(BoxLayout.Y_AXIS);
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Abonneenummer, Profielnaam\n" +
                "FROM Profiel", results -> {
                    HashMap<String, Integer> accounts = new HashMap<>();
                    while(results.next()) accounts.put(results.getString("Profielnaam"), results.getInt("Abonneenummer"));

                    showResults(accounts);
                });
    }

    private void showResults(HashMap<String, Integer> accounts) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 25, 0, 0);

        DropDown accountsDropdown = new DropDown(accounts.keySet());
        accountsDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, item, accounts.getOrDefault(item, -1)));
        accountsDropdown.selectByIndex(0);

        getContentView().addChild(accountsDropdown);
        getContentView().addChild(results);
    }

    private void updateContent(ContainerView results, String profileName, int abboneenummer){
        results.removeAllChildren();
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT Titel\n" +
                "FROM Bekeken\n" +
                "INNER JOIN Film ON Film.ID = Bekeken.Gezien\n" +
                "WHERE Abonneenummer = " + abboneenummer + " AND Profielnaam = '" + profileName + "'", result2 -> {
                    while (result2.next()) results.addChild(new TextView(result2.getString("Titel")));
                    if(results.getChildCount() == 0) results.addChild(new TextView("Geen bekeken films voor het geselecteerde profiel"));
                });
    }

    @Override public String getTitle() {
        return "Bekeken films per profiel";
    }
}
