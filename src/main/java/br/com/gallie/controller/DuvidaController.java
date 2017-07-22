package br.com.gallie.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.gallie.bean.DuvidaBean;
import br.com.gallie.enums.EnumStatus;
import br.com.gallie.model.Duvidas;
import br.com.gallie.utils.NotificacaoUtil;
import br.com.gallie.utils.WebUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author operador
 */
@ManagedBean
@SessionScoped
public class DuvidaController implements Serializable {

    private Duvidas duvidas;

    private List<Duvidas> listaDuvidasGeral;

    private List<Duvidas> listaDuvidasVisivel;

    private String nome;

    private String telefone;

    private String email;

    private String mensagem;

    private String informacao;

    @EJB
    private DuvidaBean duvidaBean;

    public void inicializar() {
        this.duvidas = null;
        this.nome = null;
        this.email = null;
        this.telefone = null;
        this.mensagem = null;
        this.informacao = null;
        //
        inicializarDuvidasGeral();
        inicializarDuvidasVisivel();
    }

    public void inicializarDuvidasGeral() {
        this.listaDuvidasGeral = duvidaBean.inicializarDuvidasGeral();
    }

    public void inicializarDuvidasVisivel() {
        this.listaDuvidasVisivel = duvidaBean.inicializarDuvidasVisivel();
    }

    public void listarDuvidas(final EnumStatus status) {
        this.listaDuvidasGeral = duvidaBean.inicializarDuvidasGeral();
    }

    public void salvarDuvidas() {
        try {
            this.informacao = null;
            //
            duvidas = new Duvidas();
            duvidas.setNome(nome);
            duvidas.setEmail(email);
            duvidas.setTelefone(telefone);
            duvidas.setMensagem(mensagem);
            //
            duvidas = duvidaBean.salvarDuvidas(duvidas);
            WebUtil.messageInfo("Realizado com sucesso");
            //
            this.informacao = "Sua dúvida foi registrada, aguarde que entrare-mos em contato com você "
                    + "o mais breve possível.";
            //
            this.nome = null;
            this.email = null;
            this.telefone = null;
            this.mensagem = null;
            // Enviar notificação de interação
            NotificacaoUtil.notificarDuvida(duvidas);
            //
        } catch (Exception e) {
            WebUtil.messageErro(e.getMessage());
        }
    }

    public void atualizarDuvidas() {
        try {
            duvidas = duvidaBean.salvarDuvidas(duvidas);
            WebUtil.messageInfo("Realizado com sucesso");
        } catch (Exception e) {
            WebUtil.messageErro(e.getMessage());
        }
    }

    public void removerDuvidas(final Duvidas d) {
        if (d != null) {
            duvidaBean.removerDuvidas(d);
            WebUtil.messageInfo("Realizado com sucesso");
        }
    }

    public void selecionarDuvidas(final Duvidas d) {
        this.duvidas = d;
    }

    public Duvidas getDuvidas() {
        return duvidas;
    }

    public void setDuvidas(Duvidas duvidas) {
        this.duvidas = duvidas;
    }

    public List<Duvidas> getListaDuvidasGeral() {
        return listaDuvidasGeral;
    }

    public void setListaDuvidasGeral(List<Duvidas> listaDuvidasGeral) {
        this.listaDuvidasGeral = listaDuvidasGeral;
    }

    public List<Duvidas> getListaDuvidasVisivel() {
        return listaDuvidasVisivel;
    }

    public void setListaDuvidasVisivel(List<Duvidas> listaDuvidasVisivel) {
        this.listaDuvidasVisivel = listaDuvidasVisivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

}
