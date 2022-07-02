package com.zealep.ventasbackend.repository;

import com.zealep.ventasbackend.model.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento,Long> {


    @Query(value = "select t from TipoDocumento t where t.estado=?1")
    List<TipoDocumento> findAllActives(String estado);

    @Modifying
    @Query(value = "update TipoDocumento t set t.estado=?2 where t.idTipoDocumento=?1")
    void deleteLogic(Long id,String estado);
}

