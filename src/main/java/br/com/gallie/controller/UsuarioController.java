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

    private static final String hashSenha = "g4ll13j014s";

    private static final String hashUsuario = "gallie";

    private String usuario;

    private String senha;

    private boolean autenticado;

    @PostConstruct
    private void inicializar() {
        autenticado = false;
        usuario = null;
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
        if (usuario != null && senha != null) {
            if (usuario.equalsIgnoreCase(hashUsuario)) {
                if (senha.equalsIgnoreCase(hashSenha)) {
                    autenticado = true;
                    usuario = null;
                    senha = null;
                    
                    WebUtil.managedBean(PessoaController.class).inicializar();
                    WebUtil.redirect("/gallie/admin/contatos.xhtml");
                    return;
                }
            }
        }
        autenticado = false;
        usuario = null;
        senha = null;
        WebUtil.messageErro("Senha inválida ou não preenchida");
        WebUtil.redirect("/gallie/admin/login.xhtml");
    }

    public void logout() {
        autenticado = false;
        usuario = null;
        senha = null;
        WebUtil.redirect("/gallie/index.xhtml");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
