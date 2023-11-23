package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Customer;
import com.hsleiden.vdlelie.model.Order;
import com.hsleiden.vdlelie.services.CustomerService;
import com.hsleiden.vdlelie.services.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class OrderController
{
    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public Order saveOrder(@RequestParam String name, @RequestParam String customerId){
        Customer customer = null;
        if (customerService.findById(customerId).isPresent()){
            customer = customerService.findById(customerId).get();
        }
        else {
            return null;
        }
        Order order = new Order(name, customer);
        return orderService.save(order);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<Order> getOrderById(@PathVariable String id){
        return orderService.findById(id);
    }

    @DeleteMapping("/orders/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable String id){
        if (orderService.findById(id).isPresent()){
            Order order = orderService.findById(id).get();
            orderService.delete(order);
        }
        else {
            return;
        }
    }
}
