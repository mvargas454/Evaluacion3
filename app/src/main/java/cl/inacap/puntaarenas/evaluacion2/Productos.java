package cl.inacap.puntaarenas.evaluacion2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Productos extends AppCompatActivity {

    private int idicono;
    private String nombre;
    private String codigo;
    private String precio;

    public int getIdicono() {
        return idicono;
    }

    public void setIdicono(int idicono) {
        this.idicono = idicono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Productos(int idicono, String nombre, String codigo, String precio) {
        this.idicono = idicono;
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }
}
