package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Account;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final AccountServiceImpl accountService;



    @Autowired
    public EmailService(TemplateEngine templateEngine, AccountServiceImpl accountService){
        this.templateEngine = templateEngine;
        this.accountService = accountService;
    }


    @Async
    public void emailStockNotification(String amount, String name, String minAmount){
        sendEmail(contextCreator(amount, name, minAmount), "Notification: Stock running low", peopleToSendTo());
    }

    public String[] peopleToSendTo(){
        List<Account> accounts = accountService.findAll();
        List<String> emailList = new ArrayList<String>();
        for(Account account : accounts){
            if (account.getEmail() != null && account.isNotification() == true){
                emailList.add(account.getEmail());
            }
        }
        String[] emailArray = emailList.toArray(new String[0]);
        return emailArray;
    }



    Context contextCreator(String amount, String name, String minAmount){
        Context context = new Context();
        context.setVariable("amount", "There is only " + amount + " left");
        context.setVariable("name", "The stock " + name + " is running low");
        context.setVariable("minAmount", "The minimum is " + minAmount);
        return context;
    }




     void sendEmail(Context context, String subject, String[] to){
         MimeMessage message = emailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
         try {
             helper.setTo(to);
             helper.setSubject(subject);
             String content = templateEngine.process("stocknotification", context);
             helper.setText(content, true);
             emailSender.send(message);
         } catch (MessagingException e){

         }

    }
}
