package com.example.apiimobiliaria.repositories;

import com.example.apiimobiliaria.models.FotoImovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoImovelRepository extends JpaRepository<FotoImovelModel, Long> {

    Optional<FotoImovelModel> findByName(String fileName);
}
