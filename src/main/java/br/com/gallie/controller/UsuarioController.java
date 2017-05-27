package br.com.gallie.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.gallie.utils.WebUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author operador
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    private static final String hash = "g4ll13j014s";

    private String senha;

    private boolean autenticado;

    @PostConstruct
    private void inicializar() {
        autenticado = false;
        senha = null;
    }

    public void administrar() {
        if (autenticado) {
            WebUtil.redirect("/gallie/admin/contatos.xhtml");
        } else {
            WebUtil.redirect("/gallie/admin/login.xhtml");
        }
    }

    public void login() {
        if (senha != null) {
            if (senha.equalsIgnoreCase(hash)) {
                autenticado = true;
                senha = null;
                WebUtil.redirect("/gallie/admin/contatos.xhtml");
                return;
            }
        }
        autenticado = false;
        senha = null;
        WebUtil.redirect("/gallie/admin/login.xhtml");
        WebUtil.messageErro("Senha inválida ou não preenchida");
    }

    public void logout() {
        autenticado = false;
        senha = null;
        WebUtil.redirect("/gallie/index.xhtml");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

}
