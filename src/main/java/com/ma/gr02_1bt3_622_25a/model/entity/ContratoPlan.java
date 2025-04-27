package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "Contrato_Plan", schema = "railway")
public class ContratoPlan {
    @Id
    @Column(name = "id_contrato", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan", nullable = false)
    private Aportacion idPlan;

    @Column(name = "detalle_contrato")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> detalleContrato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Aportacion getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Aportacion idPlan) {
        this.idPlan = idPlan;
    }

    public Map<String, Object> getDetalleContrato() {
        return detalleContrato;
    }

    public void setDetalleContrato(Map<String, Object> detalleContrato) {
        this.detalleContrato = detalleContrato;
    }

}