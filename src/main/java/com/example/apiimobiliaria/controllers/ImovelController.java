package com.example.apiimobiliaria.controllers;

import com.example.apiimobiliaria.Services.ImovelService;
import com.example.apiimobiliaria.models.FotoImovelModel;
import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    //@ExceptionHandler
    @PostMapping("/criar-imovel")
    public ResponseEntity<?> criarImovel(@RequestBody ImovelModel imovel) {

        try {
            if (imovel.getECasa() == null) {
                imovel.setECasa(true);
            }

            Assert.isNull(imovel.getCodigo(),
                    "O campo codigo não precisa ser fornecido, ele será gerado automaticamente");

            ImovelModel novoImovel = imovelService.save(imovel);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoImovel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @PostMapping("/add-fotos/{id}")
    public ResponseEntity<?> addFotos(@PathVariable(value = "id") long id, @RequestBody List<MultipartFile> files) {

        ImovelModel imovel = imovelRepository.findById(id);

        if (imovel == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> fotos = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String uploadImage = imovelService.uploadFoto(file, imovel);
                fotos.add(uploadImage);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fotos);
    }

}






