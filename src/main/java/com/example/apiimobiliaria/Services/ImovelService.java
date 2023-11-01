package com.example.apiimobiliaria.Services;

import com.example.apiimobiliaria.models.FotoImovelModel;
import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.FotoImovelRepository;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import com.example.apiimobiliaria.utils.FotoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository imovelRepository;

    @Autowired
    FotoImovelRepository fotoImovelRepository;

    public ImovelModel save(ImovelModel imovel) {

        if (imovel.getCodigo() == null || imovel.getCodigo() != null) {
            String prefixo = imovel.getECasa() ? "CA" : (imovel.getEAP() ? "AP" : "IM");

            ImovelModel ultimoCodigo = imovelRepository.findFirstByECasaAndEAPOrderByCodigoDesc(imovel.getECasa(), imovel.getEAP());

            int novoNumero;
            if (ultimoCodigo != null) {
                int ultimoNumero = 0;
                if (ultimoCodigo.getCodigo() != null && ultimoCodigo.getCodigo().length() > 2) {
                    ultimoNumero = Integer.parseInt(ultimoCodigo.getCodigo().substring(2));
                }
                novoNumero = ultimoNumero + 1;
            } else {
                novoNumero = 1;
            }

            String codigoGerado = prefixo + String.format("%04d", novoNumero);
            imovel.setCodigo(codigoGerado);
        }

        return imovelRepository.save(imovel);
    }

    public String uploadFoto(MultipartFile file, ImovelModel imovel) throws IOException {

        FotoImovelModel fotoData = new FotoImovelModel();
        fotoData.setImovel(imovel);

        FotoImovelModel foto = FotoImovelModel.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .fotoData(FotoUtils.compressImage(file.getBytes()))
                        .build();

        foto.setImovel(imovel);


        foto = fotoImovelRepository.save(foto);
        return "Foto adicionada com sucesso : " + file.getOriginalFilename();
    }
}

