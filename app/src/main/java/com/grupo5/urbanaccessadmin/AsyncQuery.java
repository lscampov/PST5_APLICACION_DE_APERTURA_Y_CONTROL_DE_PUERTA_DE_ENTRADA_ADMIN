package com.grupo5.urbanaccessadmin;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

public class AsyncQuery extends AsyncTask<String[],Void,String[]> {

    private Connection conexionMySQL=null;
    private Statement st = null;
    private ResultSet rs = null;

    @Override
    protected String[] doInBackground(String[]... datos) {
        String nombre1 ;
        String nombre2 ;
        String apellido1;
        String apellido2;
        String cedula;
        String user;
        String password;
        String mz,villa,longitud,latitud,fecha;
        String email ;
        String perfil ;
        String codigo=datos[0][5];
        String resultadoSQL = "";
        String[] totalResultadoSQL = null;
        int numColumnas = 0;
        int numFilas = 0;
        String SERVIDOR = datos[0][0];
        String PUERTO = datos[0][1];
        String BD = datos[0][2];
        String USUARIO = datos[0][3];
        String PASSWORD = datos[0][4];
        String clave_acceso,id_residente,id_villa;

        try{
            conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + SERVIDOR + ":" + PUERTO + "/" + BD,
                    USUARIO,PASSWORD);

            st = conexionMySQL.createStatement();

            //agg admin y resident
            if(codigo.equals("1")){
                nombre1 = datos[0][9];
                nombre2 = datos[0][10];
                apellido1 = datos[0][11];
                apellido2 = datos[0][12];
                cedula = datos[0][13];
                user= datos[0][6];
                password= datos[0][7];
                email = datos[0][14];
                perfil = datos[0][8];
                if(perfil.equals("Administrador")){

                    String q = "INSERT INTO Usuario (Cedula,Nombre1,Nombre2,Apellido1,Apellido2,User,Password,email,clave_acceso,Id_Perfil) VALUES " +
                            "('" + cedula + "','" + nombre1 + "','" + nombre2 + "','" + apellido1 +"','" + apellido2 +"','" + user +"','" + password +"','" + email + "','0000','1')";
                    Log.d("Query: ",q);
                    st.executeUpdate(q);

                }else{

                    Random r=new Random();
                    int val1=r.nextInt(10);
                    int val2=r.nextInt(10);
                    int val3=r.nextInt(10);
                    int val4=r.nextInt(10);
                    clave_acceso=String.valueOf(val1)+String.valueOf(val2)+String.valueOf(val3)+String.valueOf(val4);

                    String q = "INSERT INTO Usuario (Cedula,Nombre1,Nombre2,Apellido1,Apellido2,User,Password,email,clave_acceso,Id_Perfil) VALUES " +
                            "('" + cedula + "','" + nombre1 + "','" + nombre2 + "','" + apellido1 +"','" + apellido2 +"','" + user +"','" + password +"','" + email + "','"+clave_acceso+"','2')";
                    Log.d("Query: ",q);
                    st.executeUpdate(q);
                }

                //validar acceso
            }if(codigo.equals("2")){
                user= datos[0][6];
                password= datos[0][7];
                rs = st.executeQuery("SELECT User,Password FROM Usuario WHERE User='"+user+"';");
                rs.last();
                    numFilas = rs.getRow();
                    if(numFilas == 0)
                    {
                        resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                    }else
                    {
                        rs.beforeFirst();
                        while (rs.next())
                        {
                            numColumnas = rs.getMetaData().getColumnCount();
                            for(int i=1;i<=numColumnas;i++){
                                totalResultadoSQL=new String[2];
                                totalResultadoSQL[i-1]= rs.getString(i);
                            }
                        }
                }

                //agg villa
            }if(codigo.equals("3")){
                mz=datos[0][6];
                villa=datos[0][7];
                latitud=datos[0][8];
                longitud=datos[0][9];
                String q = "INSERT INTO Villa (Mz,Villa,Latitud,Longitud) VALUES " +
                        "('" + mz + "','" + villa + "','" + latitud + "','" +longitud+"')";
                Log.d("Query: ",q);
                st.executeUpdate(q);

                //consulta por cedula
            }if(codigo.equals("4")){
                cedula=datos[0][6];
                rs = st.executeQuery("SELECT Id_Usuario,clave_acceso FROM Usuario WHERE Cedula='"+cedula+"';");
                rs.last();
                numFilas = rs.getRow();
                if(numFilas == 0)
                {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                }else
                {
                    rs.beforeFirst();
                    while (rs.next())
                    {
                        numColumnas = rs.getMetaData().getColumnCount();
                        for(int i=1;i<=numColumnas;i++){
                            totalResultadoSQL=new String[2];
                            totalResultadoSQL[i-1]= rs.getString(i);
                        }
                    }
                }

                //consulta entrada
            }if(codigo.equals("5")) {
                fecha = datos[0][6];
                rs = st.executeQuery("SELECT * FROM Entrada;");
                rs.last();
                numFilas = rs.getRow();
                if (numFilas == 0) {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                } else {
                    for (int i = 1; i <= numColumnas; i++) {
                        if (i > 1) resultadoSQL += ",";
                        resultadoSQL += rs.getMetaData().getColumnName(i);
                    }
                    resultadoSQL += "\n";
                    rs.beforeFirst();
                    while (rs.next()) {
                        numColumnas = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= numColumnas; i++) {
                            if (i > 1) resultadoSQL += ",";
                            resultadoSQL += rs.getString(i);
                        }
                        resultadoSQL += "\n";
                    }
                }
                totalResultadoSQL = new String[]{resultadoSQL};

                //modificar eliminar
            }if(codigo.equals("6")) {
                //140989
                //rs = st.executeQuery("SELECT User,Password FROM Usuario WHERE User='"+user+"';");
                cedula = datos[0][6];
                rs = st.executeQuery("SELECT clave_acceso,Cedula,Nombre1,Nombre2,Apellido1,Apellido2,User,Password,email FROM Usuario WHERE Cedula = '"+cedula+"';");
                rs.last();
                numFilas = rs.getRow();
                if(numFilas == 0)
                {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                }else
                {
                    rs.beforeFirst();
                    while (rs.next())
                    {
                        numColumnas = rs.getMetaData().getColumnCount();
                        totalResultadoSQL=new String[9];
                        for(int i=1;i<=numColumnas;i++){

                            totalResultadoSQL[i-1]= rs.getString(i);
                        }
                    }
                }

                //consulta por villa y mz
            }if(codigo.equals("7")) {
                mz = datos[0][6];
                villa = datos[0][7];
                rs = st.executeQuery("SELECT id_Villa FROM Villa WHERE Mz='" + mz + "' AND Villa='"+villa+"';");
                rs.last();
                numFilas = rs.getRow();
                if (numFilas == 0) {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                } else {
                    rs.beforeFirst();
                    while (rs.next()) {
                        numColumnas = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= numColumnas; i++) {
                            totalResultadoSQL = new String[1];
                            totalResultadoSQL[i - 1] = rs.getString(i);
                        }
                    }
                }

            //asignacion
            }if(codigo.equals("8")){
            id_residente=datos[0][6];
            id_villa=datos[0][7];
            clave_acceso=datos[0][8];
            String q = "INSERT INTO Asignacion (Id_Usuario,Id_Villa,Clave_de_Acceso) VALUES " +
                    "('" + id_residente + "','" + id_villa + "','" +clave_acceso+"')";
            Log.d("Query: ",q);
            st.executeUpdate(q);

            //consulta para  modificar
        }if (codigo.equals("9")){
                cedula = datos[0][6];
                rs = st.executeQuery("SELECT Nombre1,Nombre2,Apellido1,Apellido2,User,Password,email,clave_acceso FROM Usuario WHERE Cedula='"+cedula+"';");
                rs.last();
                numFilas = rs.getRow();
                if (numFilas == 0) {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                } else {
                    rs.beforeFirst();
                    while (rs.next()) {
                        numColumnas = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= numColumnas; i++) {
                            totalResultadoSQL = new String[1];
                            totalResultadoSQL[i - 1] = rs.getString(i);
                        }
                    }
                }
            }

            //modificar
            if (codigo.equals("10")){
                cedula = datos[0][6];
                nombre1= datos[0][7];
                nombre2= datos[0][8];
                apellido1= datos[0][9];
                apellido2= datos[0][10];
                email= datos[0][11];
                user= datos[0][12];
                password= datos[0][13];
                clave_acceso= datos[0][14];
                String q = "UPDATE Usuario SET Nombre1='"+nombre1+"',Nombre2='"+nombre2+"',Apellido1='"+apellido1+"',Apellido2='"+apellido2+"',User='"+user+"',Password='"+password+"',email='"+email+"',clave_acceso='"+clave_acceso+" WHERE Cedula='"+cedula+"'";
                Log.d("Query: ",q);
                st.executeUpdate(q);
            }

            //eliminar
            if (codigo.equals("11")){
                cedula = datos[0][6];
                String q = "DELETE FROM Usuario WHERE Cedula='"+cedula+"'";
                Log.d("Query: ",q);
                st.executeUpdate(q);
            }

        }catch(SQLException ex)
        {
            Log.d("Error de conexión", ex.getMessage());
        }
        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                st.close();
                conexionMySQL.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return totalResultadoSQL;
    }

}


