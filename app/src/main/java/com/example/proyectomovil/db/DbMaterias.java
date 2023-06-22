package com.example.proyectomovil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectomovil.entidades.Materias;

import java.util.ArrayList;

public class DbMaterias extends DbHelper{
    Context context;

    public DbMaterias(@Nullable Context context) {
        super(context);
        this.context=context;

    }

    public long insertaMateria(String nombre, String periodo, String dias, String hora_inicio, String hora_fin){
        long  id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("periodo", periodo);
            values.put("dias", dias);
            values.put("hora_inicio", hora_inicio);
            values.put("hora_fin", hora_fin);

            id = db.insert(TABLE_MATERIAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    public ArrayList<Materias> mostrarMaterias(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Materias> listaMaterias = new ArrayList<>();
        Materias materia = null;
        Cursor cursorMaterias = null;

        cursorMaterias = db.rawQuery("SELECT * FROM " + TABLE_MATERIAS, null);

        if(cursorMaterias.moveToFirst()){
            do{
                materia=new Materias();
                materia.setId(cursorMaterias.getInt(0));
                materia.setNombre(cursorMaterias.getString(1));
                materia.setPeriodo(cursorMaterias.getString(2));
                materia.setDias(cursorMaterias.getString(3));
                materia.setHora_inicio(cursorMaterias.getString(4));
                materia.setHora_fin(cursorMaterias.getString(5));
                listaMaterias.add(materia);
            } while (cursorMaterias.moveToNext());
        }
        cursorMaterias.close();

        return listaMaterias;
    }

    public Materias verMateria(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Materias materia = null;
        Cursor cursorMaterias;

        cursorMaterias = db.rawQuery("SELECT * FROM " + TABLE_MATERIAS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorMaterias.moveToFirst()){

                materia=new Materias();
                materia.setId(cursorMaterias.getInt(0));
                materia.setNombre(cursorMaterias.getString(1));
                materia.setPeriodo(cursorMaterias.getString(2));
                materia.setDias(cursorMaterias.getString(3));
                materia.setHora_inicio(cursorMaterias.getString(4));
                materia.setHora_fin(cursorMaterias.getString(5));


        }
        cursorMaterias.close();

        return materia;
    }
    public boolean editarMateria(int id, String nombre, String periodo, String dias, String hora_inicio, String hora_fin){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_MATERIAS + " SET nombre = '"+nombre+"',periodo = '"+periodo+"',dias = '"+dias+"', hora_inicio = '"+hora_inicio+"',hora_fin = '"+hora_fin+"' WHERE id='"+ id +"' ");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarMateria(int id){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM "+ TABLE_MATERIAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
