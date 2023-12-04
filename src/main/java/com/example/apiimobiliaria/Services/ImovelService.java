package com.example.apiimobiliaria.Services;


import com.example.apiimobiliaria.models.FotoImovelModel;
import com.example.apiimobiliaria.models.ImovelModel;
import com.example.apiimobiliaria.repositories.FotoImovelRepository;
import com.example.apiimobiliaria.repositories.ImovelRepository;
import com.example.apiimobiliaria.utils.FotoUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.antlr.v4.runtime.atn.PredicateEvalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;
import java.io.IOException;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository imovelRepository;

    @Autowired
    FotoImovelRepository fotoImovelRepository;

    @PersistenceContext
    @Autowired
    EntityManager entityManager;

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

    public ImovelModel updateImovel(ImovelModel imovel){
        return imovelRepository.save(imovel);
    }

    public String uploadFoto(MultipartFile file, ImovelModel imovel) throws IOException {

        FotoImovelModel fotoData = new FotoImovelModel();
        fotoData.setImovel(imovel);

        FotoImovelModel foto = FotoImovelModel.builder()
                        .name(file.getOriginalFilename()+"-"+imovel.getCodigo().toLowerCase())
                        .type(file.getContentType())
                        .fotoData(FotoUtils.compressImage(file.getBytes()))
                        .build();

        foto.setImovel(imovel);


        foto = fotoImovelRepository.save(foto);
        return "Foto adicionada com sucesso : " + foto.getName();
    }

    public byte[] downloadFoto(String name) throws NotFoundException {

        Optional<FotoImovelModel> fotoData = fotoImovelRepository.findByName(name);

        if(fotoData == null){
            throw new NotFoundException();
        }

        byte[] fotos = FotoUtils.decompressImage(fotoData.get().getFotoData());
        return fotos;

    }

    public List<ImovelModel> filtrarImoveis(boolean venda
                                            , boolean aluguel
                                            , boolean ap
                                            , boolean casa
                                            , boolean condominio
                                            , BigDecimal precoMin
                                            , BigDecimal precoMax){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImovelModel> criteriaQuery = criteriaBuilder.createQuery(ImovelModel.class);
        Root<ImovelModel> root = criteriaQuery.from(ImovelModel.class);


        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("EVenda"), venda));
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("EAluguel"), aluguel));
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("EAP"), ap));
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("ECasa"), casa));
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("ECondominio"), condominio));

        Predicate intervaloValores = criteriaBuilder.between(root.get("valor"), precoMin, precoMax);

        criteriaQuery.select(root).where(intervaloValores);

        return entityManager.createQuery(criteriaQuery).getResultList();

    }

}

