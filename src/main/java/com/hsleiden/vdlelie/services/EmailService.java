package com.hsleiden.vdlelie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    JavaMailSender mailSender = getJavaMailSender();
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("draakje11111@gmail.com");
        mailSender.setPassword("jkww pvwu suku pdoe ");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }


    public void email(String amount, String name){
        sendEmail(bodyCreator(amount, name), "Notification: Stock running low", "r.j.colijn@hotmail.com");
    }

    String bodyCreator(String amount, String name){
        String body = "Notification" + "\n" +
                "" + "\n" +
                "Stock " + name + " is running low on stock" + "\n" +
                "There are only " + amount + " left in the current stock";
        return body;
    }

     void sendEmail(String body, String subject, String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
