package com.example.apiimobiliaria.models;


import com.example.apiimobiliaria.repositories.ImovelRepository;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name="tb_imoveis")
public class ImovelModel implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private UserModel proprietario;
    private String endereco;
    private String cidade;
    private String bairro;
    private Integer numeroCasa;
    private String regiao;
    private String tipoImovel;
    private Boolean ECasa;
    private Boolean EAP;
    private Boolean EAluguel;
    private Boolean EVenda;
    private Boolean ECondominio;
    private Boolean publicado;
    private Integer dormitorios;
    private Integer banheiros;
    private Integer vagasGaragem;


    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL)
    private List<FotoImovelModel> fotos;

    @Column(precision = 10, scale = 2)
    private BigDecimal tamanho;

    @Column(precision = 10, scale = 2)
    private BigDecimal tamanhoUtil;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorCondominio;

    @Column(precision = 10, scale = 2)
    private String descricao;

    public UserModel getProprietario() {
        return proprietario;
    }

    public void setProprietario(UserModel proprietario) {
        this.proprietario = proprietario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Boolean getECasa() {
        return ECasa;
    }

    public void setECasa(Boolean ECasa) {
        this.ECasa = ECasa;
    }

    public Boolean getEAP() {
        return EAP;
    }

    public void setEAP(Boolean EAP) {
        this.EAP = EAP;
    }

    public Boolean getEAluguel() {
        return EAluguel;
    }

    public void setEAluguel(Boolean EAluguel) {
        this.EAluguel = EAluguel;
    }

    public Boolean getEVenda() {
        return EVenda;
    }

    public void setEVenda(Boolean EVenda) {
        this.EVenda = EVenda;
    }

    public Boolean getECondominio() {
        return ECondominio;
    }

    public void setECondominio(Boolean ECondominio) {
        this.ECondominio = ECondominio;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public Integer getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(Integer dormitorios) {
        this.dormitorios = dormitorios;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getVagasGaragem() {
        return vagasGaragem;
    }

    public void setVagasGaragem(Integer vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public BigDecimal getTamanho() {
        return tamanho;
    }

    public void setTamanho(BigDecimal tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getTamanhoUtil() {
        return tamanhoUtil;
    }

    public void setTamanhoUtil(BigDecimal tamanhoUtil) {
        this.tamanhoUtil = tamanhoUtil;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(BigDecimal valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<FotoImovelModel> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoImovelModel> fotos) {
        this.fotos = fotos;
    }
};

