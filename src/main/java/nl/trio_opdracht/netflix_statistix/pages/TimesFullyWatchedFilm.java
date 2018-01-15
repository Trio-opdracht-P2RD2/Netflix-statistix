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

    /**
     * Executes the query and adds the results to the HashMap
     */
    @Override protected void show() {
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT ID, Titel\n" +
                "FROM Film", results -> {
                    HashMap<String, Integer> films = new HashMap<>();
                    while(results.next()) films.put(results.getString("Titel"), results.getInt("ID"));

                    showResults(films);
                });
    }

    /**
     * Shows the results in a dropdown
     * @param films an HashMap of films with their name and ID.
     * @throws SQLException notifies the parent method that an error has occurred
     */
    private void showResults(HashMap<String, Integer> films) throws SQLException{
        ContainerView results = new ContainerView(BoxLayout.Y_AXIS);
        results.setPadding(0, 10, 0, 0);

        DropDown filmsDropdown = new DropDown(films.keySet());
        filmsDropdown.setOnItemSelectedListener((dropDown, item, index) -> updateContent(results, item, films.getOrDefault(item, -1)));
        filmsDropdown.selectByIndex(0);

        ContainerView filmsContainer = new ContainerView(BoxLayout.X_AXIS);
        filmsContainer.setPadding(0, 0, 0, 10);
        filmsContainer.addChild(new TextView("Selecteer film: ", true));
        filmsContainer.addChild(filmsDropdown);
        getContentView().addChild(filmsContainer);

        getContentView().addChild(new TextView("Het aantal keer dat de geselecteerde film volledig gekeken is: ", true));
        getContentView().addChild(results);
    }

    /**
     * Updates the content when a different film was selected
     * @param results the view where the results have to get added to
     * @param filmName the name of the selected movie
     * @param filmID the ID of the selected film.
     */
    private void updateContent(ContainerView results, String filmName, int filmID){
        results.removeAllChildren();
        getSqlConnection().executeQuery(Configuration.databaseName,
                "SELECT COUNT(*) AS 'TimesFullyWatched'\n" +
                "FROM Film\n" +
                "INNER JOIN Bekeken ON Bekeken.Gezien = Film.ID\n" +
                "WHERE Percentage = 100 AND Film.Titel = '" + filmName + "' AND Film.ID = " + filmID + "\n" +
                "GROUP BY Film.ID", result2 -> {
                    while (result2.next()) results.addChild(new TextView(result2.getInt("TimesFullyWatched") + " keer volledig bekeken"));
                    if(results.getChildCount() == 0) results.addChild(new TextView("0 keer volledig bekeken"));
                });
    }

    @Override public String getTitle() {
        return "Film - aantal keren volledig bekeken";
    }
}
