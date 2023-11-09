package com.example.apiimobiliaria.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FiltroImovelRequestDTO {

    @NotNull
    private boolean venda;
    @NotNull
    private boolean aluguel;
    @NotNull
    private boolean ap;
    @NotNull
    private boolean casa;
    @NotNull
    private boolean condominio;
    @NotNull
    private BigDecimal precoMinimo;
    @NotNull
    private BigDecimal precoMaximo;

    public boolean isVenda() {
        return venda;
    }

    public void setVenda(boolean venda) {
        this.venda = venda;
    }

    public boolean isAluguel() {
        return aluguel;
    }

    public void setAluguel(boolean aluguel) {
        this.aluguel = aluguel;
    }

    public boolean isAp() {
        return ap;
    }

    public void setAp(boolean ap) {
        this.ap = ap;
    }

    public boolean isCasa() {
        return casa;
    }

    public void setCasa(boolean casa) {
        this.casa = casa;
    }

    public boolean isCondominio() {
        return condominio;
    }

    public void setCondominio(boolean condominio) {
        this.condominio = condominio;
    }

    public BigDecimal getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(BigDecimal precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public BigDecimal getPrecoMaximo() {
        return precoMaximo;
    }

    public void setPrecoMaximo(BigDecimal precoMaximo) {
        this.precoMaximo = precoMaximo;
    }
}
