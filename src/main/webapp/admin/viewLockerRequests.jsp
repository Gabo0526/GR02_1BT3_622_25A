<%@ page import="java.util.List" %>
<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero" %><%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solicitudes de Casilleros - Sistema de Gestión</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: white;
            color: #333;
            min-height: 100vh;
        }

        .app-title {
            font-family: 'Impact', sans-serif;
            color: #ff3333;
            text-align: left;
            margin: 15px 0 5px 30px;
            font-size: 2.5rem;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
            letter-spacing: 1px;
        }

        .container {
            width: 95%;
            margin: 20px auto;
            padding: 20px;
            border: 3px solid #333;
            border-radius: 5px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .page-title {
            border-bottom: 1px solid rgba(0, 0, 0, 0.2);
            padding-bottom: 15px;
            margin-bottom: 25px;
            font-size: 1.6rem;
            font-weight: normal;
        }

        .solicitudes-list {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .solicitudes-list th, .solicitudes-list td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .solicitudes-list th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .solicitudes-list tr:hover {
            background-color: #f5f5f5;
        }

        .no-solicitudes {
            text-align: center;
            padding: 20px;
            color: #666;
            font-style: italic;
        }

        .accion-buttons {
            display: flex;
            gap: 10px;
        }

        .aprobar-btn, .rechazar-btn {
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: all 0.3s;
        }

        .aprobar-btn {
            background-color: #e6ffee;
            border: 1px solid #b3ffcc;
            color: #009933;
        }

        .rechazar-btn {
            background-color: #ffebe6;
            border: 1px solid #ffcccc;
            color: #cc0000;
        }

        .aprobar-btn:hover {
            background-color: #b3ffcc;
        }

        .rechazar-btn:hover {
            background-color: #ffcccc;
        }

        .filtro {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f8f8f8;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        .filtro select, .filtro button {
            padding: 8px 12px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .filtro button {
            background-color: #4d4d4d;
            color: white;
            cursor: pointer;
        }

        .filtro button:hover {
            background-color: #333;
        }

        .badge {
            display: inline-block;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 0.85rem;
            font-weight: bold;
        }

        .badge-pendiente {
            background-color: #fff3cd;
            color: #856404;
            border: 1px solid #ffeeba;
        }

        .badge-aprobado {
            background-color: #d1e7dd;
            color: #0f5132;
            border: 1px solid #badbcc;
        }

        .badge-rechazado {
            background-color: #f8d7da;
            color: #842029;
            border: 1px solid #f5c2c7;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            border-radius: 5px;
            color: #333;
            text-decoration: none;
            transition: all 0.3s;
        }

        .back-button:hover {
            background-color: #e0e0e0;
        }

        .logout {
            position: absolute;
            top: 15px;
            right: 15px;
        }

        .logout button {
            padding: 8px 12px;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: #666;
            cursor: pointer;
            transition: all 0.3s;
        }

        .logout button:hover {
            background-color: #e0e0e0;
            color: #333;
        }

        @media (max-width: 768px) {
            .container {
                width: 98%;
                padding: 10px;
            }

            .solicitudes-list th, .solicitudes-list td {
                padding: 8px;
            }

            .accion-buttons {
                flex-direction: column;
                gap: 5px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="page-title">Solicitudes de Casilleros</h2>

    <div class="filtro">
        <form action="SvSolicitudes" method="get">
            <select name="estado">
                <option value="todas">Todas las solicitudes</option>
                <option value="pendientes">Pendientes</option>
                <option value="aprobadas">Aprobadas</option>
                <option value="rechazadas">Rechazadas</option>
            </select>
            <button type="submit">Filtrar</button>
        </form>
    </div>

    <%
        List<AlquilerCasillero> alquilerCasilleros = (List<AlquilerCasillero>) request.getAttribute("alquilerCasilleros");
        if (alquilerCasilleros != null && !alquilerCasilleros.isEmpty()) {
    %>
    <table class="solicitudes-list">
        <thead>
        <tr>
            <th>ID</th>
            <th>Estudiante</th>
            <th>Bloque</th>
            <th>Casillero</th>
            <th>Fecha Solicitud</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (AlquilerCasillero solicitud : alquilerCasilleros) {
        %>
        <tr>
            <td><%= solicitud.getId() %></td>
            <td><%= solicitud.getIdUsuario().getNombre()+" "+solicitud.getIdUsuario().getApellido() %></td>
            <td><%= solicitud.getIdCasillero().getIdBloque().getNombreBloque()%></td>
            <td><%= solicitud.getIdCasillero().getNumero() %></td>
            <td><%= solicitud.getFechaInicio() %></td>
            <td>
                <%
                    String estado = solicitud.getEstadoAlquiler();
                    if ("Pendiente".equals(estado)) {
                %>
                <span class="badge badge-pendiente">Pendiente</span>
                <%
                } else if ("Activo".equals(estado)) {
                %>
                <span class="badge badge-aprobado">Aprobada</span>
                <%
                } else if ("Rechazado".equals(estado)) {
                %>
                <span class="badge badge-rechazado">Rechazada</span>
                <%
                } else if ("Vencido".equals(estado)) {
                %>
                <span class="badge badge-rechazado">Vencido</span>
                <%
                    }
                %>
            </td>
            <td>
                <%
                    if ("Pendiente".equals(solicitud.getEstadoAlquiler())) {
                %>
                <div class="accion-buttons">
                    <form action="ActualizarSolicitudServlet" method="post">
                        <input type="hidden" name="accion" value="aprobar">
                        <input type="hidden" name="idSolicitud" value="<%= solicitud.getId() %>">
                        <button type="submit" class="aprobar-btn">Aprobar</button>
                    </form>
                    <form action="ActualizarSolicitudServlet" method="post">
                        <input type="hidden" name="accion" value="rechazar">
                        <input type="hidden" name="idSolicitud" value="<%= solicitud.getId() %>">
                        <button type="submit" class="rechazar-btn">Rechazar</button>
                    </form>
                </div>
                <%
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <div class="no-solicitudes">
        <p>No hay solicitudes de casilleros disponibles.</p>
    </div>
    <%
        }
    %>

    <a href="${pageContext.request.contextPath}/home.jsp" class="back-button">Volver al inicio</a>

    <div class="logout">
        <form action="LogoutServlet" method="get">
            <button type="submit">Cerrar Sesion</button>
        </form>
    </div>
</div>
</body>
</html>