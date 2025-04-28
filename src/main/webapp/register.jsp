<%--
  Created by IntelliJ IDEA.
  User: DVC
  Date: 27/4/2025
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Register Page</title>
  <style>
    body {
      font-family: Arial, Helvetica, sans-serif;
      background-color: darkgrey;
      margin: 0;
      padding: 0;
      height: 100vh;

      display: flex;
      justify-content: center;
      align-items: center;
    }

    .card {
      background-color: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 300px;
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
      margin-bottom: 10px;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
  </style>
</head>
<body>
<div class="card">
  <h2>Registro</h2>
  <form action="UsuarioServlet" method="post">
    <input type="text" name="cedula" placeholder="Cedula" />
    <input type="text" name="nombre" placeholder="Nombre" />
    <input type="text" name="apellido" placeholder="Apellido" />
    <input type="text" name="codigoUnico" placeholder="Codigo Unico" />
    <input type="email" name="email" placeholder="Email" />
    <input type="password" name="password" placeholder="Password" />
    <select name="facultad">
      <option value="Ciencias">Ciencias</option>
      <option value="Ciencias Administrativas">
        Ciencias Administrativas
      </option>
      <option value="Civil y Ambiental">Civil y Ambiental</option>
      <option value="Electrica y Electronica">
        Electrica y Electronica
      </option>
      <option value="Geologia y Petroleos">Geologia y Petroleos</option>
      <option value="Mecanica">Mecanica</option>
      <option value="Quimica y Agroindustria">
        Quimica y Agroindustria
      </option>
      <option value="Sistemas">Sistemas</option>
      <option value="ESFOT">ESFOT</option>
      <option value="Formacion Basica">Formacion Basica</option>
    </select>
    <button type="submit">Registrarse</button>
  </form>
</div>
</body>
</html>
