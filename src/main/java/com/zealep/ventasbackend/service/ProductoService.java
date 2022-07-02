package com.zealep.ventasbackend.service;


import com.zealep.ventasbackend.model.entity.Producto;

import java.util.List;

public interface ProductoService {

    Producto findById(Long id);

    List<Producto> findAll();

    Producto save(Producto p);

    void delete(Long id);

    boolean isExist(Long id);

    void incrementStock(double stock,Long idProducto);

    void decrementStock(double stock,Long idProducto);

    boolean isExistCodigo(String codigo);

    List<Producto> getStocksMinimum();

    List<Producto> getNoStock();

    List<Producto> getExpirateThreeMonths();

    List<Producto> getTopSalesSixMonths();
}
