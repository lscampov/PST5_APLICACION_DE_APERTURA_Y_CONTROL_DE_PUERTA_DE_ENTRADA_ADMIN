package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private Spinner spinnerPerfil;
    private EditText txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    private String codigo,perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        spinnerPerfil= (Spinner)findViewById(R.id.spinnerPerfil);
        txt1 = (EditText)findViewById(R.id.editText12);
        txt2 = (EditText)findViewById(R.id.editText6);
        txt3 = (EditText)findViewById(R.id.editText15);
        txt4 = (EditText)findViewById(R.id.editText3);
        txt5 = (EditText)findViewById(R.id.editText5);
        txt6 = (EditText)findViewById(R.id.editText7);
        txt7 = (EditText)findViewById(R.id.editText8);
        txt8 = (EditText)findViewById(R.id.editTextCedulaME);

        String [] perfiles= {"Administrador","Residente"};
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, perfiles);
        spinnerPerfil.setAdapter(adapter1);
    }

    // Agregar el administrador o residente a la base de datos
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
                codigo="1",
                txt6.getText().toString(),
                txt7.getText().toString(),
                perfil=spinnerPerfil.getSelectedItem().toString(),
                txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString(),
                txt4.getText().toString(),
                txt5.getText().toString(),
                txt8.getText().toString()

        };

        if(txt1.getText().toString().equals("")|| txt2.getText().toString().equals("")|| txt3.getText().toString().equals("")|| txt4.getText().toString().equals("")|| txt5.getText().toString().equals("")|| txt6.getText().toString().equals("")|| txt7.getText().toString().equals("")|| txt8.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
        }else{
            new AsyncQuery().execute(datosConexion);
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");
            txt8.setText("");
            Toast.makeText(AgregarActivity.this,"Dato Agregado.", Toast.LENGTH_LONG).show();
        }


    }catch(Exception ex)
    {
        Toast.makeText(this, "Error: "
                + ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}

}
