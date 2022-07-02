package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {

    @Query("select v from DetalleVenta v where v.venta.idVenta=?1")
    List<DetalleVenta> findDetailsByVenta(Long idVenta);

    @Modifying
    @Query(value = "delete from detalle_venta where id_detalle_venta = ?1",nativeQuery = true)
    void deleteNative(Long idDetalle);

}
