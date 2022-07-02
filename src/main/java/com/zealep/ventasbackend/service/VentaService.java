package com.zealep.ventasbackend.service;

import com.zealep.ventasbackend.model.dto.ImprimirDTO;
import com.zealep.ventasbackend.model.entity.DetalleVenta;
import com.zealep.ventasbackend.model.entity.Venta;

import java.util.Date;
import java.util.List;

public interface VentaService {


    Venta findById(Long id);

    List<Venta> findAll();

    List<Venta> findAnuladas();

    Venta save(Venta v);

    void delete(Long id);

    boolean isExist(Long id);

    List<DetalleVenta> findDetails(Long id);

    Double getTotalByMes();

    Double getTotalByDia(Date date);

    Integer obtenerUltimaVenta();

    String formatCodigoVenta(String codigo);

    byte[] imprimirTicket(ImprimirDTO imprimirDTO);

}
