package com.zealep.ventasbackend.controller;

import com.zealep.ventasbackend.model.dto.*;
import com.zealep.ventasbackend.repository.jdbc.ReporteComprasJdbcRepository;
import com.zealep.ventasbackend.repository.jdbc.ReporteVentasJdbcRepository;
import com.zealep.ventasbackend.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/reporte")
public class ReportesController {

    @Autowired
    ReporteVentasJdbcRepository reporteVentasJdbcRepository;

    @Autowired
    ReporteComprasJdbcRepository reporteComprasJdbcRepository;

    @Autowired
    ReportesService reportesService;

    @PostMapping(value = "/ventas")
    public ResponseEntity<List<ReporteVentasDTO>> bucarVentas(@RequestBody ParametersReporteVentasDTO parms) {
        return new ResponseEntity<List<ReporteVentasDTO>>(reporteVentasJdbcRepository.listVentas(parms), HttpStatus.OK);
    }

    @PostMapping(value = "/detalle-ventas")
    public ResponseEntity<List<ReporteDetallesVentasDTO>> bucarDetallesVentas(@RequestBody ParametersReporteVentasDTO parms) {
        return new ResponseEntity<List<ReporteDetallesVentasDTO>>(reporteVentasJdbcRepository.listDetallesVentas(parms), HttpStatus.OK);
    }

    @PostMapping(value = "/compras")
    public ResponseEntity<List<ReporteComprasDTO>> bucarCompras(@RequestBody ParametersReporteComprasDTO parms) {
        return new ResponseEntity<List<ReporteComprasDTO>>(reporteComprasJdbcRepository.listCompras(parms), HttpStatus.OK);
    }

    @PostMapping(value = "/detalle-compras")
    public ResponseEntity<List<ReporteDetallesComprasDTO>> bucarDetallesCompras(@RequestBody ParametersReporteComprasDTO parms) {
        return new ResponseEntity<List<ReporteDetallesComprasDTO>>(reporteComprasJdbcRepository.listDetallesCompras(parms), HttpStatus.OK);
    }

    @PostMapping(value = "/exportVentas")
    public ResponseEntity<InputStreamSource> exportarExcelVentas(@RequestBody ParametersReporteVentasDTO parms){
        try {
            List<ReporteVentasDTO> export = reporteVentasJdbcRepository.listVentas(parms);
            ByteArrayInputStream stream = reportesService.exportExcelVentas(export);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-ventas.xls");
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/exportDetalleVentas")
    public ResponseEntity<InputStreamSource> exportarExcelDetalleVentas(@RequestBody ParametersReporteVentasDTO parms){
        try {
            List<ReporteDetallesVentasDTO> export = reporteVentasJdbcRepository.listDetallesVentas(parms);
            ByteArrayInputStream stream = reportesService.exportExcelDetalleVentas(export);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-det-ventas.xls");
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/exportCompras")
    public ResponseEntity<InputStreamSource> exportarExcelCompras(@RequestBody ParametersReporteComprasDTO parms){
        try {
            List<ReporteComprasDTO> export = reporteComprasJdbcRepository.listCompras(parms);
            ByteArrayInputStream stream = reportesService.exportExcelCompras(export);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-compras.xls");
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/exportDetalleCompras")
    public ResponseEntity<InputStreamSource> exportarExcelDetalleCompras(@RequestBody ParametersReporteComprasDTO parms){
        try {
            List<ReporteDetallesComprasDTO> export = reporteComprasJdbcRepository.listDetallesCompras(parms);
            ByteArrayInputStream stream = reportesService.exportExcelDetalleCompras(export);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-detalle-compras.xls");
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/ventas-mes")
    public ResponseEntity<List<ReporteMesDTO>> bucarVentasMes() {
        return new ResponseEntity<List<ReporteMesDTO>>(reporteVentasJdbcRepository.listVentasByMes(), HttpStatus.OK);
    }

    @GetMapping(value = "/compras-mes")
    public ResponseEntity<List<ReporteMesDTO>> bucarComprasMes() {
        return new ResponseEntity<List<ReporteMesDTO>>(reporteComprasJdbcRepository.listComprasByMes(), HttpStatus.OK);
    }
}
