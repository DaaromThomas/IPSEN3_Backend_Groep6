package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StockService
{
    Optional<Stock> findById(String id);
    Stock save(Stock product);
    List<Stock> findAll();
    void delete(Stock Stock);
}
