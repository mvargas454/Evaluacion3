
package cl.inacap.puntaarenas.evaluacion2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 01-12-2017.
 */

public class ListaProductos extends Activity {
    private List<Productos> lista= new ArrayList<Productos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        populateproductos();
        populatelistview();
    }

    private void populateproductos(){
        lista.add(new Productos(R.drawable.bebidas,"Bebidas","Codigo: A1", "Precio: $900"));
        lista.add(new Productos(R.drawable.jugo,"Jugo","Codigo: A2", "Precio: $500"));
        lista.add(new Productos(R.drawable.pan,"Pan","Codigo: A3", "Precio: $600"));
        lista.add(new Productos(R.drawable.azucar,"Azucar","Codigo: A4", "Precio: $500"));
        lista.add(new Productos(R.drawable.leche,"Leche","Codigo: A5", "Precio: $800"));
        lista.add(new Productos(R.drawable.galletas,"Galletas","Codigo: A6", "Precio: $1000"));
    }

    private void populatelistview(){
        ArrayAdapter<Productos> adapter = new listaadapter();
        ListView list = (ListView) findViewById(R.id.lista_productos);
        list.setAdapter(adapter);
    }

    private class listaadapter extends ArrayAdapter<Productos>{

        public listaadapter() {
            super(ListaProductos.this, R.layout.productos, lista);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemview = convertView;
            if (itemview == null){
                itemview=getLayoutInflater().inflate(R.layout.productos, parent, false);
            }

            Productos producto = lista.get(position);

            ImageView imageView = (ImageView)itemview.findViewById(R.id.item_icon);
            imageView.setImageResource(producto.getIdicono());

            TextView maketext= (TextView) itemview.findViewById(R.id.item_txtnombre);
            maketext.setText(producto.getNombre());

            TextView codigotext= (TextView) itemview.findViewById(R.id.item_txtcodigo);
            codigotext.setText(producto.getCodigo());

            TextView preciotext= (TextView) itemview.findViewById(R.id.item_txtprecio);
            preciotext.setText(producto.getPrecio());

            return itemview;

        }
    }
}

