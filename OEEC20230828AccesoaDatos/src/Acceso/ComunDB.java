/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso;

/**
 *
 * @author H
 */


import java.sql.*; // Que es java.sql   https://es.wikipedia.org/wiki/Java_Database_Connectivity

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Alumno
 */
public class ComunDB {
    // Documentacion de clases-anidadas --> https://javadesdecero.es/poo/clases-anidadas/
    class TipoDB { // La clase TipoDB se utilizara para saber que tipo de gestor de base de datos estamos accediendo 

        static final int SQLSERVER = 1; // Propiedad que tendra valor 1 para saber que es SQL SERVER
        static final int MYSQL = 2; // Propiedad que tendra valor 2 para saber que es MYSQL
    }
    static int TIPODB = TipoDB.SQLSERVER; //Propiedad para el tipo de gestor de base de datos que estamos utilizando
    //La propiedad "connectionUrl" es para almacenar el string de conexion a la base de datos actual  
   /* static String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS02:2404;"
            + "database=SeguridadWebdb;"
            + "user=sa;"
            + "password=12345;"
            + "loginTimeout=30;encrypt=false;trustServerCertificate=false";*/
    
     static String connectionUrl = "jdbc:sqlserver://DESKTOP-UK0BLII:1433;"
            + "database=EmpleadosDB;"
            + "user=oto;"
            + "password=12345;"
            + "loginTimeout=30;encrypt=false;trustServerCertificate=false";
    // Documentacion para entender por que se esta utilizando throws SQLException
    // en los siguientes metodos --> http://dis.um.es/~bmoros/Tutorial/parte9/cap9-3.html
    
    
    /* El metodo obtenerConexion() nos va a devolver una conexion abierta al gestor de base de datos que 
     estemos utilizando*/
    public static Connection obtenerConexion() throws SQLException {
        // Registrar el Driver de la conexion a la base de datos SQL server
        // para que lo reconozca el servidor web
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection connection = DriverManager.getConnection(connectionUrl); // abrir la conexion a la base de datos
        return connection; // retornar la conexion a la base de datos
    }
}

