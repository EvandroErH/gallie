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

    final static String username = "suporte@galliejoias.com.br";

    final static String password = "g4ll13j014s";

    final static Properties props = new Properties();

    static {
        props.setProperty("mail.smtp.host", "smtp.zoho.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug.auth", "true");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
    }

    public static void enviar(final Email email) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
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
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
