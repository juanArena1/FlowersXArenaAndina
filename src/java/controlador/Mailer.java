/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Aprendiz
 */
public class Mailer {

    Session session;
    //Credenciales de la cuenta de correo
    final String user = "santamartaflowers@gmail.com";//cambiará en consecuencia al servidor utilizado
    final String pass = "flowersx";
    private boolean fueEnviado = false;

    public Mailer(Session session) {
        this.session = session;
    }

    public Mailer() {
    }

    public boolean isFueEnviado() {
        return fueEnviado;
    }

    public void setFueEnviado(boolean fueEnviado) {
        this.fueEnviado = fueEnviado;
    }

    public void configurar() {
        //Configurar el servidor de correo
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        //Obtener el objeto de sesión
        this.session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
    }

    public void enviarMensaje(String destinatario, String asunto, String mensaje) throws UnsupportedEncodingException {
        this.fueEnviado = false;
        //Estructura del mensaje en HTML
        String nuevoMensaje = "<style>\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Cookie&display=swap');\n"
                + "	</style>\n"
                + "	<div style=\"background-image: url('https://i.imgur.com/GkfVLLj.jpg'); font-family: 'Open Sans', sans-serif; position: relative; text-align: center; color: #363636; width: 1600px; height: 1002px; background-size: cover\">\n"
                + "		<div style=\"font-family: 'Open Sans', sans-serif; word-wrap: break-word; padding-top: 5%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); max-width: 128em; max-height: 80em; z-index: 1000;\">\n"
                + "			<h1>" + asunto + "</h1>\n"
                + "			<p style=\"text-align: justify;\">" + mensaje + "</p>\n"
                + "			<h1 style=\"font-size: 3em; font-family: 'Cookie', cursive; color: #CFB0FF\">¡Gracias por confiar en nosotros!</h1>\n"
                + "		</div>\n"
                + "	</div>";
        try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");
            //Enviar mensaje
            Transport.send(message);
            System.out.println("Enviado");
            this.fueEnviado = true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarMensajeConUnAdjunto(String destinatario, String asunto, String mensaje, String ruta, String nombre) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<style>\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Cookie&display=swap');\n"
                + "	</style>\n"
                + "	<div style=\"background-image: url('https://i.imgur.com/GkfVLLj.jpg'); font-family: 'Open Sans', sans-serif; position: relative; text-align: center; color: #363636; width: 1600px; height: 1002px; background-size: cover\">\n"
                + "		<div style=\"font-family: 'Open Sans', sans-serif; word-wrap: break-word; padding-top: 5%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); max-width: 128em; max-height: 80em; z-index: 1000;\">\n"
                + "			<h1>" + asunto + "</h1>\n"
                + "			<p style=\"text-align: justify;\">" + mensaje + "</p>\n"
                + "			<h1 style=\"font-size: 3em; font-family: 'Cookie', cursive; color: #CFB0FF\">¡Gracias por confiar en nosotros!</h1>\n"
                + "		</div>\n"
                + "	</div>";
        try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarMensajeConDosAdjuntos(String destinatario, String asunto, String mensaje, String ruta, String nombre, String ruta1, String nombre1) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<style>\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Cookie&display=swap');\n"
                + "	</style>\n"
                + "	<div style=\"background-image: url('https://i.imgur.com/GkfVLLj.jpg'); font-family: 'Open Sans', sans-serif; position: relative; text-align: center; color: #363636; width: 1600px; height: 1002px; background-size: cover\">\n"
                + "		<div style=\"font-family: 'Open Sans', sans-serif; word-wrap: break-word; padding-top: 5%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); max-width: 128em; max-height: 80em; z-index: 1000;\">\n"
                + "			<h1>" + asunto + "</h1>\n"
                + "			<p style=\"text-align: justify;\">" + mensaje + "</p>\n"
                + "			<h1 style=\"font-size: 3em; font-family: 'Cookie', cursive; color: #CFB0FF\">¡Gracias por confiar en nosotros!</h1>\n"
                + "		</div>\n"
                + "	</div>";
        try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            BodyPart adjunto1 = new MimeBodyPart();
            adjunto1.setDataHandler(new DataHandler(new FileDataSource(ruta1)));
            adjunto1.setFileName(nombre1);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            multiparte.addBodyPart(adjunto1);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarMensajeConTresAdjuntos(String destinatario, String asunto, String mensaje, String ruta, String nombre, String ruta1, String nombre1, String ruta2, String nombre2) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<style>\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');\n"
                + "		@import url('https://fonts.googleapis.com/css?family=Cookie&display=swap');\n"
                + "	</style>\n"
                + "	<div style=\"background-image: url('https://i.imgur.com/GkfVLLj.jpg'); font-family: 'Open Sans', sans-serif; position: relative; text-align: center; color: #363636; width: 1600px; height: 1002px; background-size: cover\">\n"
                + "		<div style=\"font-family: 'Open Sans', sans-serif; word-wrap: break-word; padding-top: 5%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); max-width: 128em; max-height: 80em; z-index: 1000;\">\n"
                + "			<h1>" + asunto + "</h1>\n"
                + "			<p style=\"text-align: justify;\">" + mensaje + "</p>\n"
                + "			<h1 style=\"font-size: 3em; font-family: 'Cookie', cursive; color: #CFB0FF\">¡Gracias por confiar en nosotros!</h1>\n"
                + "		</div>\n"
                + "	</div>";
        try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            BodyPart adjunto1 = new MimeBodyPart();
            adjunto1.setDataHandler(new DataHandler(new FileDataSource(ruta1)));
            adjunto1.setFileName(nombre1);
            BodyPart adjunto2 = new MimeBodyPart();
            adjunto2.setDataHandler(new DataHandler(new FileDataSource(ruta2)));
            adjunto2.setFileName(nombre2);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            multiparte.addBodyPart(adjunto1);
            multiparte.addBodyPart(adjunto2);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
