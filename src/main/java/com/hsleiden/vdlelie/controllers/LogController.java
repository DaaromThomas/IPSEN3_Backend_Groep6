package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Log;
import com.hsleiden.vdlelie.model.Packaging;
import com.hsleiden.vdlelie.model.Product;
import com.hsleiden.vdlelie.services.AccountService;
import com.hsleiden.vdlelie.services.LogService;
import com.hsleiden.vdlelie.services.PackagingService;
import com.hsleiden.vdlelie.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
public class LogController
{
    private final LogService logService;
    private final AccountService accountService;
    private final ProductService productService;
    private final PackagingService packagingService;

    public LogController(LogService logService, AccountService accountService, ProductService productService, PackagingService packagingService) {
        this.logService = logService;
        this.accountService = accountService;
        this.productService = productService;
        this.packagingService = packagingService;
    }

    @PostMapping("/logs")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Log saveLog(@RequestParam String accountId, @RequestParam String productId, @RequestParam String packagingId, @RequestParam int packagingamount){
        Account account = null;
        Product product = null;
        Packaging packaging = null;
        if (accountService.findById(accountId).isPresent()){
            account = accountService.findById(accountId).get();
        }
        else {
            return null;
        }
        if (productService.findById(productId).isPresent()){
            product = productService.findById(productId).get();
        }
        else {
            return null;
        }
        if (packagingService.findById(packagingId).isPresent()){
            packaging = packagingService.findById(packagingId).get();
        }
        else {
            return null;
        }
        Log log = new Log(account, product, packaging, packagingamount,LocalDate.now(), LocalTime.now());
        return logService.save(log);
    }

    @GetMapping("/logs")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Log> getAllLogs(){
        return logService.findAll();
    }

    @GetMapping("/logs/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Log getLogById(@PathVariable String id){
        if (logService.findById(id).isPresent()){
            return logService.findById(id).get();
        }
        else {
            return null;
        }
    }

    @DeleteMapping("/logs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLog(@PathVariable String id){
        if (logService.findById(id).isPresent()){
            logService.delete(logService.findById(id).get());
        }
    }
}
