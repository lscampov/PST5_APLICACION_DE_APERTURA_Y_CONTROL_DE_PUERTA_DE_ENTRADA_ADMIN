package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarEliminarActivity extends AppCompatActivity {


    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText cedula,n1,n2,a1,a2,mail,user,psw,clave;
    private String codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_eliminar);

        cedula = (EditText) findViewById(R.id.editTextCedulaME);
        n1= (EditText) findViewById(R.id.editTextNombre1);
        n2= (EditText) findViewById(R.id.editTextNombre2);
        a1= (EditText) findViewById(R.id.editTextApellido);
        a2= (EditText) findViewById(R.id.editTextApellido2);
        mail= (EditText) findViewById(R.id.editTextMail);
        user= (EditText) findViewById(R.id.editTextUser);
        psw= (EditText) findViewById(R.id.editTextPsw);
        clave= (EditText) findViewById(R.id.editTextClave);

     }

    public void buscar(View v){
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
                        codigo="9",
                        cedula.getText().toString()

                };

                if(cedula.getText().toString().equals("")){
                    Toast.makeText(this, "Debe el dato cédula.", Toast.LENGTH_LONG).show();
                }else{
                    resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                    n1.setText(resultadoSQL[0]);
                    n2.setText(resultadoSQL[1]);
                    a1.setText(resultadoSQL[2]);
                    a2.setText(resultadoSQL[3]);
                    mail.setText(resultadoSQL[6]);
                    user.setText(resultadoSQL[4]);
                    psw.setText(resultadoSQL[5]);
                    clave.setText(resultadoSQL[7]);
                    Toast.makeText(this,"Conexión Establecida", Toast.LENGTH_LONG).show();
                }


            }catch(Exception ex)
            {
                Toast.makeText(this, "Error: "
                        + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    public void Modificar(View view){
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="10",
                    cedula.getText().toString(),
                    n1.getText().toString(),
                    n2.getText().toString(),
                    a1.getText().toString(),
                    a2.getText().toString(),
                    mail.getText().toString(),
                    user.getText().toString(),
                    psw.getText().toString(),
                    clave.getText().toString()
            };

            if(cedula.getText().toString().equals("")){
                Toast.makeText(this, "Debe consultar primero.", Toast.LENGTH_LONG).show();
            }else{
                new AsyncQuery().execute(datosConexion);
                Toast.makeText(ModificarEliminarActivity.this,"Datos Actualizados.", Toast.LENGTH_LONG).show();
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View view){
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="11",
                    cedula.getText().toString()
            };

            if(cedula.getText().toString().equals("")){
                Toast.makeText(this, "Ingrese cédula.", Toast.LENGTH_LONG).show();
            }else{
                new AsyncQuery().execute(datosConexion);
                cedula.setText("");
                n1.setText("");
                n2.setText("");
                a1.setText("");
                a2.setText("");
                mail.setText("");
                user.setText("");
                psw.setText("");
                clave.setText("");
                Toast.makeText(ModificarEliminarActivity.this,"Dato Eliminado.", Toast.LENGTH_LONG).show();
            }


        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
