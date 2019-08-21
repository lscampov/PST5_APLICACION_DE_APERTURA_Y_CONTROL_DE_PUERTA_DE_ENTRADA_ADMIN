package com.grupo5.urbanaccessadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarEliminarActivity extends AppCompatActivity {


    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private TextView txt1,txt2,txt3,txt4;
    private EditText txt5,txt6,txt7,txt8,txt9;
    private String codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_eliminar);

        txt1 = (TextView) findViewById(R.id.textView10);
        txt2 = (TextView)findViewById(R.id.textView12);
        txt3 = (TextView)findViewById(R.id.textView14);
        txt4 = (TextView)findViewById(R.id.textView16);
        txt5 = (EditText) findViewById(R.id.editTextmz);
        txt6 = (EditText) findViewById(R.id.editTextvilla);
        txt7 = (EditText) findViewById(R.id.editText12);
        txt8 = (EditText) findViewById(R.id.editText13);
        txt9 = (EditText) findViewById(R.id.editText9);

     }

    public void buscarModificar(View v){
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
                        codigo="6",
                        txt9.getText().toString()

                };

                if(txt9.getText().toString().equals("")){
                    Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
                }else{
                    resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                    txt1.setText(resultadoSQL[2]);
                    txt2.setText(resultadoSQL[3]);
                    txt3.setText(resultadoSQL[4]);
                    txt4.setText(resultadoSQL[5]);
                    txt5.setText(resultadoSQL[8]);
                    txt6.setText(resultadoSQL[6]);
                    txt7.setText(resultadoSQL[7]);
                    txt8.setText(resultadoSQL[0]);

                    Toast.makeText(this,"Conexión Establecida", Toast.LENGTH_LONG).show();
                }


            }catch(Exception ex)
            {
                Toast.makeText(this, "Error: "
                        + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

}
