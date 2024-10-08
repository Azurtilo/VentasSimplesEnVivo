package cl.acuna.ventassimplesenvivo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cl.acuna.ventassimplesenvivo.R;

public class menu_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_cliente);

        Button botonAgregarCliente = (Button)findViewById(R.id.boton_agregar_cliente);
        ListView listaClientes = (ListView) findViewById(R.id.datos_clientes);

        botonAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu_cliente.this, formulario_cliente.class));
                finish();
            }
        });
    }
}