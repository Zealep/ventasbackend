package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.dto.RequestLogin;
import com.zealep.ventasbackend.model.dto.ResponseLogin;
import com.zealep.ventasbackend.model.entity.Empleado;
import com.zealep.ventasbackend.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empleado> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!empleadoService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " del empleado");
        return new ResponseEntity<Empleado>(empleadoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Empleado>> bucarTodo() {
        return new ResponseEntity<List<Empleado>>(empleadoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empleado> grabar(@RequestBody Empleado empleado) {
        return new ResponseEntity<Empleado>(empleadoService.save(empleado), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!empleadoService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " del empleado");
        empleadoService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empleado> modificar(@RequestBody Empleado empleado) {
        return new ResponseEntity<Empleado>(empleadoService.save(empleado), HttpStatus.OK);
    }

    @PostMapping(value = "/ingresar")
    public ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin requestLogin) {

        Empleado user = empleadoService.findByUsername(requestLogin.getUsuario(),requestLogin.getClave());
        if(user!=null){
            return new ResponseEntity<ResponseLogin>(new ResponseLogin(user,"1"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ResponseLogin>(new ResponseLogin(null,"0"), HttpStatus.OK);
        }

    }

}
