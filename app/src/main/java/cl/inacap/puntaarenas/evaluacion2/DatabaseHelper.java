package cl.inacap.puntaarenas.evaluacion2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zero on 18-10-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bdd";
    public static final String TABLE_NAME = "usuarios";
    public static final String COL1 = "ID";
    public static final String COL2 = "rut";
    public static final String COL3 = "nombre_local";
    public static final String COL4 = "nombre";
    public static final String COL5 = "direccion";
    public static final String COL6 = "telefono";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " rut TEXT, nombre_local TEXT, nombre TEXT, direccion TEXT, telefono TEXT)";
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



    public boolean addData(String rut, String nombre_local, String nombre, String direccion, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, rut);
        contentValues.put(COL3, nombre_local);
        contentValues.put(COL4, nombre);
        contentValues.put(COL5, direccion);
        contentValues.put(COL6, telefono);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String id, String rut, String nombre_local, String nombre, String direccion, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, rut);
        contentValues.put(COL3, nombre_local);
        contentValues.put(COL4, nombre);
        contentValues.put(COL5, direccion);
        contentValues.put(COL6, telefono);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});

    }
    public Cursor getlistcontents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data= db.rawQuery("SELECT rut FROM " + TABLE_NAME,null);
        return data;
    }
}
