package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "Aportacion", schema = "railway")
public class Aportacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "nombre_plan", nullable = false)
    private String nombrePlan;

    @Column(name = "precio", nullable = false, precision = 5, scale = 2)
    private BigDecimal precio;

    @Column(name = "beneficios", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> beneficios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Map<String, Object> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(Map<String, Object> beneficios) {
        this.beneficios = beneficios;
    }

}