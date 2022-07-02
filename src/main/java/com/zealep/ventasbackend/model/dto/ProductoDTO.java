package com.zealep.ventasbackend.model.dto;

public class ProductoDTO {

    private String prod;
    private Double cant;
    private Double precio;
    private Double total;

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public Double getCant() {
        return cant;
    }

    public void setCant(Double cant) {
        this.cant = cant;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
