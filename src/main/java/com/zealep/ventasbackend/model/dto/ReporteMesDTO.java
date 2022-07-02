package com.zealep.ventasbackend.model.dto;

public class ReporteMesDTO {

    private String fecha;
    private Double total;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
