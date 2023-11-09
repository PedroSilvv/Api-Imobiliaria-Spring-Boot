package com.example.apiimobiliaria.models;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private boolean ECasa;
    private boolean EAP;
    private boolean EAluguel;
    private boolean EVenda;
    private boolean ECondominio;
    private boolean publicado;
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

    @JsonProperty("ECasa")
    public boolean getECasa() {
        return ECasa;
    }

    @JsonProperty("ECasa")
    public void setECasa(boolean ECasa) {
        this.ECasa = ECasa;
    }

    @JsonProperty("EAP")
    public boolean getEAP() {
        return EAP;
    }

    @JsonProperty("EAP")
    public void setEAP(boolean EAP) {
        this.EAP = EAP;
    }

    @JsonProperty("EAluguel")
    public boolean getEAluguel() {
        return EAluguel;
    }

    @JsonProperty("EAluguel")
    public void setEAluguel(boolean EAluguel) {
        this.EAluguel = EAluguel;
    }

    @JsonProperty("EVenda")
    public boolean getEVenda() {
        return EVenda;
    }

    @JsonProperty("EVenda")
    public void setEVenda(boolean EVenda) {
        this.EVenda = EVenda;
    }

    @JsonProperty("ECondominio")
    public boolean getECondominio() {
        return ECondominio;
    }

    @JsonProperty("ECondominio")
    public void setECondominio(boolean ECondominio) {
        this.ECondominio = ECondominio;
    }

    @JsonProperty("EPublicado")
    public boolean getPublicado() {
        return publicado;
    }

    @JsonProperty("EPublicado")
    public void setPublicado(boolean publicado) {
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

