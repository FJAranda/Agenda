package example.com.agenda.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import example.com.agenda.ui.AgendaApplication;

public class AgendaOpenHelper extends SQLiteOpenHelper {
    private  SQLiteDatabase sqLiteDatabase;
    private static AgendaOpenHelper singleton;

    private AgendaOpenHelper() {
        super(AgendaApplication.getContext(), DBAgendaContract.DATABASE_NAME, null, DBAgendaContract.DATABASE_VERSION);
    }

    public static AgendaOpenHelper getInstance() {
        if (singleton == null){
            singleton = new AgendaOpenHelper();
        }
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(DBAgendaContract.ContactoEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DBAgendaContract.ContactoEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBAgendaContract.ContactoEntry.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase openDatabase() {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase;
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }
}
