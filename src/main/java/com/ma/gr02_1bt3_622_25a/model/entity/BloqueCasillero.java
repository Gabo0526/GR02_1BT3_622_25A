package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bloque_Casilleros", schema = "railway")
public class BloqueCasillero {
    @Id
    @Column(name = "id_bloque", nullable = false)
    private Integer id;

    @Column(name = "nro_filas", nullable = false)
    private Integer nroFilas;

    @Column(name = "nro_columnas", nullable = false)
    private Integer nroColumnas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroFilas() {
        return nroFilas;
    }

    public void setNroFilas(Integer nroFilas) {
        this.nroFilas = nroFilas;
    }

    public Integer getNroColumnas() {
        return nroColumnas;
    }

    public void setNroColumnas(Integer nroColumnas) {
        this.nroColumnas = nroColumnas;
    }

    @Override
    public String toString() {
        return "BloqueCasillero{" +
                "id=" + id +
                ", nroFilas=" + nroFilas +
                ", nroColumnas=" + nroColumnas +
                '}';
    }
}