<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.Casillero" %>
<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ma.gr02_1bt3_622_25a.service.ServicioVerCasilleros" %><%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%BloqueCasillero bloqueCasillero = (BloqueCasillero) request.getAttribute("bloque");%>
<%List<Casillero> casilleros = (List<Casillero>) request.getAttribute("casilleros");%>
<%
    String modoOperacion = new ServicioVerCasilleros().getViewMode(request);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Casilleros AEIS</title>
    <style>
        /* Modern Locker Management System Stylesheet */
        :root {
            --primary: #3f51b5;
            --primary-dark: #303f9f;
            --primary-light: #c5cae9;
            --accent: #ff4081;
            --success: #4caf50;
            --warning: #ff9800;
            --danger: #f44336;
            --occupied: #4e4c4b;
            --pending: #9c27b0;
            --damaged: #f44336;
            --text-light: #ffffff;
            --text-dark: #212121;
            --text-secondary: #757575;
            --background: #f5f5f5;
            --card: #ffffff;
            --shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            --transition: all 0.3s ease;
        }

        body {
            font-family: "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            background-color: var(--background);
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            color: var(--text-dark);
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            max-width: 1200px;
            padding: 1rem;
        }

        /* Header Styling */
        .Titulo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            margin-bottom: 0.5rem;
        }

        .Titulo h1 {
            margin: 0;
            color: var(--primary);
            font-size: 1.8rem;
        }

        #numeroBloque {
            background-color: var(--primary);
            color: var(--text-light);
            padding: 0.2rem 0.8rem;
            border-radius: 8px;
            box-shadow: var(--shadow);
        }

        /* Navigation Buttons */
        .boton-home {
            background-color: var(--primary);
            color: var(--text-light);
            border: none;
            border-radius: 8px;
            padding: 0.8rem 1.5rem;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            box-shadow: var(--shadow);
            transition: var(--transition);
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin: 1rem 0;
        }

        .boton-home:hover {
            background-color: var(--primary-dark);
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        /* Block Navigation Bar */
        .barra-bloques {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            gap: 0.5rem;
            margin: 1rem 0;
            width: 100%;
            max-width: 500px;
            padding: 0.5rem;
            background-color: var(--card);
            border-radius: 12px;
            box-shadow: var(--shadow);
        }

        .boton-bloque {
            background-color: var(--primary-light);
            color: var(--primary-dark);
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            min-width: 2.5rem;
            min-height: 2.5rem;
            width: auto;
            height: auto;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: var(--transition);
            box-shadow: var(--shadow);
        }

        .boton-bloque:hover {
            background-color: var(--primary);
            color: var(--text-light);
            transform: scale(1.05);
        }

        /* Locker Grid */
        .bloque {
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: var(--card);
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 1.5rem;
            margin-top: 1rem;
            width: 100%;
            max-width: 800px;
        }

        .dashboard {
            display: grid;
            grid-template-columns: repeat(<%= bloqueCasillero.getNroColumnas()%>, 1fr);
            grid-template-rows: repeat(<%= bloqueCasillero.getNroFilas()%>, 1fr);
            gap: 0.8rem;
            padding: 1rem;
            width: auto;
            background-color: var(--background);
            border-radius: 8px;
            box-shadow: inset 0 0 8px rgba(0, 0, 0, 0.1);
        }

        .celda {
            background-color: var(--success);
            border-radius: 8px;
            box-shadow: var(--shadow);
            overflow: hidden;
            transition: var(--transition);
            aspect-ratio: 1 / 1;
            position: relative;
            width: 5rem;
            height: 5rem;
        }

        .celda:hover {
            transform: translateY(-3px) scale(1.03);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        button.casillero {
            width: 100%;
            height: 100%;
            background: none;
            border: none;
            padding: 0;
            margin: 0;
            cursor: pointer;
            font-size: 1.25rem;
            font-weight: bold;
            color: var(--text-light);
            display: flex;
            align-items: center;
            justify-content: center;
            transition: var(--transition);
            position: relative;
        }

        button.casillero::before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            transform: scale(0);
            transition: transform 0.3s ease;
        }

        button.casillero:hover::before {
            transform: scale(1.5);
        }

        .seccionInferior {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: var(--card);
            width: 100%;
            padding: 1rem 0;
            border-radius: 0 0 8px 8px;
        }

        /* Status colors for lockers */
        .disponible {
            background-color: var(--success);
        }

        .ocupado {
            background-color: var(--occupied);
        }

        .pendiente {
            background-color: var(--pending);
        }

        .averiado {
            background-color: var(--damaged);
        }

        /* Popup Modal */
        .emergente {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            backdrop-filter: blur(3px);
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        .emergente-contenido {
            background: var(--card);
            padding: 2rem;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            width: 90%;
            max-width: 400px;
            animation: modalFade 0.3s ease;
        }

        @keyframes modalFade {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .emergente-contenido h2 {
            color: var(--primary);
            margin-top: 0;
            border-bottom: 2px solid var(--primary-light);
            padding-bottom: 0.5rem;
        }

        .Disponibilidad,
        .Dimensiones {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 0;
            border-bottom: 1px solid var(--primary-light);
        }

        .Disponibilidad h3,
        .Dimensiones h3 {
            margin: 0;
            color: var(--text-secondary);
            font-size: 1rem;
            font-weight: 500;
        }

        #txtDisponibilidad,
        #textoDimensiones {
            font-weight: 500;
            color: var(--text-dark);
            margin: 0;
        }

        select {
            margin-left: 10px;
            padding: 0.5rem;
            border-radius: 6px;
            border: 1px solid var(--primary-light);
            width: auto;
            font-family: inherit;
            color: var(--text-dark);
            background-color: var(--background);
            cursor: pointer;
            transition: var(--transition);
        }

        select:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 2px var(--primary-light);
        }

        .disponibilidadSelect {
            width: auto;
            min-width: 120px;
        }

        .botonSelect {
            margin-left: 10px;
            background-color: var(--primary);
            color: var(--text-light);
            border: none;
            border-radius: 6px;
            padding: 0.5rem 1rem;
            cursor: pointer;
            transition: var(--transition);
        }

        .botonSelect:hover {
            background-color: var(--primary-dark);
        }

        .botones {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 1.5rem;
            gap: 1rem;
        }

        .botones button {
            padding: 0.6rem 1.2rem;
            border-radius: 6px;
            font-weight: 500;
            cursor: pointer;
            transition: var(--transition);
            border: none;
        }

        .botones button:first-child {
            background-color: var(--accent);
            color: var(--text-light);
        }

        .botones button:first-child:hover {
            background-color: #e91e63;
        }

        .botones button:last-child {
            background-color: var(--text-secondary);
            color: var(--text-light);
        }

        .botones button:last-child:hover {
            background-color: #616161;
        }

        /* Legend for locker status */
        .locker-legend {
            display: flex;
            flex-wrap: wrap;
            gap: 1rem;
            margin: 1rem 0;
            padding: 1rem;
            background-color: var(--card);
            border-radius: 8px;
            box-shadow: var(--shadow);
            width: 100%;
            max-width: 800px;
            justify-content: center;
        }

        .legend-item {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .legend-color {
            width: 1rem;
            height: 1rem;
            border-radius: 4px;
        }

        .legend-disponible {
            background-color: var(--success);
        }

        .legend-ocupado {
            background-color: var(--occupied);
        }

        .legend-pendiente {
            background-color: var(--pending);
        }

        .legend-averiado {
            background-color: var(--damaged);
        }

        /* Spinner for loading state */
        .spinner {
            display: inline-block;
            width: 1rem;
            height: 1rem;
            border: 2px solid rgba(255, 255, 255, 0.3);
            border-radius: 50%;
            border-top-color: white;
            animation: spin 0.8s linear infinite;
            margin-right: 0.5rem;
        }

        @keyframes spin {
            to {
                transform: rotate(360deg);
            }
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .dashboard {
                gap: 0.5rem;
            }

            .celda {
                width: 4rem;
                height: 4rem;
            }

            .Disponibilidad,
            .Dimensiones {
                flex-direction: column;
                align-items: flex-start;
                gap: 0.5rem;
            }

            .emergente-contenido {
                padding: 1.5rem;
            }
        }

        @media (max-width: 480px) {
            .celda {
                width: 3.5rem;
                height: 3.5rem;
            }

            button.casillero {
                font-size: 1rem;
            }

            .barra-bloques {
                flex-wrap: wrap;
            }

            .Titulo h1 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="Titulo">
        <h1>Bloque Casilleros N°:</h1>
        <h1 id="numeroBloque"><%= bloqueCasillero.getId() %></h1>
    </div>

    <div style="text-align: center; width: 20%;">
        <form action="home.jsp" method="get">
            <button type="submit" class="boton-home">Volver al inicio</button>
        </form>
    </div>

    <!-- Barra de navegación de bloques -->
    <div class="barra-bloques">
        <%--  Posible refactorización: Inline Tempo   --%>
        <%for (int i = 1; i <= (Integer) request.getAttribute("nroBloques"); i++){%>
        <form action="CasilleroServlet" method="get" style="display: inline;">
            <input type="hidden" name="idBloque" value="<%=i%>" />
            <button type="submit" class="boton-bloque"><%=i%></button>
        </form>
        <%}%>
    </div>

    <!-- Leyenda de estados de casilleros -->
    <div class="locker-legend">
        <div class="legend-item">
            <div class="legend-color legend-disponible"></div>
            <span>Disponible</span>
        </div>
        <div class="legend-item">
            <div class="legend-color legend-ocupado"></div>
            <span>Ocupado</span>
        </div>
        <div class="legend-item">
            <div class="legend-color legend-pendiente"></div>
            <span>Pendiente</span>
        </div>
        <div class="legend-item">
            <div class="legend-color legend-averiado"></div>
            <span>Averiado</span>
        </div>
    </div>

    <div class="bloque">
        <h2 id="nombreBloque">Bloque <%= bloqueCasillero.getNombreBloque()%></h2>
        <div class="dashboard">
            <%
                if (casilleros != null && !casilleros.isEmpty()) {
                    for (Casillero c : casilleros) {
                        String estadoClass = "";
                        switch(c.getEstado()) {
                            case "Disponible":
                                estadoClass = "disponible";
                                break;
                            case "Ocupado":
                                estadoClass = "ocupado";
                                break;
                            case "Pendiente":
                                estadoClass = "pendiente";
                                break;
                            case "Averiado":
                                estadoClass = "averiado";
                                break;
                            default:
                                estadoClass = "disponible";
                        }
            %>
            <%--  Posible refactorización: Extract Variable  --%>
            <%int numeroCasillero = c.getNumero();%>
            <div class="celda <%= estadoClass %>">
                <button class="casillero" onclick="mostrarEmergente(<%= numeroCasillero%>, '<%= c.getEstado() %>')"><%= numeroCasillero%></button>
                <%System.out.println(numeroCasillero);%>
                <p id="ID<%= numeroCasillero%>" hidden><%= c.getId()%></p>
                <p id="Disponibilidad<%= numeroCasillero%>" hidden><%= c.getEstado()%></p>
                <p id="Dimensiones<%= numeroCasillero%>" hidden><%= c.getAltura() +"x"+c.getAncho()+"x"+c.getProfundidad()%></p>
            </div>
            <%
                    }
                }
                // Si no hay casilleros, no se muestra nada
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
        <h2 id="modalTitle">Casillero</h2>
        <h2 id="numeroCasillero"></h2>
        <div class="Disponibilidad">
            <h3>Disponibilidad:</h3>
            <form action="CasilleroServlet" method="post" style="<%=(session.getAttribute("rolUsuario").equals("Estudiante") ? "display:none;" : "")%>">
                <input type="hidden" id="idCasillero" name="idCasillero">
                <input type="hidden" name="idBloque" value="<%= bloqueCasillero.getId() %>">
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
            <%
                String textoBoton = modoOperacion.equals("intercambiar") ? "Intercambiar" : "Reservar";
                String funcionBoton = modoOperacion.equals("intercambiar") ? "intercambiarCasillero()" : "reservarCasillero()";
            %>

            <button onclick="<%= funcionBoton %>"
                    style="<%=(session.getAttribute("rolUsuario").equals("Administrador") ? "display:none;" : "")%>">
                <%= textoBoton %>
            </button>

            <button onclick="cerrarEmergente()">Cerrar</button>
        </div>
    </div>
</div>

<!-- Codigo JavaScript para controlar las funciones de la pestaña emergente -->
<script>
    // Variable para determinar si el usuario es estudiante
    const isStudentRole = <%=!session.getAttribute("rolUsuario").equals("Administrador")%>;

    // Resaltar el botón del bloque actual
    document.addEventListener("DOMContentLoaded", function() {
        const blockNumber = document.getElementById("numeroBloque").textContent.trim();
        const blockButtons = document.querySelectorAll(".boton-bloque");

        blockButtons.forEach(button => {
            if (button.textContent.trim() === blockNumber) {
                button.style.backgroundColor = "var(--primary-dark)";
                button.style.color = "var(--text-light)";
                button.style.fontWeight = "bold";
            }
        });
    });

    function mostrarEmergente(numero, estado) {
        const modal = document.getElementById("miEmergente");
        const modalTitle = document.querySelector(".emergente-contenido h2");

        // Actualizar título con número de casillero
        modalTitle.textContent = `Casillero ${numero}`;

        // Obtener datos del casillero
        document.getElementById("numeroCasillero").innerText = numero;
        document.getElementById("textoDimensiones").innerText = document.getElementById("Dimensiones"+numero).innerText;
        const disponibilidad = document.getElementById("Disponibilidad"+numero).innerText;
        document.getElementById("disponibilidadSelect").value = disponibilidad;
        document.getElementById("txtDisponibilidad").innerText = disponibilidad;
        document.getElementById("idCasillero").value = document.getElementById("ID"+numero).innerText;

        // Aplicar clase de estado al título
        modalTitle.className = "";
        modalTitle.classList.add(disponibilidad.toLowerCase());

        // Mostrar o no botón de reservar según disponibilidad
        const btnReservar = document.querySelector(".botones button:first-child");
        if (disponibilidad === "Disponible" && isStudentRole) {
            btnReservar.style.display = "inline-block";
        } else {
            btnReservar.style.display = "none";
        }

        // Mostrar modal con animación
        modal.style.display = "flex";
    }

    function cerrarEmergente() {
        document.getElementById("miEmergente").style.display = "none";
    }

    function reservarCasillero() {
        // Id del casillero a reservar
        const casilleroId = document.getElementById("idCasillero").value;
        const nombreBloque = document.getElementById("nombreBloque").textContent.trim();
        const numeroCasillero = document.getElementById("numeroCasillero").textContent.trim();
        const numeroBloque = document.getElementById("numeroBloque").textContent.trim();

        // Añadir feedback visual antes de redireccionar
        const btn = event.target;
        const originalText = btn.textContent;
        btn.innerHTML = '<span class="spinner"></span> Procesando...';
        btn.disabled = true;

        // Redireccionar a la página de reserva con el ID del casillero
        setTimeout(function() {
            window.location.href = "student/lockerReservation.jsp?casilleroId=" + casilleroId +
                "&nombreBloque=" + nombreBloque +
                "&numeroCasillero=" + numeroCasillero +
                "&numeroBloque=" + numeroBloque;
        }, 500);
    }

    function intercambiarCasillero() {
        const casilleroId = document.getElementById("idCasillero").value;

        const btn = event.target;
        const originalText = btn.textContent;
        btn.innerHTML = '<span class="spinner"></span> Procesando...';
        btn.disabled = true;

        // Redirigir al servlet para intercambio
        setTimeout(function () {
            window.location.href = "IntercambioCasilleroServlet?casilleroId=" + casilleroId;
        }, 500);
    }
</script>
</body>
</html>