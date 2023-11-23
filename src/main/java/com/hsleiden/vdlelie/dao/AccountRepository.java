package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>
{

    Optional<Account> findByName(String username);
}

