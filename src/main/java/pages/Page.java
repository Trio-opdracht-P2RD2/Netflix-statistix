package pages;

import database.SQLConnection;
import ui.views.Panel;

public abstract class Page {
    private SQLConnection sqlConnection;
    private Panel contentView;

    public Page(SQLConnection sqlConnection){
        this.sqlConnection = sqlConnection;
    }

    public void setContentView(Panel contentView){
        this.contentView = contentView;
    }

    public abstract void showPage();

    public abstract String getTitle();

    protected Panel getContentView(){
        return contentView;
    }

    protected SQLConnection getSqlConnection(){
        return sqlConnection;
    }
}
