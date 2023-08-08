package com.example.apiimobiliaria.controllers;

import com.example.apiimobiliaria.Services.ImovelService;
import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class ImovelController {

    @Autowired
    ImovelRepository imovelRepository;

    @Autowired
    ImovelService imovelService;

    @GetMapping("/imoveis")
    public List<ImovelModel> listarImoveis() {
        return imovelRepository.findAll();
    }

    @GetMapping("/imoveis/{id}")
    public ImovelModel imovelDetalhe(@PathVariable(value = "id") long id) {
        return imovelRepository.findById(id);
    }

    @ExceptionHandler
    @PostMapping("/criar-imovel")
    public ResponseEntity<ImovelModel> criarImovel(@RequestBody @Valid ImovelModel imovel){
        ImovelModel novoImovel = imovelService.save(imovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoImovel);
    }
}










