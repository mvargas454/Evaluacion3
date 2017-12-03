package cl.inacap.puntaarenas.evaluacion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button ingresar;
    EditText usr,psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr = (EditText) findViewById(R.id.usr);
        psd = (EditText) findViewById(R.id.psd);
        ingresar = (Button) findViewById(R.id.ingresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usr.getText().toString().equals("Admin") && psd.getText().toString().equals("Admin")){
                    Toast.makeText(getApplicationContext(),"Iniciando sesion",Toast.LENGTH_LONG);
                    Intent myintent = new Intent(Login.this, MainActivity.class);
                    startActivity(myintent);

                }else if (usr.getText().toString().equals("root") && psd.getText().toString().equals("root") ){
                    Toast.makeText(getApplicationContext(),"Iniciando sesion",Toast.LENGTH_LONG);
                    Intent myintent1 = new Intent(Login.this, MainActivity.class);
                    startActivity(myintent1);
                }else{
                    Toast.makeText(getApplicationContext(),"Nombre de usuario o contraseña erroneos",Toast.LENGTH_LONG);
                }
            }
        });

    }
}
