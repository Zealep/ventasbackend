package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {

    @Query("select v from DetalleCompra v where v.compra.idCompra=?1")
    List<DetalleCompra> findDetailsByCompra(Long idCompra);

    @Query("select v from DetalleCompra v where v.compra.fecha>=?1 and v.compra.fecha <=?2 and v.compra.estado=?3")
    List<DetalleCompra> findDetailsByDates(Date fechaInicio, Date fechaFin, String estado);

}
