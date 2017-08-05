/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.bean;

import br.com.gallie.enums.EnumStatus;
import br.com.gallie.model.PessoaAgenda;
import br.com.gallie.model.PessoaFicha;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author operador
 */
@Stateless
public class PessoaBean {

    @PersistenceContext(unitName = "gallie-pu")
    private EntityManager em;

    public PessoaFicha salvarPessoaFicha(PessoaFicha pf) throws Exception {
        if (pf != null) {
            // Adicionar validações
            if (pf.getNome() == null) {
                throw new Exception("Campo nome não informado");
            }
            if (pf.getTelefone() == null) {
                throw new Exception("Campo telefone não informado");
            }
            if (pf.getCidade() == null) {
                throw new Exception("Campo cidade não informado");
            }
            if (pf.getEndereco() == null) {
                throw new Exception("Campo endereco não informado");
            }
            if (pf.getNumero() == null) {
                throw new Exception("Campo número não informado");
            }
            if (pf.getCep() == null) {
                throw new Exception("Campo cep não informado");
            }
            // Atualizar GeoLocalizacao
            pf = atualiarGeoLocalizacao(pf);
            //
            if (pf.getId() == null) {
                pf.setStatus(EnumStatus.PENDENTE);
                pf.setDataRegistro(new Date());
                em.persist(pf);
            } else {
                pf = em.merge(pf);
            }
        }
        return pf;
    }

    public PessoaFicha atualiarGeoLocalizacao(PessoaFicha pf) {
        final Geocoder geocoder = new Geocoder();
        final String endereco = pf.getCidade() + ", " + pf.getEndereco() + " - " + pf.getNumero() + ", " + pf.getCep();
        //
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(endereco).getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        if (geocoderResponse != null) {
            if (geocoderResponse.getResults() != null && geocoderResponse.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = geocoderResponse.getResults();
                pf.setLatitude(results.get(0).getGeometry().getLocation().getLat().doubleValue());
                pf.setLongitude(results.get(0).getGeometry().getLocation().getLng().doubleValue());
            }
        }
        return pf;
    }

    public List<PessoaFicha> listarPessoaFicha(final EnumStatus status) {
        if (status.equals(EnumStatus.TODOS)) {
            return em.createQuery("select o from PessoaFicha o order by o.dataRegistro desc").
                    getResultList();
        } else {
            return em.createQuery("select o from PessoaFicha o where o.status = :status order by o.dataRegistro desc").
                    setParameter("status", status).
                    getResultList();
        }
    }

    public List<PessoaAgenda> listarPessoaAgenda(final PessoaFicha pf, Boolean ativo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select o from PessoaAgenda o ");
        sb.append(" where o.pessoaFicha.id = :pf ");
        if (ativo != null) {
            sb.append(" and o.ativo = :ativo");
        }
        sb.append(" order by o.dataAgenda desc");
        //
        final Query q = em.createQuery(sb.toString());
        q.setParameter("pf", pf.getId());
        if (ativo != null) {
            q.setParameter("ativo", ativo);
        }
        return q.getResultList();
    }
}
