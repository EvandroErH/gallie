/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.utils;

import br.com.gallie.classes.Email;
import br.com.gallie.model.Duvidas;
import br.com.gallie.model.PessoaFicha;
import static br.com.gallie.utils.EmailUtil.enviar;

/**
 *
 * @author operador
 */
public class NotificacaoUtil {

    public static void notificarCadastro(final PessoaFicha p) {
        if (p != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Notificação de cadastro:");
            sb.append("<br/>");
            sb.append("Nome: ").append(p.getNome());
            sb.append("<br/>");
            sb.append("Email:").append(p.getEmail());
            sb.append("<br/>");
            sb.append("Telefone:").append(p.getTelefone());
            sb.append("<br/>");
            sb.append("Cidade:");
            sb.append("<br/>");
            sb.append(p.getCidade());
            sb.append("<br/>");
            sb.append("Responda esta o mais breve possível.");
            //
            final Email email = new Email();
            email.setAssunto("Notificação de cadastro");
            email.setDe("suporte@galliejoias.com.br");
            email.setPara("suporte@galliejoias.com.br");
            email.setMensagem(sb);
            //
            EmailUtil.enviar(email);
        }
    }

    public static void notificarDuvida(final Duvidas d) {
        if (d != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Notificação de dúvida:");
            sb.append("<br/>");
            sb.append("Nome: ").append(d.getNome());
            sb.append("<br/>");
            sb.append("Email:").append(d.getEmail());
            sb.append("<br/>");
            sb.append("Telefone:").append(d.getTelefone());
            sb.append("<br/>");
            sb.append("<br/>");
            sb.append("Mensagem:");
            sb.append("<br/>");
            sb.append(d.getMensagem());
            sb.append("<br/>");
            sb.append("Responda esta o mais breve possível.");
            //
            final Email email = new Email();
            email.setAssunto("Notificação de dúvida");
            email.setDe("suporte@galliejoias.com.br");
            email.setPara("suporte@galliejoias.com.br");
            email.setMensagem(sb);
            //
            EmailUtil.enviar(email);
        }
    }
}
