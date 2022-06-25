package com.example.ejercicio1_4jonathan_vivian;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ejercicio1_4jonathan_vivian.Tablas.Empleados;

import java.io.ByteArrayInputStream;

public class ActivityDetalleFoto extends AppCompatActivity {

    ImageView imageViewMostrarIMG;
    EditText txtdetallenombre, txtdetalledescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_foto);

        imageViewMostrarIMG = (ImageView) findViewById(R.id.imgdetallefoto);
        txtdetallenombre = (EditText) findViewById(R.id.txtdetallenombre);
        txtdetalledescripcion = (EditText) findViewById(R.id.txtdetalleapellido);
        Bundle objetEnvia = getIntent().getExtras();
        Empleados imagen = null;

        if(objetEnvia != null){
            imagen = (Empleados) objetEnvia.getSerializable("empleados");

            txtdetallenombre.setText(imagen.getNombres());
            txtdetalledescripcion.setText(imagen.getDescripcion());
            mostrarImagen(imagen.getImage());
            Bitmap image = BitmapFactory.decodeFile(imagen.getPathImage());
            imageViewMostrarIMG.setImageBitmap(image);
        }
    }
    private void mostrarImagen(byte[] image) {
        Bitmap bitmap = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(image);
        bitmap = BitmapFactory.decodeStream(bais);
        imageViewMostrarIMG.setImageBitmap(bitmap);
    }

}