/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.bean;

import br.com.gallie.model.Duvidas;
import br.com.gallie.utils.StringUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author operador
 */
@Stateless
public class DuvidaBean {

    @PersistenceContext(unitName = "gallie-pu")
    private EntityManager em;

    public Duvidas salvarDuvidas(Duvidas d) throws Exception {
        if (d.getNome() == null) {
            throw new Exception("Você deve informar o nome");
        }
        if (d.getTelefone() == null) {
            throw new Exception("Você deve informar o telefone");
        }
        if (d.getMensagem() == null) {
            throw new Exception("Você deve informar uma mensagem");
        }
        if (d.getEmail() != null) {
            d.setEmail(d.getEmail().toLowerCase());
        }
        d.setNome(d.getNome().toUpperCase());
        d.setMensagem(StringUtil.capitalizar(d.getMensagem()));
        if (d.getResposta() != null) {
            d.setResposta(StringUtil.capitalizar(d.getResposta()));
        }
        //
        if (d.getId() == null) {
            d.setDataRegistro(new Date());
            em.persist(d);
        } else {
            d = em.merge(d);
        }
        return d;
    }

    public void removerDuvidas(final Duvidas d) {
        if (d.getId() != null) {
            em.remove(em.find(Duvidas.class, d.getId()));
        }
    }

    public List<Duvidas> inicializarDuvidasGeral() {
        return em.createQuery("select o from Duvidas o order by o.dataRegistro desc").getResultList();

    }

    public List<Duvidas> inicializarDuvidasVisivel() {
        return em.createQuery("select o from Duvidas o where o.visivel = true order by o.dataRegistro desc").getResultList();
    }
}
