package com.zealep.ventasbackend.service;

import com.zealep.ventasbackend.model.entity.Compra;
import com.zealep.ventasbackend.model.entity.DetalleCompra;

import java.util.Date;
import java.util.List;

public interface CompraService {

    Compra findById(Long id);

    List<Compra> findAll();

    Compra save(Compra c);

    void delete(Long id);

    boolean isExist(Long id);

    List<DetalleCompra> getDetailsLastWeek();

    List<DetalleCompra> getDetailsLastMonth();

    Double getTotalByMes();

    Double getTotalByDia(Date date);

}
