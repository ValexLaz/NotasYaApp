package com.example.proyectomovil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "materias.db";
    public static final String TABLE_MATERIAS = "t_materias";
    public static final  String TABLE_EXAMEN ="t_examen";
    public static final String TABLE_NOTAS = "t_notas";



    public DbHelper( Context context) {
        super(context, "materias.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_MATERIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "periodo TEXT NOT NULL," +
                "dias TEXT NOT NULL," +
                "hora_inicio TEXT NOT NULL," +
                "hora_fin TEXT NOT NULL)");

        String createTableQuery = "CREATE TABLE IF NOT EXISTS examenes(materia TEXT, fecha TEXT, hora TEXT);";
        db.execSQL(createTableQuery);


        db.execSQL("CREATE TABLE " + TABLE_NOTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nota INTEGER NOT NULL," +
                "id_materia INTEGER REFERENCES " + TABLE_MATERIAS + "(id))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_MATERIAS);
        db.execSQL("drop table if exists users");
        db.execSQL("DROP table if exists examenes");
        db.execSQL("DROP TABLE "+ TABLE_NOTAS);
        onCreate(db);

    }
    public Boolean insertData (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put ("username", username);
        contentValues.put("password",password);
        long result = db.insert("users", null, contentValues);
        if(result == -1) return false;
        else
            return  true;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkpassword (String password, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ? ", null);
        if(cursor.getCount()>=0)
            return true;
        else
            return false;
    }


}
