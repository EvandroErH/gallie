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
@Table(name = "PESSOA_ANOTACAO")
public class PessoaAnotacao implements Serializable {

    @Id
    @SequenceGenerator(name = "ID_PESSOA_ANOTACAO", sequenceName = "SEQ_PESSOA_ANOTACAO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PESSOA_ANOTACAO")
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA_FICHA", referencedColumnName = "ID")
    private PessoaFicha pessoaFicha;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "DH_REGISTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataRegistro;

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
