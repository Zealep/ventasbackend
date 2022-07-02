package com.zealep.ventasbackend.service;


import com.zealep.ventasbackend.model.entity.TipoDocumento;

import java.util.List;

public interface TipoDocumentoService {

    TipoDocumento findById(Long id);

    List<TipoDocumento> findAll();

    TipoDocumento save(TipoDocumento t);

    void delete(Long id);

    boolean isExist(Long id);


}
