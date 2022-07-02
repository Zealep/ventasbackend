package com.zealep.ventasbackend.service.impl;

import com.zealep.ventasbackend.model.dto.*;
import com.zealep.ventasbackend.repository.jdbc.ReporteComprasJdbcRepository;
import com.zealep.ventasbackend.repository.jdbc.ReporteVentasJdbcRepository;
import com.zealep.ventasbackend.service.ReportesService;
import com.zealep.ventasbackend.util.ExcelUtil;
import com.zealep.ventasbackend.util.Utils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service("reporteVentaService")
public class ReportesServiceImpl implements ReportesService {

    @Autowired
    ReporteComprasJdbcRepository reporteComprasJdbcRepository;

    @Autowired
    ReporteVentasJdbcRepository reporteVentasJdbcRepository;

    @Override
    public ByteArrayInputStream exportExcelVentas(List<ReporteVentasDTO> reporteVentasDTOList) {
        try {
            String[] headers = {"Cliente","Empleado" ,"Codigo","Fecha Venta","Descuento","SubTotal","IGV","Total"};

            Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
            CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

            Sheet sheet = workbook.createSheet("Reporte Venta");
            sheet.setDefaultColumnWidth(20);

            Row row = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

            }

            int initRow = 1;
            for (ReporteVentasDTO p : reporteVentasDTOList) {
                row = sheet.createRow(initRow);
                row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));

                ExcelUtil.createStringCell(p.getCliente() + " - ",row,0,rowStyle);
                ExcelUtil.createStringCell(p.getEmpleado(),row,1,rowStyle);
                ExcelUtil.createStringCell(p.getCodigo(),row,2,rowStyle);
                ExcelUtil.createStringCell(Utils.convertDateToStringFormatddMMyyyy(p.getFechaVenta()),row,3,rowStyle);
                ExcelUtil.createDoubleCell(p.getDescuento(),row,4,rowStyle);
                ExcelUtil.createDoubleCell(p.getSubTotal(),row,5,rowStyle);
                ExcelUtil.createDoubleCell(p.getIgv(),row,6,rowStyle);
                ExcelUtil.createDoubleCell(p.getTotal(),row,7,rowStyle);

                row.setRowStyle(rowStyle);
                initRow++;
            }

            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        }catch(Exception ex){
            System.out.println(ex);
            return null;

        }

    }

    @Override
    public ByteArrayInputStream exportExcelDetalleVentas(List<ReporteDetallesVentasDTO> reporteVentasDTOList) {
        try {
            String[] headers = {"Cliente","Empleado" ,"Codigo","Fecha Venta","Categoria","Producto","Precio","Cantidad","Total"};

            Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
            CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

            Sheet sheet = workbook.createSheet("Reporte Detalle Ventas");
            sheet.setDefaultColumnWidth(20);

            Row row = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

            }

            int initRow = 1;
            for (ReporteDetallesVentasDTO p : reporteVentasDTOList) {
                row = sheet.createRow(initRow);
                row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));

                ExcelUtil.createStringCell(p.getCliente(),row,0,rowStyle);
                ExcelUtil.createStringCell(p.getEmpleado(),row,1,rowStyle);
                ExcelUtil.createStringCell(p.getCodigo(),row,2,rowStyle);
                ExcelUtil.createStringCell(Utils.convertDateToStringFormatddMMyyyy(p.getFechaVenta()),row,3,rowStyle);
                ExcelUtil.createStringCell(p.getCategoria(),row,4,rowStyle);
                ExcelUtil.createStringCell(p.getProducto(),row,5,rowStyle);
                ExcelUtil.createDoubleCell(p.getPrecio(),row,6,rowStyle);
                ExcelUtil.createDoubleCell(p.getCantidad(),row,7,rowStyle);
                ExcelUtil.createDoubleCell(p.getTotal(),row,8,rowStyle);

                row.setRowStyle(rowStyle);
                initRow++;
            }

            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        }catch(Exception ex){
            System.out.println(ex);
            return null;

        }
    }

    @Override
    public ByteArrayInputStream exportExcelCompras(List<ReporteComprasDTO> reporteComprasDTOList) {
        try {
            String[] headers = {"Tipo Documento","Proveedor","Empleado" ,"Codigo","Fecha Compra","SubTotal","IGV","Total"};

            Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
            CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

            Sheet sheet = workbook.createSheet("Reporte Compra");
            sheet.setDefaultColumnWidth(20);

            Row row = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

            }

            int initRow = 1;
            for (ReporteComprasDTO p : reporteComprasDTOList) {
                row = sheet.createRow(initRow);
                row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));

                ExcelUtil.createStringCell(p.getTipoDocumento(),row,0,rowStyle);
                ExcelUtil.createStringCell(p.getProveedor(),row,1,rowStyle);
                ExcelUtil.createStringCell(p.getEmpleado(),row,2,rowStyle);
                ExcelUtil.createStringCell(p.getCodigo(),row,3,rowStyle);
                ExcelUtil.createStringCell(Utils.convertDateToStringFormatddMMyyyy(p.getFechaVenta()),row,4,rowStyle);
                ExcelUtil.createDoubleCell(p.getSubTotal(),row,5,rowStyle);
                ExcelUtil.createDoubleCell(p.getIgv(),row,6,rowStyle);
                ExcelUtil.createDoubleCell(p.getTotal(),row,7,rowStyle);

                row.setRowStyle(rowStyle);
                initRow++;
            }

            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        }catch(Exception ex){
            System.out.println(ex);
            return null;

        }
    }

    @Override
    public ByteArrayInputStream exportExcelDetalleCompras(List<ReporteDetallesComprasDTO> reporteComprasDTOList) {
        try {
            String[] headers = {"Tipo Documento","Proveedor","Empleado" ,"Codigo","Fecha Compra","Categoria","Producto","Precio","Cantidad","Total"};

            Workbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            CellStyle headerStyle = ExcelUtil.headersStyle(workbook);
            CellStyle rowStyle = ExcelUtil.rowsStyle(workbook);

            Sheet sheet = workbook.createSheet("Reporte Detalle Compras");
            sheet.setDefaultColumnWidth(20);

            Row row = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

            }

            int initRow = 1;
            for (ReporteDetallesComprasDTO p : reporteComprasDTOList) {
                row = sheet.createRow(initRow);
                row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));

                ExcelUtil.createStringCell(p.getTipoDocumento(),row,0,rowStyle);
                ExcelUtil.createStringCell(p.getProveedor(),row,1,rowStyle);
                ExcelUtil.createStringCell(p.getEmpleado(),row,2,rowStyle);
                ExcelUtil.createStringCell(p.getCodigo(),row,3,rowStyle);
                ExcelUtil.createStringCell(Utils.convertDateToStringFormatddMMyyyy(p.getFechaCompra()),row,4,rowStyle);
                ExcelUtil.createStringCell(p.getCategoria(),row,5,rowStyle);
                ExcelUtil.createStringCell(p.getProducto(),row,6,rowStyle);
                ExcelUtil.createDoubleCell(p.getPrecio(),row,7,rowStyle);
                ExcelUtil.createDoubleCell(p.getCantidad(),row,8,rowStyle);
                ExcelUtil.createDoubleCell(p.getTotal(),row,9,rowStyle);

                row.setRowStyle(rowStyle);
                initRow++;
            }

            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        }catch(Exception ex){
            System.out.println(ex);
            return null;

        }
    }

    @Override
    public List<ReporteMesDTO> getVentasPorMes() {
        return reporteVentasJdbcRepository.listVentasByMes();
    }

    @Override
    public List<ReporteMesDTO> getComprasPorMes() {
        return reporteComprasJdbcRepository.listComprasByMes();
    }

}
