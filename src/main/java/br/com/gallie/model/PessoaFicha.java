/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.model;

import br.com.gallie.enums.EnumStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author operador
 */
@Entity
@Table(name = "PESSOA_FICHA")
public class PessoaFicha implements Serializable {

    @Id
    @SequenceGenerator(name = "ID_PESSOA_FICHA", sequenceName = "SEQ_PESSOA_FICHA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PESSOA_FICHA")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IDENTIFICADOR")
    private String identificador;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "UF")
    private String uf;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "TRABALHO_LOCAL")
    private String trabalhoLocal;

    @Column(name = "TRABALHO_TELEFONE")
    private String trabalhoTelefone;

    @Column(name = "FAMILIAR_NOME")
    private String familiarNome;

    @Column(name = "FAMILIAR_PARENTESCO")
    private String familiarParentesco;

    @Column(name = "FAMILIAR_ENDERECO")
    private String familiarEndereco;

    @Column(name = "FAMILIAR_TELEFONE")
    private String familiarTelefone;

    @Column(name = "DATA_REGISTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataRegistro;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoaFicha", cascade = {CascadeType.ALL})
    private List<PessoaAnotacao> anotacoes = new ArrayList();

    @Transient
    private List<PessoaAgenda> listaPessoaAgenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTrabalhoLocal() {
        return trabalhoLocal;
    }

    public void setTrabalhoLocal(String trabalhoLocal) {
        this.trabalhoLocal = trabalhoLocal;
    }

    public String getTrabalhoTelefone() {
        return trabalhoTelefone;
    }

    public void setTrabalhoTelefone(String trabalhoTelefone) {
        this.trabalhoTelefone = trabalhoTelefone;
    }

    public String getFamiliarNome() {
        return familiarNome;
    }

    public void setFamiliarNome(String familiarNome) {
        this.familiarNome = familiarNome;
    }

    public String getFamiliarParentesco() {
        return familiarParentesco;
    }

    public void setFamiliarParentesco(String familiarParentesco) {
        this.familiarParentesco = familiarParentesco;
    }

    public String getFamiliarEndereco() {
        return familiarEndereco;
    }

    public void setFamiliarEndereco(String familiarEndereco) {
        this.familiarEndereco = familiarEndereco;
    }

    public String getFamiliarTelefone() {
        return familiarTelefone;
    }

    public void setFamiliarTelefone(String familiarTelefone) {
        this.familiarTelefone = familiarTelefone;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<PessoaAgenda> getListaPessoaAgenda() {
        return listaPessoaAgenda;
    }

    public void setListaPessoaAgenda(List<PessoaAgenda> listaPessoaAgenda) {
        this.listaPessoaAgenda = listaPessoaAgenda;
    }

    public List<PessoaAnotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<PessoaAnotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
}
