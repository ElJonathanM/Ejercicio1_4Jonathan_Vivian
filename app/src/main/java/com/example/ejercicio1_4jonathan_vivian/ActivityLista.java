package com.example.ejercicio1_4jonathan_vivian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercicio1_4jonathan_vivian.Procesos.SQLiteConexion;
import com.example.ejercicio1_4jonathan_vivian.Procesos.Transacciones;
import com.example.ejercicio1_4jonathan_vivian.Tablas.Empleados;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaempleados;
    ArrayList<String> ArregloEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        conexion  = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        lista = (ListView) findViewById(R.id.lista);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleados);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarPicture(i);
            }
        });
    }
    private void mostrarPicture(int i){

        Empleados empleados = listaempleados.get(i);

        Bundle bundle = new Bundle();
        bundle.putSerializable("empleados", empleados);

        Intent intent = new Intent(getApplicationContext(), ActivityDetalleFoto.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void ObtenerListaEmpleados() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_emple = null;
        listaempleados = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery(Transacciones.SELECT_ALL_TABLE_PICTURE,null);

        while(cursor.moveToNext()) {
            list_emple = new Empleados();

            list_emple.setId(cursor.getInt(0));                 //ID
            list_emple.setNombres(cursor.getString(1));         //Nombre
            list_emple.setDescripcion(cursor.getString(2));     //Descripcion
            list_emple.setPathImage(cursor.getString(3));
            list_emple.setImage(cursor.getBlob(4));

            listaempleados.add(list_emple);
        }

        cursor.close();

        llenalista();
    }

    private void llenalista() {
        ArregloEmpleados = new ArrayList<String>();

        for(int i=0; i<listaempleados.size(); i++) {
            ArregloEmpleados.add(listaempleados.get(i).getId() +" | "+
                    listaempleados.get(i).getNombres() +" | "+
                    listaempleados.get(i).getDescripcion());
        }

    }
}