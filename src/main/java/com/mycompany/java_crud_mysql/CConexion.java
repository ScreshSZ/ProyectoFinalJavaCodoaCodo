
package com.mycompany.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
       
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            //JOptionPane.showMessageDialog(null, "La conexion se realizo con exito");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.toString());
        }
        return conectar;
    }
    
    
}
