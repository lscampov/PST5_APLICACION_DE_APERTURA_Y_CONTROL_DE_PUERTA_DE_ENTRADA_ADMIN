package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignacionActivity extends AppCompatActivity {
    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText txt1,txt2,txt3;
    private String codigo,idResidente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion);
        txt1= (EditText)findViewById(R.id.edit1);
        txt2= (EditText)findViewById(R.id.editText10);
        txt3= (EditText)findViewById(R.id.editText11);
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
                    codigo="4",
                    txt1.getText().toString(),
                    txt2.getText().toString(),
                    txt3.getText().toString()

            };

            if(txt1.getText().toString().equals("")|| txt2.getText().toString().equals("")|| txt3.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                idResidente=resultadoSQL[0];
                if (idResidente.equals("")){
                    Toast.makeText(this, "Dato no encontrado.", Toast.LENGTH_LONG).show();
                }else{
                    new AsyncQuery().execute(datosConexion);
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    Toast.makeText(AsignacionActivity.this,"Dato Agregado.", Toast.LENGTH_LONG).show();
                }

            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
