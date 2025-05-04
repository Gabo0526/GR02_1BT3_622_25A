<%--
  Created by IntelliJ IDEA.
  User: reneg
  Date: 3/5/2025
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reserva de Casillero</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        .section {
            margin-bottom: 25px;
        }
        .section-title {
            font-weight: bold;
            margin-bottom: 10px;
            color: #34495e;
        }
        .contract-box {
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 4px;
            background-color: #f9f9f9;
            margin-bottom: 15px;
        }
        .contract-link {
            color: #3498db;
            text-decoration: none;
        }
        .contract-link:hover {
            text-decoration: underline;
        }
        .checkbox-container {
            margin: 15px 0;
        }
        .upload-section {
            border: 1px dashed #ccc;
            padding: 20px;
            text-align: center;
            border-radius: 4px;
            margin-bottom: 20px;
            background-color: #f9f9f9;
        }
        .file-info {
            font-size: 0.85em;
            color: #7f8c8d;
            margin-top: 10px;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .btn-cancel {
            background-color: #e74c3c;
            color: white;
        }
        .btn-cancel:hover {
            background-color: #c0392b;
        }
        .btn-reserve {
            background-color: #2ecc71;
            color: white;
        }
        .btn-reserve:hover {
            background-color: #27ae60;
        }
        .btn-reserve:disabled {
            background-color: #95a5a6;
            cursor: not-allowed;
        }
        .error-message {
            color: #e74c3c;
            font-size: 0.9em;
            margin-top: 5px;
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Reserva de Casillero</h1>

    <!-- Mensaje de error del servidor si existe -->
    <% if(request.getAttribute("error") != null) { %>
    <div style="color: red; margin-bottom: 15px;">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="../subirImagen" method="post" enctype="multipart/form-data" id="reservaForm">
        <!-- Hidden field to store the casillero ID -->
        <input type="hidden" id="casilleroId" name="casilleroId" value="<%= request.getParameter("casilleroId") %>">
        <input type="hidden" id="precioId" name="precio" value="6.5">
        <div class="section">
            <div class="section-title">Información del Casillero</div>
            <div class="contract-box">
                <p>Estás por reservar el casillero número: <strong><%= request.getParameter("casilleroId") %></strong></p>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Información del Contrato</div>
            <div class="contract-box">
                <p>Por favor, revise detenidamente los términos y condiciones del contrato antes de continuar.</p>
                <p><a href="https://forested-asterisk-730.notion.site/T-rminos-y-Condiciones-Lockers-1c9c6c5f983380a29b55c7e34991314b" target="_blank" class="contract-link">Ver contrato completo</a></p>
            </div>

            <div class="checkbox-container">
                <input type="checkbox" id="aceptarTerminos" name="aceptarTerminos">
                <label for="aceptarTerminos">He leído y acepto los términos y condiciones del contrato</label>
                <div id="terminosError" class="error-message">Debe aceptar los términos para continuar</div>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Subir Comprobante</div>
            <div class="upload-section">
                <input type="file" id="comprobante" name="imagen" accept=".pdf,.jpg,.jpeg,.png">
                <div class="file-info">
                    Formatos aceptados: PDF, JPG, JPEG, PNG (Tamaño máximo: 5MB)
                </div>
                <div id="comprobanteError" class="error-message">Debe subir un comprobante para continuar</div>
            </div>
        </div>

        <div class="button-group">
            <button type="button" class="btn btn-cancel" onclick="cancelarReserva()">Cancelar</button>
            <button type="submit" class="btn btn-reserve" id="btnReservar" disabled>Reservar</button>
        </div>
    </form>
</div>

<script>
    // Variables para validación
    const checkboxTerminos = document.getElementById('aceptarTerminos');
    const inputComprobante = document.getElementById('comprobante');
    const btnReservar = document.getElementById('btnReservar');
    const terminosError = document.getElementById('terminosError');
    const comprobanteError = document.getElementById('comprobanteError');

    // Función para validar el formulario
    function validarFormulario() {
        let esValido = true;

        // Validar términos aceptados
        if (!checkboxTerminos.checked) {
            terminosError.style.display = 'block';
            esValido = false;
        } else {
            terminosError.style.display = 'none';
        }

        // Validar comprobante subido
        if (!inputComprobante.files || inputComprobante.files.length === 0) {
            comprobanteError.style.display = 'block';
            esValido = false;
        } else {
            // Validar tamaño del archivo (máximo 5MB)
            const fileSize = inputComprobante.files[0].size / 1024 / 1024; // en MB
            if (fileSize > 5) {
                comprobanteError.textContent = 'El archivo es demasiado grande. El tamaño máximo es 5MB.';
                comprobanteError.style.display = 'block';
                esValido = false;
            } else {
                comprobanteError.style.display = 'none';
            }
        }

        // Habilitar o deshabilitar botón de reserva
        btnReservar.disabled = !esValido;

        return esValido;
    }

    // Función para cancelar la reserva
    function cancelarReserva() {
        if (confirm('¿Está seguro que desea cancelar la reserva?')) {
            // Get the ID of the bloque from the URL parameter
            const urlParams = new URLSearchParams(window.location.search);
            const bloqueId = urlParams.get('bloqueId') || "1"; // Default to 1 if not present

            // Redirect to the servlet to handle the request properly
            window.location.href = 'CasilleroServlet?idBloque=' + bloqueId;
        }
    }

    // Eventos para validación en tiempo real
    checkboxTerminos.addEventListener('change', validarFormulario);
    inputComprobante.addEventListener('change', validarFormulario);

    // Validar formulario al enviar
    document.getElementById('reservaForm').addEventListener('submit', function(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    });

    // Validación inicial
    validarFormulario();
</script>
</body>
</html>