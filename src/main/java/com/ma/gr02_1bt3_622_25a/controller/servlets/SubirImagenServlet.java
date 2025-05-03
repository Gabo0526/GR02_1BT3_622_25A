package com.ma.gr02_1bt3_622_25a.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/subirImagen")
@MultipartConfig
public class SubirImagenServlet extends HttpServlet {
    private static final String RUTA_ABSOLUTA = "C:/AEISResources/images/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part archivo = request.getPart("imagen");

        if (archivo == null || archivo.getSize() == 0) {
            response.getWriter().println("No se ha enviado ningun archivo.");
            return;
        }

        if (!archivo.getContentType().startsWith("image/")) {
            response.getWriter().println("Solo se permiten archivos de imagen.");
            return;
        }

        // Obtener nombre original y extraer la extensión
        String nombreOriginal = archivo.getSubmittedFileName();
        String extension = obtenerExtension(nombreOriginal);

        // Generar nombre único con UUID
        String nombreArchivoUnico = UUID.randomUUID() + extension;

        // Crear carpeta si no existe
        File carpeta = new File(RUTA_ABSOLUTA);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Guardar archivo con nombre único
        File destino = new File(carpeta, nombreArchivoUnico);
        archivo.write(destino.getAbsolutePath());

        System.out.println("Archivo guardado en: " + destino.getAbsolutePath());
        response.getWriter().println("Imagen subida con exito: " + nombreArchivoUnico);
    }

    // Metodo auxiliar para obtener la extensión de un archivo
    private String obtenerExtension(String nombreArchivo) {
        int punto = nombreArchivo.lastIndexOf(".");
        return (punto >= 0) ? nombreArchivo.substring(punto) : "";
    }
}
