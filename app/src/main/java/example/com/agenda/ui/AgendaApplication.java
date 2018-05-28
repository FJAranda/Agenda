package example.com.agenda.ui;

import android.app.Application;
import android.content.Context;

import example.com.agenda.data.db.AgendaOpenHelper;

public class AgendaApplication extends Application {

    private static AgendaApplication agendaApplication;

    public AgendaApplication(){
        agendaApplication = this;
    }

    public static Context getContext(){
        return agendaApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AgendaOpenHelper.getInstance().openDatabase();
    }
}
