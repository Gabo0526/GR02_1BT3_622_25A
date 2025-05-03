<%--
  Created by IntelliJ IDEA.
  User: reneg
  Date: 27/4/2025
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inicio - Sistema de Casilleros</title>
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
      width: 100%;
      height: 100vh;
      margin: 0;
      padding: 20px;
      border: 3px solid #333;
      border-radius: 5px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
      position: relative;
      box-sizing: border-box;
    }

    .welcome-message {
      border-bottom: 1px solid rgba(0, 0, 0, 0.2);
      padding-bottom: 5px;
      margin-bottom: 5px;
      font-size: 1.6rem;
      font-weight: normal;
    }

    .user-role {
      color: #666;
      font-size: 1.1rem;
      margin-bottom: 25px;
      padding-bottom: 15px;
      border-bottom: 1px solid rgba(0, 0, 0, 0.2);
    }

    .role-badge {
      display: inline-block;
      background-color: #f0f0f0;
      padding: 3px 10px;
      border-radius: 15px;
      font-weight: bold;
      margin-left: 5px;
      border: 1px solid #ddd;
    }

    .role-admin {
      background-color: #e6f2ff;
      border-color: #b3d9ff;
      color: #0066cc;
    }

    .role-student {
      background-color: #e6ffee;
      border-color: #b3ffcc;
      color: #009933;
    }

    .button-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      grid-template-rows: repeat(2, 1fr);
      grid-gap: 20px;
      margin-top: 30px;
    }

    .chalk-button {
      background-color: transparent;
      border: 2px solid #333;
      border-radius: 15px;
      color: #333;
      padding: 15px 10px;
      text-align: center;
      text-decoration: none;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 1.1rem;
      cursor: pointer;
      transition: all 0.3s;
      height: 80px;
      box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
      position: relative;
      overflow: hidden;
    }

    .chalk-button:hover {
      background-color: rgba(0, 0, 0, 0.05);
      transform: scale(1.05);
    }

    .chalk-button.active {
      background-color: rgba(0, 0, 0, 0.1);
      border-width: 3px;
      font-weight: bold;
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

    .logout-btn {
      position: absolute;
      top: 15px;
      right: 15px;
      color: #666;
      text-decoration: none;
      font-size: 0.9rem;
    }

    .logout-btn:hover {
      color: #333;
      text-decoration: underline;
    }

    @media (max-width: 768px) {
      .button-grid {
        grid-template-columns: 1fr 1fr;
      }
    }

    @media (max-width: 480px) {
      .button-grid {
        grid-template-columns: 1fr;
      }
    }
  </style>
</head>
<body>

<div class="container">
  <h2 class="welcome-message">
    Bienvenido:
    <%
      String nombreUsuario = (String) session.getAttribute("nombreUsuario");
      if (nombreUsuario != null) {
        System.out.println(nombreUsuario);
      } else {
        out.println("Usuario");
      }
    %>
  </h2>

  <div class="user-role">
    Rol:
    <%
      String rolUsuario = (String) session.getAttribute("rolUsuario");
      if ("Administrador".equals(rolUsuario)) {
        out.println("<span class='role-badge role-admin'>Administrador</span>");
      } else if ("Estudiante".equals(rolUsuario)) {
        out.println("<span class='role-badge role-student'>Estudiante</span>");
      } else {
        out.println("<span class='role-badge'>Invitado</span>");
      }
    %>
  </div>


  <%
    if ("Estudiante".equals(rolUsuario)) {
  %>
  <div class="button-grid">
    <a href="CasilleroServlet?idBloque=1" class="chalk-button active">Ver Casilleros</a>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
  </div>
  <% } else if ("Administrador".equals(rolUsuario)) { %>
  <div class="button-grid">
    <a href="CasilleroServlet?idBloque=1" class="chalk-button active">Ver Casilleros</a>
    <a href="createLockerBlock.jsp" class="chalk-button active">Crear Bloque</a>
    <a href="verSolicitudes.jsp" class="chalk-button active">Ver Solicitudes</a>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
    <div class="chalk-button" style="opacity: 0.5;"></div>
  </div>
  <% } else { %>
  <p>Por favor, inicie sesión para acceder a las funcionalidades.</p>
  <% } %>

  <a href="logout.jsp" class="logout-btn">Cerrar sesión</a>
</div>

<div class="footer">
  Sistema de Gestión de Casilleros &copy; 2025
</div>

<script>
  // Pequeña animación para los botones
  document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.chalk-button');
    buttons.forEach(button => {
      button.addEventListener('mouseenter', function() {
        this.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.2)';
      });
      button.addEventListener('mouseleave', function() {
        this.style.boxShadow = '2px 2px 5px rgba(0, 0, 0, 0.2)';
      });
    });
  });
</script>
</body>
</html>