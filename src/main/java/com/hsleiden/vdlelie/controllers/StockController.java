package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Stock;
import com.hsleiden.vdlelie.services.StockService;
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
    public Stock saveStock(Stock stock){
        return stockService.save(stock);
    }

    @GetMapping("/stock")
    public List<Stock> getAllStocks(){
        return stockService.findAll();
    }

    @GetMapping("/stock/{id}")
    public Optional<Stock> getStockById(@PathVariable String id){
        return stockService.findById(id);
    }

    @DeleteMapping("/stock/{id}")
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
