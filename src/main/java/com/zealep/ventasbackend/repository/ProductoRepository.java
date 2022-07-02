package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {


    @Query(value = "select p from Producto  p where p.estado=?1")
    List<Producto> findAllActives(String estado);

    @Modifying
    @Query(value = "update Producto p set p.estado=?2 where p.idProducto=?1")
    void deleteLogic(Long id,String estado);

    @Modifying
    @Query(value= "update Producto p set p.stock=?1 where p.idProducto=?2")
    void updateStock(double amount,Long idProducto);

    @Query(value = "select p from Producto p where p.codigo=?1 and p.estado=?2")
    Producto findByCodigo(String codigo,String estado);

    @Query(value = "select p from Producto  p where p.estado=?1 and p.stock <= p.stockMinimo")
    List<Producto> findAllStockMinimos(String estado);

    @Query(value = "select p from Producto  p where p.estado=?1 and p.stock = 0 ")
    List<Producto> findNoStock(String estado);

    @Query(value = "select * from producto where fecha_vencimiento <= date_add(current_date(), interval + 3 month) and estado='A';",nativeQuery = true)
    List<Producto> findExpirateThreeMonths(String estado);

    @Query(value = "select p.* from producto p inner join (\n" +
            "select det.id_producto,sum(det.total)  from (select dv.* from detalle_venta dv \n" +
            "left join venta v on dv.id_venta = v.id_venta\n" +
            "where v.fecha >= date_add(current_date(), interval - 6 month)) as det group by det.id_producto order by 2 desc limit 10\n" +
            ") as top on p.id_producto= top.id_producto where p.estado ='A';",nativeQuery = true)
    List<Producto> findTopSalesSixMonths(String estado);




}
