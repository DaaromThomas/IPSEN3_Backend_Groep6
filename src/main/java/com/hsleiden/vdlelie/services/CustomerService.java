package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService
{
    Optional<Customer> findById(String id);
    Customer save(Customer customer);
    List<Customer> findAll();
    void delete(Customer customer);
}
