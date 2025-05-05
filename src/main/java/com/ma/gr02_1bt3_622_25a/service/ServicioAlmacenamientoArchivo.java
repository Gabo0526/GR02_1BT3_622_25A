package com.ma.gr02_1bt3_622_25a.service;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ServicioAlmacenamientoArchivo {
    private static final String RUTA_ABSOLUTA = "C:/AEISResources/images/";

    public String guardarImagen(Part archivo) throws IOException {
        if (archivo == null || archivo.getSize() == 0) {
            throw new IllegalArgumentException("No se ha enviado ningún archivo.");
        }

        if (!archivo.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Solo se permiten archivos de imagen.");
        }

        String nombreOriginal = archivo.getSubmittedFileName();
        String extension = obtenerExtension(nombreOriginal);
        String nombreUnico = UUID.randomUUID() + extension;

        File carpeta = new File(RUTA_ABSOLUTA);
        if (!carpeta.exists()) carpeta.mkdirs();

        File destino = new File(carpeta, nombreUnico);
        archivo.write(destino.getAbsolutePath());

        return RUTA_ABSOLUTA + nombreUnico;
    }

    private String obtenerExtension(String nombreArchivo) {
        int punto = nombreArchivo.lastIndexOf(".");
        return (punto >= 0) ? nombreArchivo.substring(punto) : "";
    }
}
