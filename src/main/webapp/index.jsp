<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Page</title>
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
        p {
            color: darkblue;
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        select {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        select option {
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>Login</h2>
    <form action="SvUsuarios" method="get">
        <select name="estado">
            <option value="Estudiante">Estudiante</option>
            <option value="Administrador">Administrador</option>
        </select>
        <input type="email" name="email" placeholder="Email" />
        <input type="password" name="password" placeholder="Password" />
        <button type="submit">Login</button>
        <p style="text-align: center; margin-top: 20px">
            Aun no posees una cuenta? <a href="register.jsp">Registrar</a>
        </p>
    </form>
</div>
</body>
</html>

