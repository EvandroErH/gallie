package br.com.gallie.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.gallie.enums.EnumProcesso;
import br.com.gallie.enums.EnumStatus;
import br.com.gallie.utils.WebUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author operador
 */
@ManagedBean
@SessionScoped
public class ProcessoController implements Serializable {

    private EnumProcesso processo;

    private EnumStatus status;

    private Integer index;

    public void selecionarProcesso(final EnumProcesso processo, final Integer index) {
        selecionarProcesso(processo, index, EnumStatus.PENDENTE);
    }

    public void selecionarProcesso(final EnumProcesso processo, final Integer index, final EnumStatus status) {
        this.processo = processo;
        this.status = status;
        this.index = index;
        //
        switch (processo) {
            case PESSOA:
                final PessoaController pc = WebUtil.managedBean(PessoaController.class);
                pc.setPessoaFicha(null);
                pc.listarPessoas(status);
                break;
            case PERGUNTA:
                final DuvidaController dc = WebUtil.managedBean(DuvidaController.class);
                dc.setDuvidas(null);
                dc.listarDuvidas(status);
                break;
            case AGENDA:
                break;
        }
    }

    public EnumProcesso getProcesso() {
        return processo;
    }

    public void setProcesso(EnumProcesso processo) {
        this.processo = processo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
