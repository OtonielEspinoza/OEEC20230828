/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import Entidad.Empleados;
import Acceso.EmpleadosDAL;
import utils.Utilidad;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmpleadosServlet", urlPatterns = {"/Empleados"})
public class EmpleadosServlet extends HttpServlet {

    private Empleados obtenerEmpleado(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Empleados empleado = new Empleados();
        if (!accion.equals("create")) {
            empleado.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        empleado.setNombre(Utilidad.getParameter(request, "nombre", ""));
        empleado.setApellido(Utilidad.getParameter(request, "apellido", ""));
        empleado.setCorreoElectronico(Utilidad.getParameter(request, "correo_electronico", ""));
        empleado.setPuesto(Utilidad.getParameter(request, "puesto", ""));
        return empleado;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Empleados> empleados = EmpleadosDAL.obtenerEmpleados(); // Obtener la lista de empleados.
            request.setAttribute("empleados", empleados); // Enviar los empleados al jsp.
            request.getRequestDispatcher("Views/Empleados/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empleados empleado = obtenerEmpleado(request);
            int result = EmpleadosDAL.crearEmpleado(empleado);
            if (result != 0) {
                request.setAttribute("confirmacion", "El empleado se creó correctamente");
                request.getRequestDispatcher("Views/Empleados/confirmacion.jsp").forward(request, response);
            } else {
                Utilidad.enviarError("No se logró registrar un nuevo empleado", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empleados empleado = obtenerEmpleado(request);
            int result = EmpleadosDAL.editarEmpleado(empleado);
            if (result != 0) {
                request.setAttribute("confirmacion", "El empleado se actualizó correctamente");
                request.getRequestDispatcher("Views/Empleados/confirmacion.jsp").forward(request, response);
            } else {
                Utilidad.enviarError("No se logró actualizar el empleado", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    // ... Otros métodos de acciones ...

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGetRequestIndex(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        switch (accion) {
            case "create":
                doPostRequestCreate(request, response);
                break;
            case "edit":
                doPostRequestEdit(request, response);
                break;
            // ... Otras acciones ...
            default:
                doGetRequestIndex(request, response);
        }
    }
}
