package com.facci.votantesgz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by GABRIEL_SZM on 25/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "CNEGZ.db";
    public static final String TABLA_NOMBRE = "VOTANTESGZ";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO_ELECTORAL";
    public static final String COL_5 = "ANO_NACIMIENTO";

    public DBHelper(Context context) {
        super(context,DB_NOMBRE,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }


    @SuppressLint("LongLogTag")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s INTEGER)",TABLA_NOMBRE,COL_2,COL_3,COL_4,COL_5));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s",TABLA_NOMBRE));
        onCreate(db);
    }
    public boolean IngresarDatos(String nombre,String apellido,String recinto, String anio ){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,anio);
        long resultado =db.insert(TABLA_NOMBRE,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }
    public Cursor MostarTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }


    public Cursor bucarporid(String id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();



        // TODO Auto-generated method stub
        String[] columns = new String[]{COL_1,COL_2,COL_3,COL_4,COL_5};
        Cursor c = db.query(TABLA_NOMBRE, columns, COL_1 + "="+ id, null, null, null, null);
        if(c!=null)
        {
            return c;
        }
        return null;
    }

    public Integer eliminarRegistro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{id});
    }

    public boolean modificar(String id, String nombre, String apellido, String recinto, String anio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,anio);
        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});



        return true;
    }
}
