package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>
{
    Optional<Customer> findByName(String username);
}
