<%@ page import="com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero" %>
<%@ page import="java.util.List" %>
<%--Created by IntelliJ IDEA.
User: DVC
Date: 11/05/2025
Time: 16:51
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<AlquilerCasillero> alquileres = (List<AlquilerCasillero>) request.getAttribute("alquileres");%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Casilleros - AEIS</title>
    <style>
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

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        }

        body {
            background-color: var(--background);
            color: var(--text-dark);
            min-height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem 1rem;
        }

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 1.5rem;
            flex-wrap: wrap;
            gap: 1rem;
        }

        .title {
            font-size: 1.8rem;
            font-weight: bold;
            color: var(--primary);
        }

        .back-button {
            background-color: var(--primary);
            color: var(--text-light);
            border: none;
            border-radius: 0.5rem;
            padding: 0.6rem 1.2rem;
            font-size: 0.9rem;
            font-weight: 500;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 0.5rem;
            transition: var(--transition);
        }

        .back-button:hover {
            background-color: var(--primary-dark);
        }

        .card-container {
            background-color: var(--card);
            border-radius: 0.75rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 1.5rem;
            margin-bottom: 1.5rem;
        }

        .locker-grid {
            display: grid;
            gap: 1.5rem;
            width: 100%;
        }

        /* Responsive grid based on number of lockers */
        .grid-cols-1 {
            grid-template-columns: 1fr;
        }

        .grid-cols-2 {
            grid-template-columns: repeat(2, 1fr);
        }

        .grid-cols-3 {
            grid-template-columns: repeat(3, 1fr);
        }

        .grid-cols-4 {
            grid-template-columns: repeat(4, 1fr);
        }

        .locker-card {
            border: 2px solid var(--primary-light);
            border-radius: 0.5rem;
            transition: var(--transition);
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        .locker-card:hover {
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
            transform: translateY(-2px);
        }

        .locker-content {
            padding: 1.5rem;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            flex: 1;
            gap: 0.75rem;
        }

        .block-name {
            font-size: 1.5rem;
            font-weight: bold;
            color: var(--primary);
            margin-top: 0.5rem;
        }

        .locker-number {
            font-size: 1.25rem;
            font-weight: 600;
        }

        .locker-info {
            color: var(--text-secondary);
            margin: 0.25rem 0;
        }

        .info-label {
            font-weight: 500;
        }

        .button-container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 0.75rem;
            padding: 1rem;
            background-color: var(--primary-light);
            border-top: 1px solid var(--primary-light);
        }

        .btn {
            padding-left: 0.4rem;
            padding-right: 0.15rem;
            border-radius: 0.375rem;
            font-weight: 500;
            cursor: pointer;
            transition: var(--transition);
            border: none;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .btn-outline {
            background-color: var(--card);
            color: var(--primary);
            border: 1px solid var(--primary);
        }

        .btn-outline:hover {
            background-color: var(--primary-light);
        }

        .btn-danger {
            background-color: var(--danger);
            color: var(--text-light);
        }

        .btn-danger:hover {
            background-color: #d32f2f;
        }

        .legend {
            background-color: var(--card);
            border-radius: 0.5rem;
            box-shadow: var(--shadow);
            padding: 1rem;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 1rem;
        }

        .legend-item {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .legend-color {
            width: 1rem;
            height: 1rem;
            border-radius: 0.25rem;
        }

        .color-disponible {
            background-color: var(--success);
        }

        .color-ocupado {
            background-color: var(--occupied);
        }

        .color-pendiente {
            background-color: var(--pending);
        }

        .color-averiado {
            background-color: var(--damaged);
        }

        /* Single locker should be taller */
        .single-locker {
            height: 500px;
        }

        .multi-locker {
            height: 350px;
        }

        /* Icons */
        .icon {
            width: 16px;
            height: 16px;
            display: inline-block;
        }

        .icon-arrow-left {
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='white' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpath d='m12 19-7-7 7-7'/%3E%3Cpath d='M19 12H5'/%3E%3C/svg%3E");
            background-size: cover;
        }

        .icon-refresh {
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpath d='M3 12a9 9 0 0 1 9-9 9.75 9.75 0 0 1 6.74 2.74L21 8'/%3E%3Cpath d='M21 3v5h-5'/%3E%3Cpath d='M21 12a9 9 0 0 1-9 9 9.75 9.75 0 0 1-6.74-2.74L3 16'/%3E%3Cpath d='M3 21v-5h5'/%3E%3C/svg%3E");
            background-size: cover;
        }

        .icon-alert {
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='white' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpath d='m21.73 18-8-14a2 2 0 0 0-3.48 0l-8 14A2 2 0 0 0 4 21h16a2 2 0 0 0 1.73-3Z'/%3E%3Cpath d='M12 9v4'/%3E%3Cpath d='M12 17h.01'/%3E%3C/svg%3E");
            background-size: cover;
        }

        /* Responsive styles */
        @media (max-width: 768px) {
            .grid-cols-2, .grid-cols-3, .grid-cols-4 {
                grid-template-columns: 1fr;
            }
        }

        @media (min-width: 768px) and (max-width: 1024px) {
            .grid-cols-3, .grid-cols-4 {
                grid-template-columns: repeat(2, 1fr);
            }
        }
        .footer {
            margin-top: 30px;
            font-size: 0.9rem;
            text-align: center;
            color: #666;
            position: absolute;
            bottom: 10px;
            width: 100%;
            left: 0;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <h1 class="title">Mis Casilleros</h1>
        <form action="home.jsp" method="get">
            <button type="submit" class="back-button">
                <span class="icon icon-arrow-left"></span>
                Volver al inicio
            </button>
        </form>
    </div>

    <div class="card-container">
        <!-- Cuadrícula de alquileres con valores "quemados" -->
        <div class="locker-grid grid-cols-4">
            <%if (alquileres != null && !alquileres.isEmpty()) {
                for (AlquilerCasillero a : alquileres) {
                    int numeroCasillero = a.getIdCasillero().getNumero();%>

            <!-- Casillero 1 -->
            <div class="locker-card multi-locker">
                <div class="locker-content">
                    <h2 class="block-name"><%= a.getIdCasillero().getIdBloque().getNombreBloque() %></h2>
                    <div class="locker-number">Casillero #<%= a.getIdCasillero().getNumero()%></div>
                    <div class="locker-info">
                        <span class="info-label">Dimensiones:</span> <%= a.getIdCasillero().getAncho()+ "x"+ a.getIdCasillero().getAltura()+"x"+a.getIdCasillero().getProfundidad()%> cm
                    </div>
                    <div class="locker-info">
                        <p id="ID<%= numeroCasillero%>" hidden><%= a.getIdCasillero().getId()%></p>
                        <p id="ID<%= a.getId()%>" hidden><%= a.getId()%></p>
                        <span class="info-label">Vencimiento:</span> <%= a.getFechaVencimiento() %>
                    </div>
                </div>
                <div class="button-container">
                    <button class="btn btn-outline">
                        <span class="icon icon-refresh"></span>
                        Intercambiar
                    </button>
                    <button class="btn btn-danger">
                        <span class="icon icon-alert"></span>
                        Notificar
                        Daño
                    </button>
                </div>
            </div>

            <%
                    }
                }
            %>
        </div>
    </div>

</div>

<div class="footer">
    Sistema de Gestión de Casilleros &copy; 2025
</div>

</body>
</html>