package com.zealep.ventasbackend.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "gasto")
public class Gasto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private Long idGasto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "costo")
    private double costo;

    @Column(name = "fecha_gasto")
    private Date fechaGasto;

    @Column(name = "estado")
    private String estado;

    public Long getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
