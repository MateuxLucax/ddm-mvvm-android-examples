package com.example.cep.domain.model;

import java.util.Objects;

public class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private boolean erro;

    public String getCep() {
        return cep;
    }

    public boolean getErro() {
        return erro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getIbge() {
        return ibge;
    }

    public String getGia() {
        return gia;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public Cep setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Cep setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public Cep setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Cep setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Cep setLocalidade(String localidade) {
        this.localidade = localidade;
        return this;
    }

    public Cep setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public Cep setIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public Cep setGia(String gia) {
        this.gia = gia;
        return this;
    }

    public Cep setDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public Cep setSiafi(String siafi) {
        this.siafi = siafi;
        return this;
    }

    public boolean isErro() {
        return erro;
    }

    public Cep setErro(boolean erro) {
        this.erro = erro;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cep cep1 = (Cep) o;
        return cep.equals(cep1.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep);
    }

}

