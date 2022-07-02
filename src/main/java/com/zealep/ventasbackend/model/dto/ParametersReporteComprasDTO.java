package com.zealep.ventasbackend.model.dto;

import java.util.Date;

public class ParametersReporteComprasDTO {

    Date fechaInicio;
    Date fechaFin;

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
}
