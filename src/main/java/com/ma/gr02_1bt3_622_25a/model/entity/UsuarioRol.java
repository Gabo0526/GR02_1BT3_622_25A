package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario_Rol", schema = "railway")
@NamedQuery(name = "UsuarioRol.findByUser", query = "SELECT ur FROM UsuarioRol ur JOIN FETCH ur.idRol where ur.idUsuario = ?1")
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @MapsId("idRol")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol idRol;

    public UsuarioRolId getId() {
        return id;
    }

    public void setId(UsuarioRolId id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

}