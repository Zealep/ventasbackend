package com.zealep.ventasbackend.controller;


import com.zealep.ventasbackend.exception.ConflictException;
import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Producto;
import com.zealep.ventasbackend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!productoService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del producto");
        return new ResponseEntity<Producto>(productoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> bucarTodo() {
        return new ResponseEntity<List<Producto>>(productoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> grabar(@RequestBody Producto producto) {
        if(productoService.isExistCodigo(producto.getCodigo())){
            throw new ConflictException("El codigo de producto ya existe");
        }
        return new ResponseEntity<Producto>(productoService.save(producto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!productoService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del producto");
        productoService.delete(id);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {
        return new ResponseEntity<Producto>(productoService.save(producto), HttpStatus.OK);
    }

    @GetMapping(value = "/stock-minimums",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> obtenerProductosStockMinimos() {
        return new ResponseEntity<List<Producto>>(productoService.getStocksMinimum(), HttpStatus.OK);
    }

    @GetMapping(value = "/no-stocks",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> obtenerProductosSinStock() {
        return new ResponseEntity<List<Producto>>(productoService.getNoStock(), HttpStatus.OK);
    }

    @GetMapping(value = "/expirate-three-months",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> obtenerProductosExpirado3meses() {
        return new ResponseEntity<List<Producto>>(productoService.getExpirateThreeMonths(), HttpStatus.OK);
    }

    @GetMapping(value = "/top-six-months",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> obtenerProductosTopVentasSeisMeses() {
        return new ResponseEntity<List<Producto>>(productoService.getTopSalesSixMonths(), HttpStatus.OK);
    }
}
