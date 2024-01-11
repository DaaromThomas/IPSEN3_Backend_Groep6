package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.services.EmailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }


    @PostMapping("/email/lowonstock")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void email(@RequestParam String amount, @RequestParam String name, @RequestParam String minAmount){
        emailService.emailStockNotification(amount, name, minAmount);
    }

}
