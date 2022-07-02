package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra,Long> {

    @Query("select c from Compra c where c.estado=?1")
    List<Compra> findAllActives(String estado);

    @Modifying
    @Query("update Compra c set c.estado=?2 where c.idCompra=?1")
    void deleteLogic(Long id,String estado);

    @Query(value = "select SUM(total) from compra where fecha=?1" ,nativeQuery = true)
    public Double findTotalDia(Date date);

    @Query(value = "select SUM(total) from compra where month(fecha) = month(current_date()) and year(fecha) = year(current_date()) and estado=?1" ,nativeQuery = true)
    public Double findTotalMes(String activo);


}
