<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidad.Empleados"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<% 
    List<Empleados> empleados = (List<Empleados>) request.getAttribute("empleados");
    int numPage = 1;
    int numReg = 5;
    int countReg = 0;
    if (empleados == null) {
        empleados = new ArrayList<>();
    } else if (empleados.size() > numReg) {
        double divNumPage = (double) empleados.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main class="container">
            <div class="row">
                <div class="col l12 s12">
                    <h5>Crear Empleado</h5>
                    <form action="Empleados" method="post">
                        <!-- Resto del formulario para crear empleado -->
                        <div class="row">
                            <input type="hidden" name="accion" value="create">
                            <div class="input-field col l3 s12">
                                <input id="txtNombre" type="text" name="nombre" required class="validate" maxlength="50">
                                <label for="txtNombre">Nombre</label>
                            </div>
                            <div class="input-field col l3 s12">
                                <input id="txtApellido" type="text" name="apellido" required class="validate" maxlength="50">
                                <label for="txtApellido">Apellido</label>
                            </div>
                            <div class="input-field col l3 s12">
                                <input id="txtCorreo" type="email" name="correo_electronico" required class="validate" maxlength="50">
                                <label for="txtCorreo">Correo Electrónico</label>
                            </div>
                            <div class="input-field col l3 s12">
                                <input id="txtPuesto" type="text" name="puesto" required class="validate" maxlength="50">
                                <label for="txtPuesto">Puesto</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col l12 s12">
                                <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <td style="text-align: center" colspan="5">Lista de Empleados</td>
                                </tr>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Correo Electrónico</th>
                                    <th>Puesto</th>
                                    <!-- Agregar más encabezados de columnas según necesidad -->
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Empleados empleado : empleados) {
                                    int tempNumPage = numPage;
                                    if (numPage > 1) {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                    }
                                %>
                                <tr data-page="<%= tempNumPage %>">
                                    <td><%= empleado.getNombre() %></td>
                                    <td><%= empleado.getApellido() %></td>
                                    <td><%= empleado.getCorreoElectronico() %></td>
                                    <td><%= empleado.getPuesto() %></td>
                                    <!-- Agregar más celdas según necesidad -->
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage %>" />
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

