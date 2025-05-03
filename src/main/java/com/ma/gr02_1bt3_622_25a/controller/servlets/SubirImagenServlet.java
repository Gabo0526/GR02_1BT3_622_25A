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

@WebServlet("/subirImagen")
@MultipartConfig
public class SubirImagenServlet extends HttpServlet {

    // Ruta dentro de webapp (ej: src/main/webapp/imagenes)
    private static final String RUTA_RELATIVA = "D:/AEISResources/images/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener archivo
        Part archivo = request.getPart("imagen");
        String nombreArchivo = archivo.getSubmittedFileName();

        // Ruta absoluta dentro del servidor (carpeta webapp/imagenes)
        String rutaAbsoluta = getServletContext().getRealPath(RUTA_RELATIVA);
        File carpeta = new File(rutaAbsoluta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();  // Crear la carpeta si no existe
        }

        // Guardar archivo
        archivo.write(rutaAbsoluta + File.separator + nombreArchivo);

        System.out.println("Ruta absoluta: " + rutaAbsoluta);


        response.getWriter().println("Imagen subida con éxito: " + nombreArchivo);
    }
}


