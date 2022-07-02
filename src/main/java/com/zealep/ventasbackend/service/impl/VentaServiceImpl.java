package com.zealep.ventasbackend.service.impl;

import com.zealep.ventasbackend.model.dto.ImprimirDTO;
import com.zealep.ventasbackend.model.entity.DetalleVenta;
import com.zealep.ventasbackend.model.entity.Venta;
import com.zealep.ventasbackend.repository.DetalleVentaRepository;
import com.zealep.ventasbackend.repository.VentaRepository;
import com.zealep.ventasbackend.service.VentaService;
import com.zealep.ventasbackend.util.Constants;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ventaService")
public class VentaServiceImpl implements VentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    ProductoServiceImpl productoService;

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Transactional(readOnly = true)
    @Override
    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAllActives(Constants.ACTIVE_STATE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Venta> findAnuladas() {
        return ventaRepository.findAllAnuladas(Constants.INACTIVE_STATE);
    }

    @Transactional
    @Override
    public Venta save(Venta v) {
        Integer codigo = this.obtenerUltimaVenta();
        v.setCodigo(String.valueOf(codigo));
        v.setCodigoLargo(formatCodigoVenta(String.valueOf(codigo)));
        v.setEstado(Constants.ACTIVE_STATE);
        v.getDetallesVenta().forEach(x -> {
            x.setVenta(v);
            productoService.decrementStock(x.getCantidad(),x.getProducto().getIdProducto());
        });
        return ventaRepository.save(v);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Venta v = findById(id);
        if(v!=null){
            ventaRepository.deleteLogic(id,Constants.INACTIVE_STATE);
            v.getDetallesVenta().forEach(x->{
                productoService.incrementStock(x.getCantidad(),x.getProducto().getIdProducto());
                detalleVentaRepository.deleteNative(x.getIdDetalleVenta());
            });

        }

    }
    
    
    @Override
    public boolean isExist(Long id) {
        return findById(id)!=null;
    }

    @Override
    public List<DetalleVenta> findDetails(Long id) {
        return detalleVentaRepository.findDetailsByVenta(id);
    }

    @Override
    public Double getTotalByMes() {
        return ventaRepository.findTotalMes(Constants.ACTIVE_STATE);
    }

    @Override
    public Double getTotalByDia(Date date) {
        return ventaRepository.findTotalDia(date);
    }

    @Override
    public Integer obtenerUltimaVenta() {
        Integer last = ventaRepository.obtenerUltimaVenta();

        if(last == null)
            return 1 ;
         else return last + 1;
    }

    @Override
    public String formatCodigoVenta(String codigo) {
        if (codigo == null || codigo.equals("") || codigo.equals("null")){
            codigo = String.valueOf(this.obtenerUltimaVenta());
        }
        return Constants.PREFIX_VENTA + numeroCompletar(codigo,8);
    }

    @Override
    public byte[] imprimirTicket(ImprimirDTO imprimirDTO) {

        Map<String,Object> p = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dtf = new SimpleDateFormat("HH:mm");
        p.put("numero", imprimirDTO.getNumero());
        p.put("fecha",df.format(date));
        p.put("hora", dtf.format(date));
        p.put("cliente",imprimirDTO.getCliente());
        p.put("subtotal",imprimirDTO.getSubtotal());
        p.put("igv", imprimirDTO.getIgv());
        p.put("total", imprimirDTO.getTotal());

        JasperReport report;
        JasperPrint print;

        try {

            URL url = this.getClass().getResource("/reports/comprobante_pago.jasper");
            report = (JasperReport) JRLoader.loadObject(url);
            print = JasperFillManager.fillReport(report, p, new JRBeanCollectionDataSource(imprimirDTO.getProductoList()));
            return JasperExportManager.exportReportToPdf(print);


        } catch (JRException e) {

            e.printStackTrace();
            return null;
        }
    }

    public String numeroCompletar(String num, int length) {
        return Right("000000000000000000000000" + num, length);
    }

    public String Right(String text, int length) {
        return text.substring(text.length() - length, text.length());
    }
}
