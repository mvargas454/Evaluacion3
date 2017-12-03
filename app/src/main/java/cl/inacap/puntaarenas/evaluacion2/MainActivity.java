package cl.inacap.puntaarenas.evaluacion2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper usuariosdb;

    Button agregar;
    Button ver;
    Button actualizar;
    Button borrar;
    Button productos;
    EditText etrut,etnombre_local,etnombre,etdireccion,ettelefono,id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");



        usuariosdb = new DatabaseHelper(this);

        etrut= (EditText) findViewById(R.id.etrut);
        etnombre_local= (EditText) findViewById(R.id.etnombre_local);
        etnombre= (EditText) findViewById(R.id.etnombre);
        etdireccion= (EditText) findViewById(R.id.etdireccion);
        ettelefono= (EditText) findViewById(R.id.ettelefono);
        agregar= (Button) findViewById(R.id.agregar);
        ver = (Button) findViewById(R.id.ver);
        id = (EditText) findViewById(R.id.id);
        actualizar = (Button) findViewById(R.id.actualizar);
        productos = (Button) findViewById(R.id.productos);
        borrar = (Button) findViewById(R.id.borrar);

        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productointent = new Intent(MainActivity.this, Pedidos.class);
                MainActivity.this.startActivity(productointent);
            }
        });



        Añadir();
        Ver();
        Actualizar();
        Borrar();
    }
    public void Ver(){
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = usuariosdb.showData();

                if(data.getCount()  == 0){
                    Display("Error", "No se encontro clientes");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (data.moveToNext()){
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Rut: " + data.getString(1) + "\n");
                    buffer.append("Nombre del local: " + data.getString(2) + "\n");
                    buffer.append("Nombre: " + data.getString(3) + "\n");
                    buffer.append("Direccion: " + data.getString(4) + "\n");
                    buffer.append("Telefono: " + data.getString(5) + "\n");
                }
                Display("Clientes Creados: ", buffer.toString());
            }
        });
    }


    public void Añadir(){

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rut = etrut.getText().toString();
                String nombre_local = etnombre_local.getText().toString();
                String nombre = etnombre.getText().toString();
                String direccion = etdireccion.getText().toString();
                String telefono = ettelefono.getText().toString();

                boolean insertData = usuariosdb.addData(rut, nombre_local, nombre, direccion, telefono);


                if(insertData){
                    Toast.makeText(MainActivity.this, "Cliente Agregado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void Display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void Actualizar(){
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = id.getText().toString().length();
                if(temp > 0){
                    boolean actualizar = usuariosdb.updateData(id.getText().toString(),etrut.getText().toString(),etnombre_local.getText().toString(),etnombre.getText().toString(),etdireccion.getText().toString(),ettelefono.getText().toString());
                    if(actualizar == true){
                        Toast.makeText(MainActivity.this, "Cliente actualizado correctamente", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese la ID del cliente que desee actualizar", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void Borrar(){
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = id.getText().toString().length();
                if (temp > 0){
                    Integer deleteRow= usuariosdb.deleteData(id.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(MainActivity.this, "Cliente eliminado correctamente", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese la ID del cliente que desee borrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
