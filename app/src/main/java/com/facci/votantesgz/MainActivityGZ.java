package com.facci.votantesgz;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityGZ extends AppCompatActivity {
    EditText txtNOMBRE,txtAPELLIDO,txtRECINTO,txtID,txtANIO,buscarporid;
    DBHelper dbhelperSqlite;
    Button ingresar,mostrartodo,modificar,eliminar;
    public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_gz);
        dbhelperSqlite = new DBHelper(this);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNOMBRE = (EditText) findViewById(R.id.txtNOMBRE);
                txtID = (EditText) findViewById(R.id.txtID);
                txtAPELLIDO = (EditText) findViewById(R.id.txtAPELLIDO);
                txtRECINTO = (EditText) findViewById(R.id.txtRECINTO);
                txtANIO = (EditText) findViewById(R.id.txtANIO);
                boolean estado = dbhelperSqlite.IngresarDatos(txtNOMBRE.getText().toString(), txtAPELLIDO.getText().toString(), txtRECINTO.getText().toString(), txtANIO.getText().toString());
                if (estado == true) {
                    Toast.makeText(MainActivityGZ.this, "Guardado Correctamente", Toast.LENGTH_SHORT).show();
                    txtID.setText("");
                    txtAPELLIDO.setText("");
                    txtNOMBRE.setText("");
                    txtANIO.setText("");
                    txtRECINTO.setText("");
                } else {
                    Toast.makeText(MainActivityGZ.this, "No se pudo guardar", Toast.LENGTH_SHORT).show();

                }
            }
        });
        mostrartodo = (Button) findViewById(R.id.btnMostrar);
        mostrartodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = dbhelperSqlite.MostarTodos();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivityGZ.this, "OOOhhh ocurrio algo malo", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()) {
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Nombre : " + res.getString(1) + "\n");
                    buffer.append("Apellido : " + res.getString(2) + "\n");
                    buffer.append("Recinto : " + res.getString(3) + "\n");
                    buffer.append("Año nacimiento : " + res.getInt(4) + "\n\n");
                }

                Toast.makeText(MainActivityGZ.this, "XD", Toast.LENGTH_SHORT).show();
                mostrarMensaje("Registros", buffer.toString());

            }
        });
        buscarporid = (EditText) findViewById(R.id.txtID);
        buscarporid.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                return false;
            }
        });

        eliminar= (Button) findViewById(R.id.btnEliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtID = (EditText) findViewById(R.id.txtID);
                Integer registrosEliminados = dbhelperSqlite.eliminarRegistro(txtID.getText().toString());
                if(registrosEliminados > 0 ){
                    Toast.makeText(MainActivityGZ.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivityGZ.this,"No se ha podido eliminar",Toast.LENGTH_SHORT).show();
                }
            }
        });
        modificar= (Button) findViewById(R.id.btnModificar);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNOMBRE = (EditText) findViewById(R.id.txtNOMBRE);
                txtAPELLIDO= (EditText) findViewById(R.id.txtAPELLIDO);
                txtRECINTO = (EditText) findViewById(R.id.txtRECINTO);
                txtANIO = (EditText) findViewById(R.id.txtANIO);
                txtID = (EditText) findViewById(R.id.txtID);

                boolean Actualizador = dbhelperSqlite.modificar(String.valueOf(txtID.getText()),txtNOMBRE.getText().toString(),txtAPELLIDO.getText().toString(),txtRECINTO.getText().toString(),txtANIO.getText().toString());

                if (Actualizador == true){
                    Toast.makeText(MainActivityGZ.this,"Actualizado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivityGZ.this,"No se ha podido actualizar",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }}
