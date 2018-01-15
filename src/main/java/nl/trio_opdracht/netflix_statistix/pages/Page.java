package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;

/**
 * A page of the app which can be shown.
 */
public abstract class Page {
    private SQLConnection sqlConnection;
    private ContainerView contentView;

    public Page(SQLConnection sqlConnection){
        this.sqlConnection = sqlConnection;
    }

    public void setContentView(ContainerView contentView){
        this.contentView = contentView;
    }

    public void showPage(){
        getContentView().removeAllChildren();
        show(); // Calls the show method of the specified page (like FilmsWatched, AccountsWithOneProfile)
    }

    protected abstract void show();

    public abstract String getTitle(); // Title of the page

    protected ContainerView getContentView(){
        return contentView;
    }

    protected SQLConnection getSqlConnection(){
        return sqlConnection;
    }

    public void closeSqlConnection(){
        sqlConnection.disconnect();
    }
}
