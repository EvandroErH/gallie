package br.com.gallie.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.gallie.bean.ConsultaCepBean;
import br.com.gallie.bean.PessoaBean;
import br.com.gallie.classes.ConsultaCep;
import br.com.gallie.enums.EnumStatus;
import br.com.gallie.model.PessoaAgenda;
import br.com.gallie.model.PessoaAnotacao;
import br.com.gallie.model.PessoaFicha;
import br.com.gallie.utils.NotificacaoUtil;
import br.com.gallie.utils.WebUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author operador
 */
@ManagedBean
@SessionScoped
public class PessoaController implements Serializable {

    @EJB
    private PessoaBean pessoaBean;

    @EJB
    private ConsultaCepBean consultaCepBean;

    private PessoaFicha pessoaFicha;

    private PessoaAnotacao pessoaAnotacao;

    private List<PessoaFicha> listaPessoaFicha;

    private ScheduleModel pessoaAgendaModel;

    private String informacao;

    public PessoaController() {
        inicializar();
    }

    @PostConstruct
    public void inicializar() {
        pessoaFicha = null;
        pessoaAnotacao = null;
        listaPessoaFicha = new ArrayList<>();
        pessoaAgendaModel = new DefaultScheduleModel();
    }

    public void inicializarPessoaFicha() {
        this.informacao = null;
        pessoaFicha = new PessoaFicha();
    }

    public void inicializarLocalizacao() {
        this.listaPessoaFicha = pessoaBean.listarPessoaFicha(EnumStatus.APROVADO);
    }

    public void salvarPessoaFicha() {
        try {
            this.informacao = null;
            boolean novoCadastro = pessoaFicha.getId() == null;
            //
            pessoaBean.salvarPessoaFicha(pessoaFicha);
            // Enviar notificação de interação
            if (novoCadastro) {
                NotificacaoUtil.notificarCadastro(pessoaFicha);
                //
                this.informacao = "Seu cadastro foi registrado, aguarde que entrare-mos em contato com você "
                        + "o mais breve possível.";
                //
                pessoaFicha = new PessoaFicha();
            }
            WebUtil.messageInfo("Realizado com sucesso");
        } catch (Exception ex) {
            WebUtil.messageErro(ex.getMessage());
        }
    }

    public void selecionarPessoaFicha(final PessoaFicha pf) {
        this.pessoaFicha = pf;
        this.pessoaFicha.setListaPessoaAgenda(pessoaBean.listarPessoaAgenda(pf, true));
        //
        novaPessoaAnotacao();
        //
        pessoaAgendaModel = new DefaultScheduleModel();
        if (this.pessoaFicha.getListaPessoaAgenda() != null) {
            for (PessoaAgenda pa : this.pessoaFicha.getListaPessoaAgenda()) {
                if (pa.isAtivo()) {
                    pessoaAgendaModel.addEvent(new DefaultScheduleEvent(pa.getPessoaFicha().getIdentificador(), pa.getDataAgenda(), pa.getDataAgenda()));
                }
            }
        }
    }

    public void atualizarPessoaFichaCep() {
        if (this.pessoaFicha != null) {
            if (this.pessoaFicha.getCep() != null) {
                final ConsultaCep consultaCep = consultaCepBean.consultaCep(this.pessoaFicha.getCep());
                if (consultaCep != null) {
                    this.pessoaFicha.setCep(consultaCep.getCep());
                    this.pessoaFicha.setEndereco(consultaCep.getLogradouro());
                    this.pessoaFicha.setBairro(consultaCep.getBairro());
                    this.pessoaFicha.setCidade(consultaCep.getCidade());
                    this.pessoaFicha.setUf(consultaCep.getUf());
                } else {
                    this.pessoaFicha.setEndereco(null);
                    this.pessoaFicha.setNumero(null);
                    this.pessoaFicha.setBairro(null);
                    this.pessoaFicha.setCidade(null);
                    this.pessoaFicha.setUf(null);
                    this.pessoaFicha.setComplemento(null);
                }
            }
        }
    }

    public void cancelarPessoaFicha() {
        this.pessoaFicha = null;
    }

    public void novaPessoaAnotacao() {
        this.pessoaAnotacao = new PessoaAnotacao();
        this.pessoaAnotacao.setDataRegistro(new Date());
        this.pessoaAnotacao.setPessoaFicha(pessoaFicha);
    }

    public void listarPessoas(final EnumStatus status) {
        this.listaPessoaFicha = pessoaBean.listarPessoaFicha(status);
    }

    public PessoaFicha getPessoaFicha() {
        return pessoaFicha;
    }

    public void setPessoaFicha(PessoaFicha pessoaFicha) {
        this.pessoaFicha = pessoaFicha;
    }

    public PessoaAnotacao getPessoaAnotacao() {
        return pessoaAnotacao;
    }

    public void setPessoaAnotacao(PessoaAnotacao pessoaAnotacao) {
        this.pessoaAnotacao = pessoaAnotacao;
    }

    public List<PessoaFicha> getListaPessoaFicha() {
        return listaPessoaFicha;
    }

    public void setListaPessoaFicha(List<PessoaFicha> listaPessoaFicha) {
        this.listaPessoaFicha = listaPessoaFicha;
    }

    public ScheduleModel getPessoaAgendaModel() {
        return pessoaAgendaModel;
    }

    public void setPessoaAgendaModel(ScheduleModel pessoaAgendaModel) {
        this.pessoaAgendaModel = pessoaAgendaModel;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public List<EnumStatus> listarEnumStatusPessoa() {
        return Arrays.asList(EnumStatus.values());
    }
}
