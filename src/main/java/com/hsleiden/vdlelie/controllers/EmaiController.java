package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.services.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmaiController {

    private final EmailService emailService;

    public EmaiController(EmailService emailService){
        this.emailService = emailService;
    }


    @PostMapping("/email")
//    @PreAuthorize("HasAnyRole('USER', 'ADMIN')")
    public void email(@RequestParam String amount, @RequestParam String name){
        EmailService emailService = new EmailService();
        emailService.email(amount, name);
    }
}
