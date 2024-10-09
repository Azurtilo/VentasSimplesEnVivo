package cl.acuna.ventassimplesenvivo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cl.acuna.ventassimplesenvivo.model.Cliente;

public class MainActivity extends AppCompatActivity {

    private List<Cliente> listClient = new ArrayList<Cliente>();
    ArrayAdapter<Cliente> arrayAdapterCliente;

    EditText nombreIngresado, apellidoIngresado, telefonoIngresado, direccionIngresada;

    ListView listV_clientes;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombreIngresado = findViewById(R.id.input_nombre);
        apellidoIngresado = findViewById(R.id.input_apellido);
        telefonoIngresado = findViewById(R.id.input_telefono);
        direccionIngresada = findViewById(R.id.input_direccion);

        listV_clientes = findViewById(R.id.lv_datos_clientes);
        Button botonGuardarDatosCliente = (Button) findViewById(R.id.boton_guardar_datos_cliente);

        inicializarDatabase();
        listarDatos();

        botonGuardarDatosCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreIngresado.getText().toString();
                String apellido = apellidoIngresado.getText().toString();
                String telefono = telefonoIngresado.getText().toString();
                String direccion = direccionIngresada.getText().toString();
                if (nombre.equals("") || apellido.equals("") || telefono.equals("") || direccion.equals("")) {
                    if (nombre.equals("")) {
                        nombreIngresado.setError("Required");
                    } else if (apellido.equals("")) {
                        apellidoIngresado.setError("Required");
                    } else if (telefono.equals("")) {
                        telefonoIngresado.setError("Required");
                    } else if (direccion.equals("")) {
                        direccionIngresada.setError("Required");
                    } else {
                        return;
                    }
                } else {
                    Cliente c = new Cliente();
                    c.setUid(UUID.randomUUID().toString());
                    c.setNombre(nombre);
                    c.setApellido(apellido);
                    c.setTelefono(telefono);
                    c.setDireccion(direccion);
                    databaseReference.child("Cliente").child(c.getUid()).setValue(c);
                    Toast.makeText(MainActivity.this, "Agregado ", Toast.LENGTH_SHORT).show();
                    nombreIngresado.setText("");
                    apellidoIngresado.setText("");
                    telefonoIngresado.setText("");
                    direccionIngresada.setText("");

                }
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listClient.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Cliente c = objSnapshot.getValue(Cliente.class);
                    listClient.add(c);

                    arrayAdapterCliente = new ArrayAdapter<Cliente>(MainActivity.this, android.R.layout.simple_list_item_1,listClient);
                    listV_clientes.setAdapter(arrayAdapterCliente);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void inicializarDatabase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}