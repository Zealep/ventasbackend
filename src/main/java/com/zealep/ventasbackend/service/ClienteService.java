package com.zealep.ventasbackend.service;

import com.zealep.ventasbackend.model.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findAll();

    Cliente save(Cliente c);

    void delete(Long id);

    boolean isExist(Long id);
}
