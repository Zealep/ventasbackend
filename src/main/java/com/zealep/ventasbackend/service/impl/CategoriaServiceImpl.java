package com.zealep.ventasbackend.service.impl;


import com.zealep.ventasbackend.model.entity.Categoria;
import com.zealep.ventasbackend.repository.CategoriaRepository;
import com.zealep.ventasbackend.service.CategoriaService;
import com.zealep.ventasbackend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAllActives(Constants.ACTIVE_STATE);
    }


    @Override
    @Transactional
    public Categoria save(Categoria c) {
        c.setEstado(Constants.ACTIVE_STATE);
        return categoriaRepository.save(c);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriaRepository.deleteLogic(id, Constants.INACTIVE_STATE);
    }

    @Override
    public boolean isExist(Long id) {
        return findById(id) != null;
    }
}
