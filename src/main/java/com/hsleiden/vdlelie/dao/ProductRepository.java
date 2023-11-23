package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>
{

}
