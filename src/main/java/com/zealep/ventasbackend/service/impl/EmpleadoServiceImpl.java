package com.zealep.ventasbackend.service.impl;

import com.zealep.ventasbackend.model.entity.Empleado;
import com.zealep.ventasbackend.repository.EmpleadoRepository;
import com.zealep.ventasbackend.service.EmpleadoService;
import com.zealep.ventasbackend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return empleadoRepository.findAllActives(Constants.ACTIVE_STATE);
    }

    @Override
    @Transactional
    public Empleado save(Empleado e) {
        e.setEstado(Constants.ACTIVE_STATE);
        return empleadoRepository.save(e);
    }

    @Override
    public void delete(Long id) {
        empleadoRepository.deleteLogic(id,Constants.INACTIVE_STATE);
    }

    @Override
    public boolean isExist(Long id) {
        return findById(id)!=null;
    }

    @Override
    public List<Empleado> findByRol(String rol) {
        return empleadoRepository.findByRol(rol);
    }

    @Override
    public Empleado findByUsername(String username,String clave) {
        return empleadoRepository.findByUsername(username,clave,Constants.ACTIVE_STATE)
                .orElse(null);
    }


}
