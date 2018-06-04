package example.com.agenda.data.db.dao;

import android.content.ContentValues;
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
                    aux.add(new Contacto(cursor.getInt(0), cursor.getString(1), sdf.parse(cursor.getString(3)), cursor.getString(2)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }

        AgendaOpenHelper.getInstance().closeDatabase();

        return aux;
    }

    public long add(Contacto contacto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SQLiteDatabase sqLiteDatabase = AgendaOpenHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        long id;

        contentValues.put(DBAgendaContract.ContactoEntry.COLUMN_NOMBRE, contacto.getNombre());
        contentValues.put(DBAgendaContract.ContactoEntry.COLUMN_NUMERO, contacto.getTelefono());
        contentValues.put(DBAgendaContract.ContactoEntry.COLUMN_FECHA, sdf.format(contacto.getFecha()));

        id = sqLiteDatabase.insert(DBAgendaContract.ContactoEntry.TABLE_NAME, null, contentValues);

        AgendaOpenHelper.getInstance().closeDatabase();
        return id;
    }
}
