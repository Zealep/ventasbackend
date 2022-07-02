package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Compra;
import com.zealep.ventasbackend.model.entity.DetalleCompra;
import com.zealep.ventasbackend.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compra> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!compraService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " de la compra");
        return new ResponseEntity<Compra>(compraService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Compra>> bucarTodo() {
        return new ResponseEntity<List<Compra>>(compraService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/last-week")
    public ResponseEntity<List<DetalleCompra>> bucarComprasUltimaSemana() {
        return new ResponseEntity<List<DetalleCompra>>(compraService.getDetailsLastWeek(), HttpStatus.OK);
    }

    @GetMapping(value = "/last-month")
    public ResponseEntity<List<DetalleCompra>> bucarComprasUltimoMes() {
        return new ResponseEntity<List<DetalleCompra>>(compraService.getDetailsLastMonth(), HttpStatus.OK);
    }

    @GetMapping(value = "/month")
    public ResponseEntity<Double> bucarComprasMes() {
        return new ResponseEntity<Double>(compraService.getTotalByMes(), HttpStatus.OK);
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compra> grabar(@RequestBody Compra compra) {
        return new ResponseEntity<Compra>(compraService.save(compra), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!compraService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " de la compra");
        compraService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compra> modificar(@RequestBody Compra compra) {
        return new ResponseEntity<Compra>(compraService.save(compra), HttpStatus.OK);
    }

    @GetMapping(value = "/today")
    public ResponseEntity<Double> bucarComprasHoy() {
        return new ResponseEntity<Double>(compraService.getTotalByDia(new Date()), HttpStatus.OK);
    }


}
