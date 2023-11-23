package com.hsleiden.vdlelie.dao;

import com.hsleiden.vdlelie.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
