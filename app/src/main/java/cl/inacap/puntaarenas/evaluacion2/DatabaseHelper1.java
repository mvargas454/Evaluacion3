package cl.inacap.puntaarenas.evaluacion2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zero on 18-10-2017.
 */

public class DatabaseHelper1 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bdd1";
    public static final String TABLE_NAME = "productos";
    public static final String COL1 = "ID";
    public static final String COL2 = "nombrecliente";
    public static final String COL3 = "producto";
    public static final String COL4 = "cantidad";
    public static final String COL5 = "fecha";
    public static final String COL6 = "precio";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nombrecliente TEXT, producto TEXT, cantidad TEXT, fecha TEXT, precio TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor showData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor showData1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data1 = db.rawQuery("SELECT productos FROM "+ TABLE_NAME, null);
        return data1;
    }



    public boolean addData1(String nombrecliente, String producto, String cantidad, String fecha, String precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, nombrecliente);
        contentValues.put(COL3, producto);
        contentValues.put(COL4, cantidad);
        contentValues.put(COL5, fecha);
        contentValues.put(COL6, precio);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Integer deleteData(String id1) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id1});

    }
}
