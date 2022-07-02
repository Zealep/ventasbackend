package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Proveedor;
import com.zealep.ventasbackend.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!proveedorService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del proveedor");
        return new ResponseEntity<Proveedor>(proveedorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Proveedor>> bucarTodo() {
        return new ResponseEntity<List<Proveedor>>(proveedorService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> grabar(@RequestBody Proveedor proveedor) {
        return new ResponseEntity<Proveedor>(proveedorService.save(proveedor), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!proveedorService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del proveedor");
        proveedorService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> modificar(@RequestBody Proveedor proveedor) {
        return new ResponseEntity<Proveedor>(proveedorService.save(proveedor), HttpStatus.OK);
    }

}
