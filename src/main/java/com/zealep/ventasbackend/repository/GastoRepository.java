package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface GastoRepository extends JpaRepository<Gasto,Long> {


    @Query(value = "select g from Gasto  g where g.estado=?1")
    List<Gasto> findAllActives(String estado);

    @Modifying
    @Query(value = "update Gasto g set g.estado=?2 where g.idGasto=?1")
    void deleteLogic(Long id,String estado);

    @Query(value = "select SUM(costo) from gasto where fecha_gasto=?1 and estado=?2" ,nativeQuery = true)
    public Double findTotalDia(Date date, String activo);

    @Query(value = "select SUM(costo) from gasto where month(fecha_gasto) = month(current_date()) and year(fecha_gasto) = year(current_date()) and estado=?1" ,nativeQuery = true)
    public Double findTotalMes(String activo);
}
