/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso;

/**
 *
 * @author H
 */

import Entidad.Empleados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmpleadosDAL {

    public static int crearEmpleado(Empleados empleado) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO Empleados (Nombre, Apellido, correo_electronico, Puesto) VALUES (?, ?, ?, ?)";
        try (Connection connection = ComunDB.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setString(3, empleado.getCorreoElectronico());
            statement.setString(4, empleado.getPuesto());
            result = statement.executeUpdate();
        }
        return result;
    }
     public static int editarEmpleado(Empleados empleado) throws SQLException {
        int result = 0;
        String sql = "UPDATE Empleados SET Nombre = ?, Apellido = ?, correo_electronico = ?, Puesto = ? WHERE id = ?";
        try (Connection connection = ComunDB.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setString(3, empleado.getCorreoElectronico());
            statement.setString(4, empleado.getPuesto());
            statement.setInt(5, empleado.getId());
            result = statement.executeUpdate();
        }
        return result;
    }

    // Otros métodos para actualizar, eliminar y consultar empleados...

    public static Empleados resultSetToEmpleado(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("Nombre");
        String apellido = resultSet.getString("Apellido");
        String correoElectronico = resultSet.getString("correo_electronico");
        String puesto = resultSet.getString("Puesto");
        return new Empleados(id, nombre, apellido, correoElectronico, puesto);
    }

  public static List<Empleados> obtenerEmpleados() throws SQLException {
        List<Empleados> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";
        
        try (Connection connection = ComunDB.obtenerConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Empleados empleado = resultSetToEmpleado(resultSet);
                empleados.add(empleado);
            }
        }
        
        return empleados;
    }

    
}

