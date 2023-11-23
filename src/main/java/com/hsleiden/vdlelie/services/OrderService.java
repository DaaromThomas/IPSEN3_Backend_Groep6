package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService
{
    Optional<Order> findById(String id);
    Order save(Order order);
    List<Order> findAll();
    void delete(Order order);
}
