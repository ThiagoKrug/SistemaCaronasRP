/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Usuario
 */
public class Mail {
    
    private Session getSession() {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable","true" );
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true" );
        Authenticator auth = new SMTPAuthenticator("syscaruni@gmail.com", 
                "krug_viado");
        Session session = Session.getInstance(props, auth);
        return session;
    }
    
    
    private void sendMail(String to, Session session, String message) {
        try {
            
            Message msg = new MimeMessage(session);
            
              // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("syscaruni@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Notificação de Viagem - Unipampa");
            msg.setContent(message, "text/html; charset=utf-8");
              // -- Set some other header information --
            msg.setHeader("Unipampa", "Gerenciamento de Frota" );
            msg.setSentDate(new Date());
            
              // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent to"+to+" OK." );
            
            
        }
        catch (javax.mail.AuthenticationFailedException e) {
            
            e.printStackTrace();
        }
        catch (javax.mail.MessagingException m) {
            m.printStackTrace();
        }
        
        
    }
    
    public void sendmail(String to) {
        String html = "<html>"
                + "<body>"
                + "<h1>Meus Parasitas!</h1>"
                + "<h2>Sua viagem foi confirmada!</h2>"
                + "</body>"
                + "</html>";
        this.sendMail(to, this.getSession(), html);
    }
    
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        private String user;
        private String psswd;
        
        public SMTPAuthenticator(String user, String psswd) {
            super();
            this.user = user;
            this.psswd = psswd;
        }
        @Override
        public PasswordAuthentication getPasswordAuthentication() {                             // specify your password here
            return new PasswordAuthentication(user, psswd);
        }
  }
}
