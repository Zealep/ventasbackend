package com.zealep.ventasbackend.service.impl;

import com.zealep.ventasbackend.model.entity.Compra;
import com.zealep.ventasbackend.model.entity.DetalleCompra;
import com.zealep.ventasbackend.repository.CompraRepository;
import com.zealep.ventasbackend.repository.DetalleCompraRepository;
import com.zealep.ventasbackend.service.CompraService;
import com.zealep.ventasbackend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("compraService")
public class CompraServiceImpl implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProductoServiceImpl productoService;

    @Autowired
    DetalleCompraRepository detalleCompraRepository;


    @Transactional(readOnly = true)
    @Override
    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Compra> findAll() {
        return compraRepository.findAllActives(Constants.ACTIVE_STATE);
    }

    @Transactional
    @Override
    public Compra save(Compra c) {
        c.setEstado(Constants.ACTIVE_STATE);
        c.getDetallesCompra().forEach(x ->{
            x.setCompra(c);
            productoService.incrementStock(x.getCantidad(),x.getProducto().getIdProducto());
        });
        return compraRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        compraRepository.deleteLogic(id,Constants.INACTIVE_STATE);
    }


    @Override
    public boolean isExist(Long id) {
        return findById(id)!=null;
    }

    @Override
    public List<DetalleCompra> getDetailsLastWeek() {
        /*
        LocalDate today = LocalDate.now();
        LocalDate pastWeek= today.minusWeeks(1);
        return detalleCompraRepository.findDetailsByDates(pastWeek,today,Constants.ACTIVE_STATE);
        */
        return null;
    }

    @Override
    public List<DetalleCompra> getDetailsLastMonth() {
        /*
        LocalDate today = LocalDate.now();
        LocalDate pastMonth= today.minusMonths(1);
        return detalleCompraRepository.findDetailsByDates(pastMonth,today,Constants.ACTIVE_STATE);

         */
        return null;
    }

    @Override
    public Double getTotalByMes() {
        return compraRepository.findTotalMes(Constants.ACTIVE_STATE);
    }

    @Override
    public Double getTotalByDia(Date date) {
        return compraRepository.findTotalDia(date);
    }
}
