package nl.trio_opdracht.netflix_statistix.pages;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.DropDown;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class TimesFullyWatchedFilm extends Page {
    public TimesFullyWatchedFilm(SQLConnection sqlConnection) {
        super(sqlConnection);
    }

    @Override protected void show() {
        getContentView().setLayout(BoxLayout.Y_AXIS);
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT ID, Titel\n" +
                "FROM Film", results -> {
                    HashMap<String, Integer> films = new HashMap<>();
                    while(results.next()) films.put(results.getString("Titel"), results.getInt("ID"));

                    showResults(films);
                });
    }

    private void showResults(HashMap<String, Integer> films) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 25, 0, 0);

        DropDown accountsDropdown = new DropDown(films.keySet());
        accountsDropdown.addActionListener(actionEvent -> updateContent(results, (String) accountsDropdown.getSelectedItem(), films.getOrDefault(accountsDropdown.getSelectedItem(), -1)));
        accountsDropdown.setSelectedIndex(0);

        getContentView().add(accountsDropdown);
        getContentView().add(results);
        getContentView().updateUI();
    }

    private void updateContent(ContainerView results, String filmName, int filmID){
        results.removeAll();
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT COUNT(*) AS 'TimesFullyWatched'\n" +
                "FROM Film\n" +
                "INNER JOIN Bekeken ON Bekeken.Gezien = Film.ID\n" +
                "WHERE Percentage = 100 AND Film.Titel = '" + filmName + "' AND Film.ID = " + filmID + "\n" +
                "GROUP BY Film.ID", result2 -> {
                    while (result2.next()) results.add(new TextView(result2.getInt("TimesFullyWatched") + " keer volledig bekeken"));
                    if(results.getComponentCount() == 0) results.add(new TextView("0 keer volledig bekeken"));
                    results.updateUI();
                });
        results.updateUI();
    }

    @Override public String getTitle() {
        return "Film - aantal keren volledig bekeken";
    }
}
