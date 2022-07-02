package com.zealep.ventasbackend.model.dto;

public class ResponseBoletaVenta {

    private String codigo;
    private String formateo;

    public ResponseBoletaVenta(String codigo, String formateo) {
        this.codigo = codigo;
        this.formateo = formateo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFormateo() {
        return formateo;
    }

    public void setFormateo(String formateo) {
        this.formateo = formateo;
    }
}
