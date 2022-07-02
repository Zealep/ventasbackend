package com.zealep.ventasbackend.model.dto;

import java.util.List;

public class ImprimirDTO {

    String numero;
    String fecha;
    String hora;
    String cliente;
    Double subtotal;
    Double igv;
    Double total;
    List<ProductoDTO> productoList;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }
}
