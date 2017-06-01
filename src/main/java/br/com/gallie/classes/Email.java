/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.classes;

/**
 *
 * @author operador
 */
public class Email {

    public String de;

    public String para;

    public String assunto;

    public StringBuilder mensagem;

    public Email() {
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public StringBuilder getMensagem() {
        return mensagem;
    }

    public void setMensagem(StringBuilder mensagem) {
        this.mensagem = mensagem;
    }
}
