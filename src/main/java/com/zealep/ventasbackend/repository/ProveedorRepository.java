package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {

    @Query(value = "select p from Proveedor  p where p.estado=?1")
    List<Proveedor> findAllActives(String estado);

    @Modifying
    @Query(value = "update Proveedor p set p.estado=?2 where p.idProveedor=?1")
    void deleteLogic(Long id,String estado);
}
