package cl.inacap.puntaarenas.evaluacion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pedidos extends AppCompatActivity {

    DatabaseHelper1 pedidosdb;

    Button ingresar;
    Button borrar1;
    Button revisarpedidos;
    Button revisarproductos;
    EditText etnombre1,etproducto,etcantidad,etfecha,etprecio,id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_productos);
        super.onCreate(savedInstanceState);

        pedidosdb = new DatabaseHelper1(this);

        etnombre1= (EditText) findViewById(R.id.etnombre1);
        etproducto= (EditText) findViewById(R.id.etproducto);
        etcantidad= (EditText) findViewById(R.id.etcantidad);
        etfecha= (EditText) findViewById(R.id.etfecha);
        etprecio= (EditText) findViewById(R.id.etprecio);
        ingresar= (Button) findViewById(R.id.ingresar);
        borrar1= (Button) findViewById(R.id.borrar1);
        revisarpedidos= (Button) findViewById(R.id.revisarpedidos);
        revisarproductos = (Button) findViewById(R.id.revisarproductos);
        id1 = (EditText) findViewById(R.id.id1);

        revisarproductos.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent revisarproducto = new Intent(Pedidos.this, ListaProductos.class);
                                                    Pedidos.this.startActivity(revisarproducto);
                                                }
                                            });
        Añadir1();
        Ver1();
        Borrar1();
    }


    public void Ver1(){
        revisarpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = pedidosdb.showData();

                if(data.getCount()  == 0){
                    Display("Error", "No se encontro pedidos");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (data.moveToNext()){
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Nombre Cliente: " + data.getString(1) + "\n");
                    buffer.append("Producto: " + data.getString(2) + "\n");
                    buffer.append("Cantidad: " + data.getString(3) + "\n");
                    buffer.append("Fecha: " + data.getString(4) + "\n");
                    buffer.append("Precio: " + data.getString(5) + "\n");
                }
                Display("Pedidos Creados: ", buffer.toString());
            }
        });
    }


    public void Añadir1(){

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombrecliente = etnombre1.getText().toString();
                String producto = etproducto.getText().toString();
                String cantidad = etcantidad.getText().toString();
                String fecha = etfecha.getText().toString();
                String precio = etprecio.getText().toString();

                boolean insertData1 = pedidosdb.addData1(nombrecliente, producto, cantidad, fecha, precio);


                if(insertData1){
                    Toast.makeText(Pedidos.this, "Pedido Agregado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Pedidos.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
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


    public void Borrar1(){
        borrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = id1.getText().toString().length();
                if (temp > 0){
                    Integer deleteRow= pedidosdb.deleteData(id1.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(Pedidos.this, "Pedido eliminado correctamente", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Pedidos.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Pedidos.this, "Ingrese la ID del usuario que desee borrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}

