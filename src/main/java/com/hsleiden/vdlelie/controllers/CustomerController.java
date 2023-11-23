package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.dao.CustomerRepository;
import com.hsleiden.vdlelie.dao.PackagingRepository;
import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Customer;
import com.hsleiden.vdlelie.model.Packaging;
import com.hsleiden.vdlelie.services.CustomerService;
import com.hsleiden.vdlelie.services.PackagingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController
{
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final PackagingService packagingService;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, PackagingService packagingService) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.packagingService = packagingService;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Customer saveCustomer(@RequestParam int customerNumber, @RequestParam String name, @RequestParam String address, @RequestParam String phonenumber, @RequestParam String email, @RequestParam(required = false) String preferredPackageId){
        Packaging preferredPackage = null;
        if (!preferredPackageId.isBlank()){
            if (packagingService.findById(preferredPackageId).isPresent()){
                preferredPackage = packagingService.findById(preferredPackageId).get();
            }
        }
        Customer customer = new Customer(customerNumber, name, address, phonenumber, email, preferredPackage);
        return customerService.save(customer);
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/customers/name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Customer getAccountByName(@RequestParam String name) {
        if (customerRepository.findByName(name).isPresent()) {
            return customerRepository.findByName(name).get();
        } else {
            return null;
        }
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Customer getCustomerById(@PathVariable String id){
        Optional<Customer> possibleCustomer = customerService.findById(id);

        if (possibleCustomer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        return possibleCustomer.get();
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(@PathVariable String id){
        if (customerService.findById(id).isPresent()){
            Customer customer = customerService.findById(id).get();
            customerService.delete(customer);
        }
    }

    @PatchMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void updateCustomer(@PathVariable String id, @RequestParam(required = false) String address, @RequestParam(required = false) String name, @RequestParam(required = false) String phonenumber, @RequestParam(required = false) String email){

        Optional<Customer> possibleCustomer = customerService.findById(id);

        if (possibleCustomer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        Customer customer = possibleCustomer.get();

        if (address != null){
            customer.setAddress(address);
        }

        if (name != null){
            customer.setName(name);
        }

        if (phonenumber != null){
            customer.setPhonenumber(phonenumber);
        }

        if (email != null){
            customer.setEmail(email);
        }

        customerService.save(customer);
    }

}
