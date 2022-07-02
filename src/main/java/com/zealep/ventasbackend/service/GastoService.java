package com.zealep.ventasbackend.service;


import com.zealep.ventasbackend.model.entity.Gasto;

import java.util.Date;
import java.util.List;

public interface GastoService {

    Gasto findById(Long id);

    List<Gasto> findAll();

    Gasto save(Gasto g);

    void delete(Long id);

    boolean isExist(Long id);

    Double getTotalByMes();

    Double getTotalByDia(Date date);


}
