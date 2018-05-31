package example.com.agenda.data.db;

import android.provider.BaseColumns;

public final class DBAgendaContract {
    private DBAgendaContract(){

    }

    public static final int DATABASE_VERSION = 2;
    public static final String  DATABASE_NAME = "agenda.db";

    public static class ContactoEntry implements BaseColumns{
        public static final String TABLE_NAME = "agenda";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_NUMERO = "numero";
        public static final String COLUMN_FECHA = "fecha";
        public static final String[] ALL_COLUMN = {BaseColumns._ID, COLUMN_NOMBRE, COLUMN_NUMERO, COLUMN_FECHA};

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, BaseColumns._ID, COLUMN_NOMBRE, COLUMN_NUMERO, COLUMN_FECHA);

        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s VALUES (1, 'Yo', '656621361', '1992-07-10')," +
                "(2, 'Otro', '656621361', '2012-07-10')", TABLE_NAME);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }
}
