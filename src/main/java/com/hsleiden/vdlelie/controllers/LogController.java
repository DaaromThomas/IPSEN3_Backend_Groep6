package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Log;
import com.hsleiden.vdlelie.model.Packaging;
import com.hsleiden.vdlelie.model.Product;
import com.hsleiden.vdlelie.services.AccountService;
import com.hsleiden.vdlelie.services.LogService;
import com.hsleiden.vdlelie.services.PackagingService;
import com.hsleiden.vdlelie.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


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

    @PatchMapping("/logs")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Log> revertLog(@RequestBody Map<String, String> requestParams) {
        String logId = requestParams.get("logId");
        String packagingId = requestParams.get("packagingId");
        String productId = requestParams.get("productId");
        int packagingAmount = Integer.parseInt(requestParams.get("packagingAmount"));

        Log log = null;
        Packaging packaging = null;
        Product product = null;

        if (this.logService.findById(logId).isPresent()){
            log = this.logService.findById(logId).get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (this.packagingService.findById(packagingId).isPresent()){
            packaging = this.packagingService.findById(packagingId).get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (this.productService.findById(productId).isPresent()){
            product = this.productService.findById(productId).get();
        } else {
            return ResponseEntity.notFound().build();
        }

        packaging.setAmountinstock(packaging.getAmountinstock() + packagingAmount);
        this.packagingService.save(packaging);

        product.setPacked(false);
        this.productService.save(product);

        return ResponseEntity.ok(log);
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
