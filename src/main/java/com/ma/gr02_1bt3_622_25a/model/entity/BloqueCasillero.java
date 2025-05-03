package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "Bloque_Casilleros", schema = "railway")
@DynamicInsert
@NamedQuery(name = "BloqueCasillero.findLast", query = "SELECT b FROM BloqueCasillero b WHERE b.id = (SELECT MAX(b2.id) FROM BloqueCasillero b2)")
@NamedQuery(name = "BloqueCasillero.countAll", query = "SELECT COUNT(b) FROM BloqueCasillero b")
public class BloqueCasillero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloque", nullable = true)
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