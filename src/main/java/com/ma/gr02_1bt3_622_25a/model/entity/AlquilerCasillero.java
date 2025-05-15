package com.ma.gr02_1bt3_622_25a.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "Alquiler_Casillero", schema = "railway")
@DynamicInsert // Permite que se inserte un registro sin tener que especificar todos los campos, para que se genere un valor por defecto
@NamedQuery(name = "AlquilerCasillero.updateState", query = "UPDATE AlquilerCasillero a SET a.estadoAlquiler = ?1 WHERE a.id = ?2")
@NamedQuery(name = "AlquilerCasillero.exchangeLocker", query = "UPDATE AlquilerCasillero a SET a.idCasillero = ?1 WHERE a.id = ?2")
@NamedQuery(name = "AlquilerCasillero.findLockerByUser", query = "SELECT a FROM AlquilerCasillero a JOIN FETCH a.idCasillero JOIN FETCH a.idCasillero.idBloque WHERE a.idUsuario = ?1 AND a.estadoAlquiler = ?2")
public class AlquilerCasillero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_casillero", nullable = false)
    private Casillero idCasillero;

    @ColumnDefault("(now())")
    @Column(name = "fecha_inicio", nullable = true)
    private Instant fechaInicio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Instant fechaVencimiento;

    @Column(name = "detalle_alquiler", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> detalleAlquiler;

    @ColumnDefault("'Activo'")
    @Lob
    @Column(name = "estado_alquiler", nullable = true)
    private String estadoAlquiler;

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

    public Casillero getIdCasillero() {
        return idCasillero;
    }

    public void setIdCasillero(Casillero idCasillero) {
        this.idCasillero = idCasillero;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Instant fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Map<String, Object> getDetalleAlquiler() {
        return detalleAlquiler;
    }

    public void setDetalleAlquiler(Map<String, Object> detalleAlquiler) {
        this.detalleAlquiler = detalleAlquiler;
    }

    public String getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(String estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }

    @Override
    public String toString() {
        return "AlquilerCasillero{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idCasillero=" + idCasillero +
                ", fechaInicio=" + fechaInicio +
                ", fechaVencimiento=" + fechaVencimiento +
                ", detalleAlquiler=" + detalleAlquiler +
                ", estadoAlquiler='" + estadoAlquiler + '\'' +
                '}';
    }
}