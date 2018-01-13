package nl.trio_opdracht.netflix_statistix.pages;

import nl.trio_opdracht.netflix_statistix.database.SQLConnection;
import nl.trio_opdracht.netflix_statistix.ui.views.Panel;

public abstract class Page {
    private SQLConnection sqlConnection;
    private Panel contentView;

    public Page(SQLConnection sqlConnection){
        this.sqlConnection = sqlConnection;
    }

    public void setContentView(Panel contentView){
        this.contentView = contentView;
    }

    public void showPage(){
        getContentView().removeAll();
        getContentView().updateUI();
        show();
    }

    protected abstract void show();

    public abstract String getTitle();

    protected Panel getContentView(){
        return contentView;
    }

    protected SQLConnection getSqlConnection(){
        return sqlConnection;
    }
}
