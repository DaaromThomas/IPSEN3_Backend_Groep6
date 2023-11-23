package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Account;
import com.hsleiden.vdlelie.model.Packaging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagingRepository extends CrudRepository<Packaging, String>
{
    Optional<Packaging> findByName(String name);
}
