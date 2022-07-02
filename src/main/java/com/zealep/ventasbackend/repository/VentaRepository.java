package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {

    @Query("select v from Venta v where v.estado=?1")
    List<Venta> findAllActives(String estado);

    @Query("select v from Venta v where v.estado=?1")
    List<Venta> findAllAnuladas(String estado);

    @Modifying
    @Query("update Venta v set v.estado=?2 where v.idVenta=?1")
    void deleteLogic(Long id,String estado);

    @Query(value = "select SUM(total) from venta where fecha=?1" ,nativeQuery = true)
    public Double findTotalDia(Date date);

    @Query(value = "select SUM(total) from venta where month(fecha) = month(current_date()) and year(fecha) = year(current_date()) and estado=?1" ,nativeQuery = true)
    public Double findTotalMes(String activo);

    @Query(value = "select max(v.codigo) from Venta v")
    public Integer obtenerUltimaVenta();
}
