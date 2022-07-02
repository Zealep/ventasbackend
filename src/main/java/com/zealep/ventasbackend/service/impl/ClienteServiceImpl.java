package com.zealep.ventasbackend.service.impl;

import com.zealep.ventasbackend.model.entity.Cliente;
import com.zealep.ventasbackend.repository.ClienteRepository;
import com.zealep.ventasbackend.service.ClienteService;
import com.zealep.ventasbackend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAllActives(Constants.ACTIVE_STATE);
    }

    @Override
    @Transactional
    public Cliente save(Cliente c) {
        c.setEstado(Constants.ACTIVE_STATE);
        return clienteRepository.save(c);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteLogic(id,Constants.INACTIVE_STATE);
    }

    @Override
    public boolean isExist(Long id) {
        return findById(id)!=null;
    }
}
