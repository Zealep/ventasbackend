package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query(value = "select c from Cliente  c where c.estado=?1")
    List<Cliente> findAllActives(String estado);

    @Modifying
    @Query(value = "update Cliente c set c.estado=?2 where c.idCliente=?1")
    void deleteLogic(Long id,String estado);
}
