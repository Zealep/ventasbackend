package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.Categoria;
import com.zealep.ventasbackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!categoriaService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " de la categoria");
        return new ResponseEntity<Categoria>(categoriaService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Categoria>> bucarTodo() {
        return new ResponseEntity<List<Categoria>>(categoriaService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> grabar(@RequestBody Categoria categoria) {
        return new ResponseEntity<Categoria>(categoriaService.save(categoria), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!categoriaService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " de la categoria");
        categoriaService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> modificar(@RequestBody Categoria categoria) {
        return new ResponseEntity<Categoria>(categoriaService.save(categoria), HttpStatus.OK);
    }


}
