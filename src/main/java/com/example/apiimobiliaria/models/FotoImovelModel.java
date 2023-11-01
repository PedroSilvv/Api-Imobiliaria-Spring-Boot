package com.example.apiimobiliaria.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FotoImovelModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String url;

    @JsonIgnore
    @Lob
    @Column(name = "fotodata",length = 1000)
    private byte[] fotoData;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private ImovelModel imovel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFotoData() {
        return fotoData;
    }

    public void setFotoData(byte[] fotoData) {
        this.fotoData = fotoData;
    }

    public ImovelModel getImovel() {
        return imovel;
    }

    public void setImovel(ImovelModel imovel) {
        this.imovel = imovel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
