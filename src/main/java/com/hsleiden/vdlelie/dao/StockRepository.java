package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, String>
{

}
