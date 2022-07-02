package com.zealep.ventasbackend.repository.jdbc;

import com.zealep.ventasbackend.model.dto.ParametersReporteVentasDTO;
import com.zealep.ventasbackend.model.dto.ReporteDetallesVentasDTO;
import com.zealep.ventasbackend.model.dto.ReporteMesDTO;
import com.zealep.ventasbackend.model.dto.ReporteVentasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReporteVentasJdbcRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    private StringBuilder buildSqlVentas(ParametersReporteVentasDTO params){
        StringBuilder sql = new StringBuilder();
        sql.append("select concat(v.cliente,' - ' ,v.documento) as cliente,concat(e.nombres,' ',e.apellidos) as empleado,v.codigo_largo as codigo,v.fecha,v.sub_total,v.descuento,v.igv,v.total from venta v "
                +"left join cliente c on v.id_cliente = c.id_cliente "
                +"left join empleado e on v.id_empleado = e.id_empleado "
                +"where v.estado = 'A' ");

        if(params.getFechaFin()!=null && params.getFechaFin()!=null){
            sql.append(" and v.fecha >='"+params.getFechaInicio() + "' and v.fecha <='"+params.getFechaFin()+"'");
        }

        return sql;
    }

    private StringBuilder buildSqlDetallesVentas(ParametersReporteVentasDTO params){
        StringBuilder sql = new StringBuilder();
        sql.append("select concat(v.cliente,' - ' ,v.documento) as cliente,concat(e.nombres,' ',e.apellidos) as empleado,v.codigo_largo as codigo,v.fecha,ca.nombre as categoria,p.nombre as producto,dv.precio,dv.cantidad,dv.total from detalle_venta dv \n" +
                "left join producto p on p.id_producto = dv.id_producto\n" +
                "left join categoria ca on ca.id_categoria = p.id_categoria\n" +
                "left join venta v on v.id_venta = dv.id_venta \n" +
                "left join cliente c on v.id_cliente = c.id_cliente\n" +
                "left join empleado e on v.id_empleado = e.id_empleado\n" +
                "where v.estado = 'A' ");

        if(params.getFechaFin()!=null && params.getFechaFin()!=null){
            sql.append(" and v.fecha >='"+params.getFechaInicio() + "' and v.fecha <='"+params.getFechaFin()+"'");
        }

        return sql;
    }


    private static final class ReporteVentasMapper implements RowMapper<ReporteVentasDTO> {

        @Override
        public ReporteVentasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReporteVentasDTO t = new ReporteVentasDTO();
            t.setCliente(rs.getString("cliente"));
            t.setEmpleado(rs.getString("empleado"));
            t.setCodigo(rs.getString("codigo"));
            t.setFechaVenta(rs.getDate("fecha"));
            t.setDescuento(rs.getDouble("descuento"));
            t.setSubTotal(rs.getDouble("sub_total"));
            t.setIgv(rs.getDouble("igv"));
            t.setTotal(rs.getDouble("total"));
            return t;

        }
    }

    private static final class ReporteDetallesVentasMapper implements RowMapper<ReporteDetallesVentasDTO> {

        @Override
        public ReporteDetallesVentasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReporteDetallesVentasDTO t = new ReporteDetallesVentasDTO();
            t.setCliente(rs.getString("cliente"));
            t.setEmpleado(rs.getString("empleado"));
            t.setCodigo(rs.getString("codigo"));
            t.setFechaVenta(rs.getDate("fecha"));
            t.setCategoria(rs.getString("categoria"));
            t.setProducto(rs.getString("producto"));
            t.setPrecio(rs.getDouble("precio"));
            t.setCantidad(rs.getDouble("cantidad"));
            t.setTotal(rs.getDouble("total"));
            return t;

        }
    }

    private static final class ReporteMesMapper implements RowMapper<ReporteMesDTO> {

        @Override
        public ReporteMesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReporteMesDTO t = new ReporteMesDTO();
            t.setFecha(rs.getString("fecha"));
            t.setTotal(rs.getDouble("total"));
            return t;

        }
    }


    public List<ReporteVentasDTO> listVentas(ParametersReporteVentasDTO params){

        StringBuilder sql = buildSqlVentas(params);
        return (List<ReporteVentasDTO>) jdbcTemplate.query(sql.toString(),new ReporteVentasMapper());

    }

    public List<ReporteDetallesVentasDTO> listDetallesVentas(ParametersReporteVentasDTO params){

        StringBuilder sql = buildSqlDetallesVentas(params);
        return (List<ReporteDetallesVentasDTO>) jdbcTemplate.query(sql.toString(),new ReporteDetallesVentasMapper());

    }

    public List<ReporteMesDTO> listVentasByMes(){
        String sql = "select CASE \n" +
                "\t\tWHEN p.mes = 1 THEN CONCAT('ENERO','-',p.anho)\n" +
                "        WHEN p.mes =2 THEN CONCAT('FEBRERO','-',p.anho)\n" +
                "        WHEN p.mes =3 THEN CONCAT('MARZO','-',p.anho)\n" +
                "        WHEN p.mes =4 THEN CONCAT('ABRIL','-',p.anho)\n" +
                "        WHEN p.mes =5 THEN CONCAT('MAYO','-',p.anho)\n" +
                "        WHEN p.mes =6 THEN CONCAT('JUNIO','-',p.anho)\n" +
                "        WHEN p.mes =7 THEN CONCAT('JULIO','-',p.anho)\n" +
                "        WHEN p.mes =8 THEN CONCAT('AGOSTO','-',p.anho)\n" +
                "        WHEN p.mes =9 THEN CONCAT('SEPTIEMBRE','-',p.anho)\n" +
                "        WHEN p.mes =10 THEN CONCAT('OCTUBRE','-',p.anho)\n" +
                "        WHEN p.mes =11 THEN CONCAT('NOVIEMBRE','-',p.anho)\n" +
                "        WHEN p.mes =12 THEN CONCAT('DICIEMBRE','-',p.anho)\n" +
                "        END as fecha,p.total \n" +
                "        from (select month(fecha)as mes,year(fecha)as anho,sum(total) as total from venta\n" +
                "        where fecha >= date_add(current_date(), interval - 6 month) and estado='A'\n" +
                "\t\tgroup by month(fecha),year(fecha)) p";

        return (List<ReporteMesDTO>) jdbcTemplate.query(sql,new ReporteMesMapper());
    }


}
