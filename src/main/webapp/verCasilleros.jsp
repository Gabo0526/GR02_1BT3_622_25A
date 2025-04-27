<%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            grid-template-columns: repeat(3, 100px); /* 3 columnas */
            grid-template-rows: repeat(3, 100px); /* 3 filas */
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
    </style>
</head>
<body>
<div class="container">
    <div class="Titulo">
        <h1>Bloque Casilleros</h1>
    </div>
    <div class="bloque">
        <div class="dashboard">
            <div class="celda">
                <button class="casillero" onclick="mostrarEmergente(1)">1</button>
            </div>
            <div class="celda">2</div>
            <div class="celda">3</div>
            <div class="celda">4</div>
            <div class="celda">5</div>
            <div class="celda">6</div>
            <div class="celda">7</div>
            <div class="celda">8</div>
            <div class="celda">9</div>
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
        <h2>¡Hola!</h2>
        <p id="textoEmergente">Este es un mensaje emergente.</p>
        <button onclick="cerrarEmergente()">Cerrar</button>
    </div>
</div>
<!-- Codigo JavaScript para controlar las funciones de la pestaña emergente -->
<script>
    function mostrarEmergente(numero) {
        document.getElementById("miEmergente").style.display = "flex";
        document.getElementById("textoEmergente").innerText =
            "Has seleccionado el casillero " + numero;
    }
    function cerrarEmergente() {
        document.getElementById("miEmergente").style.display = "none";
    }
</script>
</body>
</html>

