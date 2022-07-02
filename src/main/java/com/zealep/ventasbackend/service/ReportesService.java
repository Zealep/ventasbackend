package com.zealep.ventasbackend.service;

import com.zealep.ventasbackend.model.dto.*;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportesService {

    ByteArrayInputStream exportExcelVentas(List<ReporteVentasDTO> reporteVentasDTOList);
    ByteArrayInputStream exportExcelDetalleVentas(List<ReporteDetallesVentasDTO> reporteVentasDTOList);
    ByteArrayInputStream exportExcelCompras(List<ReporteComprasDTO> reporteComprasDTOList);
    ByteArrayInputStream exportExcelDetalleCompras(List<ReporteDetallesComprasDTO> reporteComprasDTOList);
    List<ReporteMesDTO> getVentasPorMes();
    List<ReporteMesDTO> getComprasPorMes();


}
