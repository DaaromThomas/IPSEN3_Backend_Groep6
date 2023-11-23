package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService
{
    Optional<Account> findById(String id);
    Account save(Account account);

    List<Account> findAll();
    void delete(Account account);



}
