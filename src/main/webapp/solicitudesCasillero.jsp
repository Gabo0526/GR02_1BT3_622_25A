<%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Recordatorios Page</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: rgb(255, 255, 255);
            margin: 0;
            padding: 0;
            height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;
        }

        div {
            background-color: white;
            padding: 20px;
            border-radius: 10px;

            width: 100%;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            height: 100%;
        }
        .recordatorio {
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            margin-bottom: 10px;
            background-color: red;
        }
        .contenidoRecordatorio {
            display: flex;
            flex-direction: column;
            width: 90%;
            height: 100%;
            border-radius: 0px;
        }
        .estadoRecordatorio {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            width: 10%;
            height: 100%;
            border-radius: 0px;
        }
        h2 {
            color: darkblue;
            text-align: center;
            margin-bottom: 30px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: darkblue;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: rgb(23, 23, 206);
        }
        select {
            width: 100%;
            padding: 8px 12px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
            font-size: 14px;
            align-content: center;
            color: #333;
            appearance: none; /* Oculta el estilo nativo del sistema */
            cursor: pointer;
            transition: border-color 0.2s ease, box-shadow 0.2s ease;
        }

        select:focus {
            outline: none;
            border-color: #5b9bd5;
            box-shadow: 0 0 5px rgba(91, 155, 213, 0.5);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="registro">
        <h2>Recordatorios</h2>
        <form action="SvRecordatorios" method="post">
            <input type="hidden" name="accion" value="AgregarRecordatorio" />
            <input type="text" name="titulo" placeholder="Título" required />
            <input
                    type="text"
                    name="descripcion"
                    placeholder="Descripción"
                    required
            />
            <button type="submit">Agregar Recordatorio</button>
        </form>
        <p>Recordatorios Agregados:</p>
        <ul id="recordatorios-list">
            <!-- Aquí se agregarán los recordatorios -->
        </ul>
    </div>
    <%
        List<Recordatorio> recordatorios = (List<Recordatorio>) request.getAttribute("recordatorios");
        if (recordatorios != null && !recordatorios.isEmpty()) {
            for (Recordatorio r : recordatorios) {
    %>
    <div class="recordatorio">
        <div class="contenidoRecordatorio">
            <h3><%= r.getTitulo() %> - <%= r.getFechaCreacion() %></h3>
            <p><%= r.getDescripcion() %></p>
        </div>
        <div class="estadoRecordatorio">
            <form action="SvRecordatorios" method="post">
                <input type="hidden" name="id" value="<%= r.getId() %>" />
                <input type="hidden" name="accion" value="ActualizarEstado" />
                <select name="estado">
                    <option value="Pendiente" <%= "Pendiente".equalsIgnoreCase(r.getEstado()) ? "selected" : "" %>>Pendiente</option>
                    <option value="Completado" <%= "Completado".equalsIgnoreCase(r.getEstado()) ? "selected" : "" %>>Completado</option>
                </select>
                <button type="submit">Actualizar</button>
            </form>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p>No hay recordatorios disponibles.</p>
    <%
        }
    %>

    <div class="logout">
        <form action="LogoutServlet" method="get">
            <button type="submit">
                Cerrar Sesión
            </button>
        </form>
    </div>
</div>
</body>
</html>
