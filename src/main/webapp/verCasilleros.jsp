<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.Casillero" %>
<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%BloqueCasillero bloqueCasillero = (BloqueCasillero) session.getAttribute("bloque");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mirror Casilleros</title>
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
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            height: 100%;
        }
        .bloque {
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: rgb(70, 130, 180);
        }
        .dashboard {
            display: grid;
            grid-template-columns: repeat(<%= bloqueCasillero.getNroColumnas()%>, 100px); /* 3 columnas */
            grid-template-rows: repeat(<%= bloqueCasillero.getNroFilas()%>, 100px); /* 3 filas */
            gap: 10px; /* Espacio entre los divs */
            background-color: rgb(70, 130, 180);
            padding: 10px; /* Espacio alrededor de la cuadrícula */
            width: min-content;
        }
        .celda {
            background-color: rgb(69, 238, 69);
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .seccionInferior {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: white;
            width: 80%;
        }
        button {
            width: 100%;
            height: 100%;
            background-color: rgb(70, 130, 180);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 20px;
        }
        button.casillero {
            width: 100%;
            height: 100%;
            background: none;
            border: none;
            padding: 0;
            margin: 0;
            cursor: pointer;
            font-size: 20px;
            font-weight: bold;
        }
        .emergente {
            display: none; /* Oculto por defecto */
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }
        .emergente-contenido {
            background: white;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }
        .Disponibilidad {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            padding: 10px;
        }
        .Dimensiones {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            padding: 10px;
        }
        select {
            margin-left: 10px;
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
            height: 50%;
            width: 100%;
        }
        .botones {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
            gap: 10px;
        }
        .disponibilidadSelect {
            width: 100%;
        }
        .botonSelect {
            margin-left: 10px;
            width: 100%;
        }
    </style>
</head>
<body>
<%
    List<Casillero> casilleros = (List<Casillero>) request.getAttribute("casilleros");
%>
<div class="container">
    <div class="Titulo">
        <h1>Bloque Casilleros N°:</h1>
        <h1 id="numeroBloque">1</h1>
    </div>
    <div class="bloque">
        <div class="dashboard">
            <%
                for (Casillero r : casilleros) {
            %>
            <div class="celda">
                <button class="casillero" onclick="mostrarEmergente(<%= r.getNumero()%>)"><%= r.getNumero()%></button>
                <p id="ID<%= r.getNumero()%>" hidden><%= r.getId()%></p>
                <p id="Disponibilidad<%= r.getNumero()%>" hidden><%= r.getEstado()%></p>
                <p id="Dimensiones<%= r.getNumero()%>" hidden><%= r.getAltura() +"x"+r.getAncho()+"x"+r.getProfundidad()%></p>
            </div>
            <%
                }
            %>
        </div>
        <div class="seccionInferior">
            <div class="botones">
                <h1></h1>
            </div>
        </div>
    </div>
</div>
<!-- Contenedor vista Emergente -->
<div id="miEmergente" class="emergente">
    <div class="emergente-contenido">
        <h2>Casillero 1</h2>
        <div class="Disponibilidad">
            <h3>Disponibilidad:</h3>
                <form action="CasilleroServlet" method="post" style="<%=(session.getAttribute("rolUsuario").equals("Estudiante") ? "display:none;" : "")%>">
                <input type="hidden" id="idCasillero" name="idCasillero">
                <select class="disponibilidadSelect" id="disponibilidadSelect" name="disponibilidadSelect">
                    <option value="Disponible">Disponible</option>
                    <option value="Ocupado">Ocupado</option>
                    <option value="Pendiente">Pendiente</option>
                    <option value="Averiado">Averiado</option>
                </select>
                <button class="botonSelect">Actualizar</button>
                </form>
            <p id="txtDisponibilidad" style="<%=(session.getAttribute("rolUsuario").equals("Administrador") ? "display:none;" : "")%>"></p>

        </div>
        <div class="Dimensiones">
            <h3>Dimensiones:</h3>
            <p id="textoDimensiones"></p>
        </div>
        <div class="botones">
            <button onclick="reservarCasillero()" style="<%=(session.getAttribute("rolUsuario").equals("Administrador") ? "display:none;" : "")%>">Reservar</button>
            <button onclick="cerrarEmergente()">Cerrar</button>
        </div>
    </div>
</div>
<!-- Codigo JavaScript para controlar las funciones de la pestaña emergente -->
<script>
    function mostrarEmergente(numero) {
        document.getElementById("miEmergente").style.display = "flex";
        document.getElementById("textoDimensiones").innerText = document.getElementById("Dimensiones"+numero).innerText;

        const disponibilidad = document.getElementById("Disponibilidad"+numero).innerText;

        document.getElementById("disponibilidadSelect").value = disponibilidad;
        document.getElementById("txtDisponibilidad").innerText = disponibilidad;
        document.getElementById("idCasillero").value = document.getElementById("ID"+numero).innerText;

        // Mostrar o no por disponibilidad
        const btnReservar = document.querySelector(".botones button:first-child");
        if (disponibilidad === "Disponible" && <%=!session.getAttribute("rolUsuario").equals("Administrador")%>) {
            btnReservar.style.display = "inline-block";
        } else {
            btnReservar.style.display = "none";
        }
    }
    function cerrarEmergente() {
        document.getElementById("miEmergente").style.display = "none";
    }
    function reservarCasillero() {
        // Id del casillero a reservar
        let casilleroId = document.getElementById("idCasillero").value;

        // Redireccionar a la página de reserva con el ID del casillero
        window.location.href = "reserva.jsp?casilleroId=" + casilleroId;
    }
</script>
</body>
</html>


