package com.example.apiimobiliaria.Services;

import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository imovelRepository;


    public ImovelModel save(ImovelModel imovel){
        String prefixo = imovel.getECasa() ? "CA" : (imovel.getEAP() ? "AP" : "IM");

        ImovelModel ultimoCodigo = imovelRepository.findFirstByECasaAndEAPOrderByCodigoDesc(imovel.getECasa(), imovel.getEAP());

        int novoNumero;
        if (ultimoCodigo != null) {
            int ultimoNumero = Integer.parseInt(ultimoCodigo.getCodigo().substring(2));
            novoNumero = ultimoNumero + 1;
        } else {
            novoNumero = 1;
        }

        String codigoGerado = prefixo + String.format("%04d", novoNumero);
        imovel.setCodigo(codigoGerado);

        return imovelRepository.save(imovel);
    }

}
