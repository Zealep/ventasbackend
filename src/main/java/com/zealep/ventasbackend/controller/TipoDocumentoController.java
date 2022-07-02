package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.entity.TipoDocumento;
import com.zealep.ventasbackend.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tipoDocumento")
public class TipoDocumentoController {

    @Autowired
    TipoDocumentoService tipoDocumentoService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoDocumento> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!tipoDocumentoService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del tipo documento");
        return new ResponseEntity<TipoDocumento>(tipoDocumentoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<TipoDocumento>> bucarTodo() {
        return new ResponseEntity<List<TipoDocumento>>(tipoDocumentoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoDocumento> grabar(@RequestBody TipoDocumento tipoDocumento) {
        return new ResponseEntity<TipoDocumento>(tipoDocumentoService.save(tipoDocumento), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!tipoDocumentoService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del tipo documento");
        tipoDocumentoService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoDocumento> modificar(@RequestBody TipoDocumento tipoDocumento) {
        return new ResponseEntity<TipoDocumento>(tipoDocumentoService.save(tipoDocumento), HttpStatus.OK);
    }


}
