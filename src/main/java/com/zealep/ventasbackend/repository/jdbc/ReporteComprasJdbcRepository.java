package com.zealep.ventasbackend.repository.jdbc;

import com.zealep.ventasbackend.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReporteComprasJdbcRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    private StringBuilder buildSqlCompras(ParametersReporteComprasDTO params){
        StringBuilder sql = new StringBuilder();
        sql.append("select td.nombre as tipo_documento,c.nombre as proveedor,concat(e.nombres,' ',e.apellidos) as empleado,v.codigo,v.fecha,v.sub_total,v.igv,v.total from compra v "
                +"left join tipo_documento td on v.id_tipo_documento = td.id_tipo_documento "
                +"left join proveedor c on v.id_proveedor = c.id_proveedor "
                +"left join empleado e on v.id_empleado = e.id_empleado "
                +"where v.estado = 'A' ");

        if(params.getFechaFin()!=null && params.getFechaFin()!=null){
            sql.append(" and v.fecha >='"+params.getFechaInicio() + "' and v.fecha <='"+params.getFechaFin()+"'");
        }

        return sql;
    }


    private StringBuilder buildSqlDetallesCompras(ParametersReporteComprasDTO params){
        StringBuilder sql = new StringBuilder();
        sql.append("select td.nombre as tipo_documento,c.nombre as proveedor,concat(e.nombres,' ',e.apellidos) as empleado,v.codigo,v.fecha,ca.nombre as categoria,p.nombre as producto,dv.precio_compra as precio,dv.cantidad,dv.total from detalle_compra dv \n" +
                "left join producto p on p.id_producto = dv.id_producto\n" +
                "left join categoria ca on ca.id_categoria = p.id_categoria\n" +
                "left join compra v on v.id_compra = dv.id_compra \n" +
                "left join tipo_documento td on v.id_tipo_documento = td.id_tipo_documento\n" +
                "left join proveedor c on v.id_proveedor = c.id_proveedor\n" +
                "left join empleado e on v.id_empleado = e.id_empleado\n" +
                "where v.estado = 'A' ");

        if(params.getFechaFin()!=null && params.getFechaFin()!=null){
            sql.append(" and v.fecha >='"+params.getFechaInicio() + "' and v.fecha <='"+params.getFechaFin()+"'");
        }

        return sql;
    }


    private static final class ReporteComprasMapper implements RowMapper<ReporteComprasDTO> {

        @Override
        public ReporteComprasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReporteComprasDTO t = new ReporteComprasDTO();
            t.setTipoDocumento(rs.getString("tipo_documento"));
            t.setProveedor(rs.getString("proveedor"));
            t.setEmpleado(rs.getString("empleado"));
            t.setCodigo(rs.getString("codigo"));
            t.setFechaVenta(rs.getDate("fecha"));
            t.setSubTotal(rs.getDouble("sub_total"));
            t.setIgv(rs.getDouble("igv"));
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

    private static final class ReporteDetallesComprasMapper implements RowMapper<ReporteDetallesComprasDTO> {

        @Override
        public ReporteDetallesComprasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReporteDetallesComprasDTO t = new ReporteDetallesComprasDTO();
            t.setTipoDocumento(rs.getString("tipo_documento"));
            t.setProveedor(rs.getString("proveedor"));
            t.setEmpleado(rs.getString("empleado"));
            t.setCodigo(rs.getString("codigo"));
            t.setFechaCompra(rs.getDate("fecha"));
            t.setCategoria(rs.getString("categoria"));
            t.setProducto(rs.getString("producto"));
            t.setPrecio(rs.getDouble("precio"));
            t.setCantidad(rs.getDouble("cantidad"));
            t.setTotal(rs.getDouble("total"));
            return t;

        }
    }

    public List<ReporteComprasDTO> listCompras(ParametersReporteComprasDTO params){

        StringBuilder sql = buildSqlCompras(params);
        return (List<ReporteComprasDTO>) jdbcTemplate.query(sql.toString(),new ReporteComprasMapper());

    }

    public List<ReporteDetallesComprasDTO> listDetallesCompras(ParametersReporteComprasDTO params){

        StringBuilder sql = buildSqlDetallesCompras(params);
        return (List<ReporteDetallesComprasDTO>) jdbcTemplate.query(sql.toString(),new ReporteDetallesComprasMapper());

    }

    public List<ReporteMesDTO> listComprasByMes(){
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
                "        from (select month(fecha)as mes,year(fecha)as anho,sum(total) as total from compra\n" +
                "        where fecha >= date_add(current_date(), interval - 6 month) and estado='A'\n" +
                "\t\tgroup by month(fecha),year(fecha)) p";

        return (List<ReporteMesDTO>) jdbcTemplate.query(sql,new ReporteMesMapper());
    }
}
