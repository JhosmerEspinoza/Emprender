/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Async
    public void enviarMail(String cuerpo, String titulo, String destinatario){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(destinatario);
        mail.setFrom("equipo.emprender.autenticacion@gmail.com");
        mail.setSubject(titulo);
        mail.setText(cuerpo);
        
        mailSender.send(mail); 
    }
    
    @Async
    public void enviarMailRegistro(String cuerpo, String titulo, String destinatario, String link){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(destinatario);
        mail.setFrom("equipo.emprender.autenticacion@gmail.com");
        mail.setSubject(titulo);
        mail.setText(cuerpo+" "+link);
        
        mailSender.send(mail);
        
    }
}
