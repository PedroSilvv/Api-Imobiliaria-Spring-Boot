package com.example.apiimobiliaria.repositories;

import com.example.apiimobiliaria.models.ImovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository extends JpaRepository<ImovelModel, Long> {


    @Override
    List<ImovelModel> findAll();
    ImovelModel findById(long id);
    ImovelModel findFirstByECasaAndEAPOrderByCodigoDesc(Boolean ECasa, Boolean EAP);
    //ImovelModel save(ImovelModel imovel);

}

