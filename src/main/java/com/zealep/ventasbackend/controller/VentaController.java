package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.exception.NotFoundException;
import com.zealep.ventasbackend.model.dto.ImprimirDTO;
import com.zealep.ventasbackend.model.dto.ResponseBoletaVenta;
import com.zealep.ventasbackend.model.entity.DetalleVenta;
import com.zealep.ventasbackend.model.entity.Venta;
import com.zealep.ventasbackend.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/venta")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> buscarPorId(@PathVariable(value = "id") Long id) {
        if (!ventaService.isExist(id))
            throw new NotFoundException("No existe el id " + id + " de la venta");
        return new ResponseEntity<Venta>(ventaService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Venta>> bucarTodo() {
        return new ResponseEntity<List<Venta>>(ventaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/find/details/{id}")
    public ResponseEntity<List<DetalleVenta>> bucarDetalleVenta(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<List<DetalleVenta>>(ventaService.findDetails(id), HttpStatus.OK);
    }

    @GetMapping(value = "/anuladas")
    public ResponseEntity<List<Venta>> bucarAnuladas() {
        return new ResponseEntity<List<Venta>>(ventaService.findAnuladas(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> grabar(@RequestBody Venta venta) {
        return new ResponseEntity<Venta>(ventaService.save(venta), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void borrar(@PathVariable(value = "id") Long id) {
        if (!ventaService.isExist(id))
            throw new NotFoundException("No se encontro el id " + id + " de la venta");
        ventaService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> modificar(@RequestBody Venta venta) {
        return new ResponseEntity<Venta>(ventaService.save(venta), HttpStatus.OK);
    }

    @GetMapping(value = "/month")
    public ResponseEntity<Double> bucarVentasMes() {
        return new ResponseEntity<Double>(ventaService.getTotalByMes(), HttpStatus.OK);
    }

    @GetMapping(value = "/today")
    public ResponseEntity<Double> bucarVentasHoy() {
        return new ResponseEntity<Double>(ventaService.getTotalByDia(new Date()), HttpStatus.OK);
    }

    @GetMapping(value = "/lastCodigo")
    public ResponseEntity<Integer> obtenerUltimoCodigoVenta() {
        return new ResponseEntity<Integer>(ventaService.obtenerUltimaVenta(), HttpStatus.OK);
    }

    @GetMapping(value = "/formatCodigoVenta")
    public ResponseEntity<ResponseBoletaVenta> obtenerUltimoCodigoVenta(@RequestParam(value = "codigo") String codigo) {


        return new ResponseEntity<>(new ResponseBoletaVenta(null,ventaService.formatCodigoVenta(codigo)), HttpStatus.OK);
    }

    @PostMapping(value = "/imprimir", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarContrato(@RequestBody ImprimirDTO imprimirDTO){
        byte[] data = null;
        try {
            data = ventaService.imprimirTicket(imprimirDTO);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }



}
