package com.zealep.ventasbackend.service;

import com.zealep.ventasbackend.model.entity.Empleado;

import java.util.List;

public interface EmpleadoService {

    Empleado findById(Long id);

    List<Empleado> findAll();

    Empleado save(Empleado e);

    void delete(Long id);

    boolean isExist(Long id);

    List<Empleado> findByRol(String rol);

    Empleado findByUsername(String username,String clave);
}
