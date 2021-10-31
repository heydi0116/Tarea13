package com.example.tarea13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea13.configuracion.SQLiteConexion;
import com.example.tarea13.configuracion.Transacciones;

public class ActivityEditar extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id;
    EditText nombres;
    EditText apellidos;
    EditText edad;
    EditText correo;
    EditText direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        Button btnconsulta = (Button) findViewById(R.id.btnbuscar);
        Button btneliminar = (Button) findViewById(R.id.btneliminar);
        Button btnactualizar = (Button) findViewById(R.id.btnactualizar);

        id = (EditText) findViewById(R.id.txtcid);
        nombres = (EditText) findViewById(R.id.txtcnombres);
        apellidos = (EditText) findViewById(R.id.txtcapellidos);
        edad = (EditText) findViewById(R.id.txtcedad);
        correo = (EditText) findViewById(R.id.txtccorreo);
        direccion = (EditText) findViewById(R.id.txtcdireccion);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
    }

    private void Eliminar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Registro eliminado existosamente", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {
                id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());
        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Registro actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = {id.getText().toString()};

        String [] fields = {Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.correo,
                Transacciones.edad,
                Transacciones.direccion };
        String wherecond = Transacciones.id + "=?";

        try{
            Cursor cdata = db.query(Transacciones.tablaPersonas, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));
            Toast.makeText(getApplicationContext(), "Registro encontrado",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Registro no encontrado, verifique el Id", Toast.LENGTH_LONG).show();
        }
    }

    private void ClearScreen() {

        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");

    }
}