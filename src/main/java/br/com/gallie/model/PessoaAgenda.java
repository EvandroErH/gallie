/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author operador
 */
@Entity
@Table(name = "PESSOA_AGENDA")
public class PessoaAgenda implements Serializable {

    @Id
    @SequenceGenerator(name = "ID_PESSOA_AGENDA", sequenceName = "SEQ_PESSOA_AGENDA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PESSOA_AGENDA")
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA_FICHA", referencedColumnName = "ID")
    private PessoaFicha pessoaFicha;

    @Column(name = "DH_AGENDA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAgenda;

    @Column(name = "DH_REALIZADO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataRealizada;

    @Column(name = "DH_PROXIMA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataProxima;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "ATIVO")
    private boolean ativo;

    @Column(name = "VALOR")
    private double valorVenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PessoaFicha getPessoaFicha() {
        return pessoaFicha;
    }

    public void setPessoaFicha(PessoaFicha pessoaFicha) {
        this.pessoaFicha = pessoaFicha;
    }

    public Date getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Date dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public Date getDataProxima() {
        return dataProxima;
    }

    public void setDataProxima(Date dataProxima) {
        this.dataProxima = dataProxima;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}
