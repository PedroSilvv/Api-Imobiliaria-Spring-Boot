package com.example.apiimobiliaria.dtos;


public class AtualizarImovelRequestDTO {

    private String endereco;
    private String cidade;
    private String bairro;
    private boolean eAluguel;
    private boolean eVenda;

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

    public boolean iseAluguel() {
        return eAluguel;
    }

    public void seteAluguel(boolean eAluguel) {
        this.eAluguel = eAluguel;
    }

    public boolean iseVenda() {
        return eVenda;
    }

    public void seteVenda(boolean eVenda) {
        this.eVenda = eVenda;
    }
}
