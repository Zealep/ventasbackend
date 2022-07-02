package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    @Query(value = "select e from Empleado  e where e.estado=?1")
    List<Empleado> findAllActives(String estado);

    @Modifying
    @Query(value = "update Empleado e set e.estado=?2 where e.idEmpleado=?1")
    void deleteLogic(Long id,String estado);

    @Query(value = "select e from Empleado e where e.usuario=?1 and e.clave=?2 and e.estado=?3")
    Optional<Empleado> findByUsername(String username,String clave,String estado);

    @Query("select u from Empleado u where u.usuario=?1 and u.estado='A'")
    Optional<Empleado> findByUsername(String username);

    @Query("select u.idEmpleado from Empleado u where u.usuario=?1 and u.estado='A'")
    String findIdUsuarioByUsername(String usuario);

    List<Empleado> findByRol(String rol);
}
