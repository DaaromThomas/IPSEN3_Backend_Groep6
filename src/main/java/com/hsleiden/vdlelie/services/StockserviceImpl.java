package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.StockRepository;
import com.hsleiden.vdlelie.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockserviceImpl implements StockService
{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Optional<Stock> findById(String id) {
        return stockRepository.findById(id);
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> findAll() {
        return (List<Stock>) stockRepository.findAll();
    }

    @Override
    public void delete(Stock stock) {
        stockRepository.delete(stock);
    }
}
