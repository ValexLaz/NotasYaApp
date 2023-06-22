package com.example.proyectomovil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectomovil.entidades.Notas;

import java.util.ArrayList;

public class DbNotas extends DbHelper{
    Context context;
    public DbNotas(@Nullable Context context) {
        super(context);
        this.context = context;


    }
    public long insertarNota( int nota, int id_materia){
        DbHelper  dbHelper= new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long  id = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("nota",nota);
            values.put("id_materia",id_materia);

            id = db.insert(TABLE_NOTAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;



    }

    public ArrayList<Notas> mostrarNotas(){
        DbHelper  dbHelper= new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Notas> listaNotas = new ArrayList<>();
        Notas nota;
        Cursor cursorNotas;

        cursorNotas = db.rawQuery("SELECT * FROM "+ TABLE_NOTAS, null);
        if(cursorNotas.moveToFirst()){
            do{
                nota=new Notas();
                nota.setId(cursorNotas.getInt(0));
                nota.setNota(cursorNotas.getInt(1));
                nota.setId_materia(cursorNotas.getInt(2));
                listaNotas.add(nota);
            } while (cursorNotas.moveToNext());
        }
        cursorNotas.close();
        return listaNotas;
    }

    public Notas verNotas(int id){
        DbHelper  dbHelper= new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Notas nota= null;
        Cursor cursorNotas;

        cursorNotas = db.rawQuery("SELECT * FROM "+ TABLE_NOTAS + " WHERE id = " + id + " LIMIT 1", null);
        if(cursorNotas.moveToFirst()){

                nota=new Notas();
                nota.setId(cursorNotas.getInt(0));
                nota.setNota(cursorNotas.getInt(1));
                nota.setId_materia(cursorNotas.getInt(2));


        }
        cursorNotas.close();
        return nota;
    }

    public boolean editarNota(int id, int nota, int id_materia){

        boolean correcto = false;

        DbHelper  dbHelper= new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_NOTAS + " SET nota = '"+nota+"', id_materia = '"+id_materia+"' WHERE id = '"+ id +"' ");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return correcto;
    }
    public boolean eliminarNota(int id){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM "+ TABLE_NOTAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public ArrayList<Notas> mostrarNotasPorMateria(int idMateria) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Notas> listaNotas = new ArrayList<>();
        Notas nota;
        Cursor cursorNotas;

        cursorNotas = db.rawQuery("SELECT * FROM " + TABLE_NOTAS + " WHERE id_materia = " + idMateria, null);
        if (cursorNotas.moveToFirst()) {
            do {
                nota = new Notas();
                nota.setId(cursorNotas.getInt(0));
                nota.setNota(cursorNotas.getInt(1));
                nota.setId_materia(cursorNotas.getInt(2));
                listaNotas.add(nota);
            } while (cursorNotas.moveToNext());
        }
        cursorNotas.close();
        return listaNotas;
    }

    public double obtenerPromedioPorMateria(int idMateria) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        double promedio = 0;
        Cursor cursorNotas;

        cursorNotas = db.rawQuery("SELECT AVG(nota) FROM " + TABLE_NOTAS + " WHERE id_materia = " + idMateria, null);
        if (cursorNotas.moveToFirst()) {
            promedio = cursorNotas.getDouble(0);
        }
        cursorNotas.close();
        return promedio;
    }


}
