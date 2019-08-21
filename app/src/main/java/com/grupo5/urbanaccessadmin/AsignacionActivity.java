package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AsignacionActivity extends AppCompatActivity {
    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText cedula, mz,villa;
    private TextView idR,clave,idV;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion);
        cedula= (EditText)findViewById(R.id.editTextcedula);
        mz= (EditText)findViewById(R.id.editTextmz);
        villa= (EditText)findViewById(R.id.editTextvilla);
        idR= (TextView)findViewById(R.id.textViewID);
        idV= (TextView)findViewById(R.id.textViewVilla);
        clave= (TextView)findViewById(R.id.textViewClave);
    }

    public void consultaResidente(View view){
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
                    codigo="4",
                    cedula.getText().toString()

            };

            if(cedula.getText().toString().equals("")){
                Toast.makeText(this, "Debe el dato cédula.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                idR.setText(resultadoSQL[0]);
                clave.setText(resultadoSQL[1]);
                Toast.makeText(AsignacionActivity.this,"Conexión Establecida", Toast.LENGTH_LONG).show();

            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void consultaVilla(View view){
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
                    codigo="7",
                    mz.getText().toString(),
                    villa.getText().toString()

            };

            if(mz.getText().toString().equals("")){
                Toast.makeText(this, "Debe el dato cédula.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                idV.setText(resultadoSQL[0]);
                Toast.makeText(AsignacionActivity.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
            }

        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void agregarAsignacion(View view){
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();

            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="8",
                    idR.getText().toString(),
                    idV.getText().toString(),
                    clave.getText().toString()

            };

            if(idV.getText().toString().equals("")|| idR.getText().toString().equals("")|| clave.getText().toString().equals("")){
                Toast.makeText(this, "Primero debe consultar.", Toast.LENGTH_LONG).show();
            }else{
                new AsyncQuery().execute(datosConexion);
                Toast.makeText(AsignacionActivity.this,"Dato Agregado.", Toast.LENGTH_LONG).show();
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}



