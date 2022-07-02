package com.zealep.ventasbackend.service;


import com.zealep.ventasbackend.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria findById(Long id);

    List<Categoria> findAll();

    Categoria save(Categoria c);

    void delete(Long id);

    boolean isExist(Long id);


}
