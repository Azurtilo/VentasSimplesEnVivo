package cl.acuna.ventassimplesenvivo.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cl.acuna.ventassimplesenvivo.R;

public class formulario_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_cliente);

        EditText nombreIngresado = (EditText) findViewById(R.id.input_nombre);
        EditText apellidoIngresado = (EditText) findViewById(R.id.input_apellido);
        EditText telefonoIngresado = (EditText) findViewById(R.id.input_telefono);
        EditText direccionIngresada = (EditText) findViewById(R.id.input_direccion);

        Button botonGuardarDatosCliente = (Button)findViewById(R.id.boton_guardar_datos_cliente);


    }
}