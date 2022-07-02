package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Gasto;
import com.zealep.ventasbackend.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/gasto")
public class GastoController {

    @Autowired
    GastoService gastoService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gasto> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!gastoService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del gasto");
        return new ResponseEntity<Gasto>(gastoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Gasto>> bucarTodo() {
        return new ResponseEntity<List<Gasto>>(gastoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gasto> grabar(@RequestBody Gasto gasto) {
        return new ResponseEntity<Gasto>(gastoService.save(gasto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!gastoService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del gasto");
        gastoService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gasto> modificar(@RequestBody Gasto gasto) {
        return new ResponseEntity<Gasto>(gastoService.save(gasto), HttpStatus.OK);
    }

    @GetMapping(value = "/month")
    public ResponseEntity<Double> bucarGastosMes() {
        return new ResponseEntity<Double>(gastoService.getTotalByMes(), HttpStatus.OK);
    }

    @GetMapping(value = "/today")
    public ResponseEntity<Double> bucarGastosHoy() {
        return new ResponseEntity<Double>(gastoService.getTotalByDia(new Date()), HttpStatus.OK);
    }


}
