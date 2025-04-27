package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "Casillero", schema = "railway", uniqueConstraints = {
        @UniqueConstraint(name = "Casillero_pk", columnNames = {"numero"})
})
public class Casillero {
    @Id
    @Column(name = "id_casillero", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_bloque", nullable = false)
    private BloqueCasillero idBloque;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado", nullable = true)
    private String estado;

    @Column(name = "ancho", nullable = false, precision = 5, scale = 2)
    private BigDecimal ancho;

    @Column(name = "profundidad", nullable = false, precision = 5, scale = 2)
    private BigDecimal profundidad;

    @Column(name = "altura", nullable = false, precision = 5, scale = 2)
    private BigDecimal altura;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BloqueCasillero getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(BloqueCasillero idBloque) {
        this.idBloque = idBloque;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(BigDecimal profundidad) {
        this.profundidad = profundidad;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

}