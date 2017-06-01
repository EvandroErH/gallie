package br.com.gallie.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.gallie.bean.ConsultaCepBean;
import br.com.gallie.bean.PessoaBean;
import br.com.gallie.classes.ConsultaCep;
import br.com.gallie.enums.EnumStatusPessoa;
import br.com.gallie.model.PessoaAgenda;
import br.com.gallie.model.PessoaFicha;
import br.com.gallie.utils.WebUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.TabChangeEvent;
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

    private List<PessoaFicha> listaPessoaFicha;

    private EnumStatusPessoa statusSelecioando = EnumStatusPessoa.PENDENTE;

    private ScheduleModel pessoaAgendaModel;

    public PessoaController() {
        inicializar();
    }

    @PostConstruct
    public void inicializar() {
        pessoaFicha = null;
        listaPessoaFicha = new ArrayList<>();
        pessoaAgendaModel = new DefaultScheduleModel();
    }

    public void inicializarPessoaFicha() {
        pessoaFicha = new PessoaFicha();
    }
    
    public void inicializarLocalizacao() {
        this.listaPessoaFicha = pessoaBean.listarPessoaFicha(EnumStatusPessoa.APROVADO);
    }

    public void salvarPessoaFicha() {
        try {
            pessoaBean.salvarPessoaFicha(pessoaFicha);
            this.pessoaFicha = null;
            listarPessoaFicha(this.statusSelecioando);
            WebUtil.messageInfo("Realizado com sucesso");
        } catch (Exception ex) {
            WebUtil.messageErro(ex.getMessage());
        }
    }

    public void selecionarPessoaFicha(final PessoaFicha pf) {
        this.pessoaFicha = pf;
        this.pessoaFicha.setListaPessoaAgenda(pessoaBean.listarPessoaAgenda(pf, true));
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

    public void listarPessoaFicha(final EnumStatusPessoa status) {
        this.statusSelecioando = status;
        this.listaPessoaFicha = pessoaBean.listarPessoaFicha(this.statusSelecioando);
    }

    public void onTabPessoaFichaChange(TabChangeEvent event) {
        this.statusSelecioando = EnumStatusPessoa.valueOf(event.getTab().getTitletip());

        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        switch (statusSelecioando) {
            case AGENDA:
                this.pessoaAgendaModel = new DefaultScheduleModel();
                for (int i = 0; i < 10; i++) {
                    this.pessoaAgendaModel.addEvent(new DefaultScheduleEvent("Pasta " + i, c.getTime(), c.getTime()));
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
                break;
            default:
                this.listaPessoaFicha = pessoaBean.listarPessoaFicha(this.statusSelecioando);
        }
    }

    public PessoaFicha getPessoaFicha() {
        return pessoaFicha;
    }

    public void setPessoaFicha(PessoaFicha pessoaFicha) {
        this.pessoaFicha = pessoaFicha;
    }

    public List<PessoaFicha> getListaPessoaFicha() {
        return listaPessoaFicha;
    }

    public void setListaPessoaFicha(List<PessoaFicha> listaPessoaFicha) {
        this.listaPessoaFicha = listaPessoaFicha;
    }

    public EnumStatusPessoa getStatusSelecioando() {
        return statusSelecioando;
    }

    public void setStatusSelecioando(EnumStatusPessoa statusSelecioando) {
        this.statusSelecioando = statusSelecioando;
    }

    public ScheduleModel getPessoaAgendaModel() {
        return pessoaAgendaModel;
    }

    public void setPessoaAgendaModel(ScheduleModel pessoaAgendaModel) {
        this.pessoaAgendaModel = pessoaAgendaModel;
    }

    public List<EnumStatusPessoa> listarEnumStatusPessoa() {
        return Arrays.asList(EnumStatusPessoa.values());
    }

}
