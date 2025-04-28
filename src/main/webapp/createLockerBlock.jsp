<%--
  Created by IntelliJ IDEA.
  User: reneg
  Date: 27/4/2025
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Bloque de Casilleros</title>
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
            width: 90%;
            max-width: 800px;
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

        .form-group {
            display: flex;
            margin-bottom: 20px;
            align-items: center;
        }

        .form-label {
            width: 120px;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .form-input {
            flex-grow: 1;
            padding: 10px;
            border: 2px solid #333;
            border-radius: 5px;
            font-size: 1rem;
        }

        .section-title {
            font-size: 1.3rem;
            font-weight: bold;
            margin: 30px 0 15px 0;
            background-color: #eee;
            padding: 5px 10px;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid rgba(0, 0, 0, 0.2);
        }

        .button {
            padding: 12px 30px;
            font-size: 1rem;
            cursor: pointer;
            border: 2px solid #333;
            border-radius: 5px;
            background-color: white;
            transition: all 0.3s;
            font-weight: bold;
            min-width: 120px;
            text-align: center;
        }

        .button.primary {
            background-color: #333;
            color: white;
        }

        .button:hover {
            transform: scale(1.05);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .button.primary:hover {
            background-color: #555;
        }

        .footer {
            margin-top: 30px;
            font-size: 0.9rem;
            text-align: center;
            color: #666;
        }

        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 15px;
            }

            .form-group {
                flex-direction: column;
                align-items: flex-start;
            }

            .form-label {
                margin-bottom: 5px;
            }

            .form-input {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="page-title">Crear Bloque de Casilleros</h2>

    <form action="CasilleroServlet" method="post">
        <div class="form-group">
            <label class="form-label">Bloque número:</label>
            <input type="number" name="id" class="form-input" min="1" required>
        </div>

        <div class="form-group">
            <label class="form-label">Número de filas:</label>
            <input type="number" name="nroFilas" class="form-input" min="1" required>
        </div>

        <div class="form-group">
            <label class="form-label">Número de columnas:</label>
            <input type="number" name="nroColumnas" class="form-input" min="1" required>
        </div>

        <div class="section-title">Casillero</div>

        <div class="form-group">
            <label class="form-label">Ancho:</label>
            <input type="number" name="ancho" class="form-input" step="0.01" min="0" required>
        </div>

        <div class="form-group">
            <label class="form-label">Profundidad:</label>
            <input type="number" name="profundidad" class="form-input" step="0.01" min="0" required>
        </div>

        <div class="form-group">
            <label class="form-label">Altura:</label>
            <input type="number" name="altura" class="form-input" step="0.01" min="0" required>
        </div>

        <div class="button-container">
            <button type="submit" class="button primary">Crear</button>
            <a href="home.jsp" class="button">Cancelar</a>
        </div>
    </form>
</div>

<div class="footer">
    Sistema de Gestión de Casilleros &copy; 2025
</div>

<script>
    // Validación del formulario
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.querySelector('form');
        form.addEventListener('submit', function(event) {
            const inputs = form.querySelectorAll('input[type="number"]');
            let isValid = true;

            inputs.forEach(input => {
                if (input.value <= 0) {
                    alert('Todos los valores deben ser mayores que cero.');
                    isValid = false;
                }
            });

            if (!isValid) {
                event.preventDefault();
            }
        });
    });
</script>
</body>
</html>