package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AccesoActivity extends AppCompatActivity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText txt1,txt2;
    private String codigo,user,psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        txt1 = (EditText)findViewById(R.id.editText);
        txt2 = (EditText)findViewById(R.id.editText2);
    }

    public void inicio(View view){
        Intent i = new Intent(this, MenuActivity.class );
        startActivity(i);
    }

    // Metodo para ingresar al menu principal
    public void ingreso(View view){
        String[] resultadoSQL = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();

            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="2",
                    txt1.getText().toString(),
                    txt2.getText().toString()

            };

            if(txt1.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                Toast.makeText(AccesoActivity.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
                user=resultadoSQL[0];
                psw=resultadoSQL[1];
               if(txt2.getText().toString().equals(psw)){
                    Intent i = new Intent(this, MenuActivity.class );
                    startActivity(i);
                    Toast.makeText(this, "Ingreso Exitoso.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Se ha producido un error.", Toast.LENGTH_LONG).show();
                }
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
