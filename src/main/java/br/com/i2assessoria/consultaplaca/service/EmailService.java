package br.com.i2assessoria.consultaplaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void send(String messagem, String email) throws MailException, InterruptedException{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(messagem);
        message.setTo(email);
        message.setFrom("gabrieltanner@gmail.com");
        javaMailSender.send(message);
    }

    public void sendEmailSenha(String senha, String email){
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Senha: " + senha);
            send(stringBuilder.toString(), email);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
