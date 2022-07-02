package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Cliente;
import com.zealep.ventasbackend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!clienteService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del Cliente");
        return new ResponseEntity<Cliente>(clienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Cliente>> bucarTodo() {
        return new ResponseEntity<List<Cliente>>(clienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> grabar(@RequestBody Cliente Cliente) {
        return new ResponseEntity<Cliente>(clienteService.save(Cliente), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!clienteService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del Cliente");
        clienteService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> modificar(@RequestBody Cliente Cliente) {
        return new ResponseEntity<Cliente>(clienteService.save(Cliente), HttpStatus.OK);
    }

}
