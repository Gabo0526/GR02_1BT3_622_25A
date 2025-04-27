package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario", schema = "railway")
public class Usuario {
    @Id
    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "codigo_unico", length = 9)
    private String codigoUnico;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "clave", nullable = false, length = 20)
    private String clave;

    @Lob
    @Column(name = "facultad", nullable = false)
    private String facultad;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

}