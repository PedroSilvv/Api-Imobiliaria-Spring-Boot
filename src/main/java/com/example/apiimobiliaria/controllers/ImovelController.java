package com.example.apiimobiliaria.controllers;

import com.example.apiimobiliaria.Services.ImovelService;
import com.example.apiimobiliaria.dtos.AtualizarImovelRequestDTO;
import com.example.apiimobiliaria.dtos.FiltroImovelRequestDTO;
import com.example.apiimobiliaria.dtos.FotosImovelResponseDTO;
import com.example.apiimobiliaria.models.FotoImovelModel;
import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            Assert.isNull(imovel.getCodigo(),
                    "O campo codigo não precisa ser fornecido, ele será gerado automaticamente");

            ImovelModel novoImovel = imovelService.save(imovel);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoImovel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @PostMapping("/atualizar-imovel/{id}")
    public ResponseEntity<?> atualizarImovel(@PathVariable(value = "id") long id,
                                             @RequestBody AtualizarImovelRequestDTO requestDTO){

        ImovelModel imovel = imovelRepository.findById(id);

        if(imovel == null){return null;}

        imovel.setEndereco(requestDTO.getEndereco());
        imovel.setCidade(requestDTO.getCidade());
        imovel.setBairro(requestDTO.getBairro());
        imovel.setEAluguel(requestDTO.iseAluguel());
        imovel.setEVenda(requestDTO.iseVenda());

        ImovelModel novoImovel = imovelService.updateImovel(imovel);

        return ResponseEntity.status(HttpStatus.OK).body(imovel);
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

    @GetMapping("foto/{name}")
    public ResponseEntity<?> verFoto(@PathVariable String name) throws Exception {

        try{
            byte[] fotoData = imovelService.downloadFoto(name);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(fotoData);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ error : " + e.getMessage() + " }");
        }
    }

    @GetMapping("{codigo}/fotos")
    public ResponseEntity<?> verFotoImovel (@PathVariable("codigo") String codigo){

        try{
            List<String> listaFotos = new ArrayList<>();

            ImovelModel imovelModel = imovelRepository.findByCodigo(codigo);

            Assert.notNull(imovelModel, "Codigo do imovel nao encontrado.");

            //List<FotoImovelModel> listaFotosModel = new ArrayList<>();

            for(FotoImovelModel foto : imovelModel.getFotos()){
                listaFotos.add(foto.getName());
            }

            FotosImovelResponseDTO response = new FotosImovelResponseDTO(listaFotos);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    @PostMapping("filtrar-imoveis")
    public ResponseEntity<List<ImovelModel>> filtrarImoveis(@RequestBody FiltroImovelRequestDTO filtro) throws Exception {

        try{List<ImovelModel> imoveisFiltrados = imovelService.filtrarImoveis(
                filtro.isVenda(),
                filtro.isAluguel(),
                filtro.isAp(),
                filtro.isCasa(),
                filtro.isCondominio(),
                filtro.getPrecoMinimo(),
                filtro.getPrecoMaximo()
        );
            return ResponseEntity.status(HttpStatus.OK).body(imoveisFiltrados);
        }
        catch (Exception e){
            throw new Exception("Não foi encontrado nenhum imóvel com essas especificações");
        }



    }


}






