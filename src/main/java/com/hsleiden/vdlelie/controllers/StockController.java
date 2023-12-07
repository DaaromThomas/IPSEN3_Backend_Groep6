package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Stock;
import com.hsleiden.vdlelie.services.StockService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StockController
{
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public Stock saveStock(Stock stock){
        return stockService.save(stock);
    }

    @GetMapping("/stock")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Stock> getAllStocks(){
        return stockService.findAll();
    }

    @GetMapping("/stock/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<Stock> getStockById(@PathVariable String id){
        return stockService.findById(id);
    }

    @DeleteMapping("/stock/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStock(@PathVariable String id){
        if (stockService.findById(id).isPresent()){
            Stock stock = stockService.findById(id).get();
            stockService.delete(stock);
        }
        else {
            return;
        }
    }
}
