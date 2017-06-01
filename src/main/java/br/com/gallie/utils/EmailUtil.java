/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.utils;

import br.com.gallie.classes.Email;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author operador
 */
public class EmailUtil {

    final static String username = "evandroerh@gmail.com";

    final static String password = "252960014621";

    final static Properties props = new Properties();

    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public static void main(String[] args) {
        System.out.println("Teste");
        enviar(null);
    }

    public static boolean enviar(final Email email) {
        try {
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {

                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getDe()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getPara()));
            message.setSubject(email.getAssunto());
            message.setText(email.getMensagem().toString());

            Transport.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
