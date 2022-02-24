package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sandra
 */
public class Conexion {

    static Connection conectar = null;
    final static String URL = "jdbc:mysql://localhost/turismo_javaweb";
    final static String USUARIO = "root";
    final static String PASSWORD = "";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Obtendrá la conexion a la base de datos
     * @return 
     */
    public static Connection abrirConexion() {
        try {
            if(conectar == null){
             Class.forName(DRIVER);
            conectar = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión Exitosa");
            } else {
                System.out.println("Ya está conectado");
            }
            
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido una excepcion:");
            System.out.println("Mensaje: " + ex.getMessage());
            System.out.println("Estado: "+ ex.getSQLState());
            System.out.println("Codigo de error "+ ex.getErrorCode());
        }  finally {
            return conectar;
        }
        
    }
}
