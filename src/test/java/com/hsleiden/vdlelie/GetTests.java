package com.hsleiden.vdlelie;

import com.hsleiden.vdlelie.dao.CustomerRepository;
import com.hsleiden.vdlelie.model.Customer;
import com.hsleiden.vdlelie.services.CustomerService;
import com.hsleiden.vdlelie.services.CustomerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class GetTests
{
   @Mock
   private CustomerRepository customerRepository;

   private CustomerService SUT;

   @BeforeEach
   public void setup(){this.SUT = new CustomerServiceImpl(customerRepository);}

    @Test
    public void should_return_all_customers(){
        List<Customer> dummyCustomers = Arrays.asList(
                new Customer(1, "Thomas", "Rotterdam", "810576107643", "thomas@vdlelie.nl")
        );

       when(this.customerRepository.findAll()).thenReturn(dummyCustomers);
       List<Customer> actualCustomers = SUT.findAll();

       assertThat(actualCustomers.size(), is(dummyCustomers.size()));
   }

    @Test
    public void should_return_the_right_customer_by_id(){
        Customer dummyCustomer = new Customer(1, "Thomas", "Rotterdam", "810576107643", "thomas@vdlelie.nl");
        String id = dummyCustomer.getId();

        when(customerRepository.findById(id)).thenReturn(Optional.of(dummyCustomer));

        Optional<Customer> actualCustomer = SUT.findById(id);

        assertThat(actualCustomer.get().getName(), is(dummyCustomer.getName()));
    }}
