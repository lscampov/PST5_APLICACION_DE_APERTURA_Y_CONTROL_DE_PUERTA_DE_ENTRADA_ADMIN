package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VillaActivity extends AppCompatActivity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText txt1,txt2,txt3,txt4;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa);
        txt1 = (EditText)findViewById(R.id.editText14);
        txt2 = (EditText)findViewById(R.id.editText15);
        txt3 = (EditText)findViewById(R.id.editText16);
        txt4 = (EditText)findViewById(R.id.editText17);
    }
    public void agregar(View view){
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();

            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="3",
                    txt1.getText().toString(),
                    txt2.getText().toString(),
                    txt3.getText().toString(),
                    txt4.getText().toString()

            };

            if(txt1.getText().toString().equals("")|| txt2.getText().toString().equals("")|| txt3.getText().toString().equals("")|| txt4.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                new AsyncQuery().execute(datosConexion);
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                Toast.makeText(VillaActivity.this,"Dato Agregado.", Toast.LENGTH_LONG).show();
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
