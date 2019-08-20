package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaActivity extends AppCompatActivity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private Spinner spinnerPerfil;
    private EditText txt1;
    private TextView txt2;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        txt1 = (EditText)findViewById(R.id.editText13);
        txt2 = (TextView) findViewById(R.id.textViewConsulta);
    }

    public void consulta(View view){
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
                    codigo="5",
                    txt1.getText().toString()

            };

            if(txt1.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                txt2.setText(resultadoSQL[0]);
                Toast.makeText(ConsultaActivity.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
