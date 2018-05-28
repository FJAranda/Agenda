package example.com.agenda.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import example.com.agenda.data.db.AgendaOpenHelper;
import example.com.agenda.data.db.DBAgendaContract;
import example.com.agenda.data.db.pojo.Contacto;

public class AgendaDAO {
    public ArrayList<Contacto> loadAll(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SQLiteDatabase sqLiteDatabase = AgendaOpenHelper.getInstance().openDatabase();
        ArrayList<Contacto> aux = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBAgendaContract.ContactoEntry.TABLE_NAME, DBAgendaContract.ContactoEntry.ALL_COLUMN, null, null,
                null, null, null);
        if (cursor.moveToFirst()){
            do {
                try {
                    aux.add(new Contacto(cursor.getInt(0), cursor.getString(1), sdf.parse(cursor.getString(2)), cursor.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }

        AgendaOpenHelper.getInstance().closeDatabase();

        return aux;
    }
}
