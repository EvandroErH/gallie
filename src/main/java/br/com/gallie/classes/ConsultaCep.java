/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edimar Fernando Liberali
 */
@XmlRootElement(name = "webservicecep")
public class ConsultaCep {

    protected String cep;

    protected String tipoLogradouro;

    protected String logradouro;

    protected String bairro;

    protected String cidade;

    protected String uf;

    protected Integer resultado;

    public ConsultaCep() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    @XmlElement(name = "logradouro")
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    @XmlElement(name = "tipo_logradouro")
    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    @XmlElement(name = "bairro")
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    @XmlElement(name = "cidade")
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    @XmlElement(name = "uf")
    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getResultado() {
        return resultado;
    }

    @XmlElement(name = "resultado")
    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

}
