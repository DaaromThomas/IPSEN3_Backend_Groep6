package com.hsleiden.vdlelie.dao;
import com.hsleiden.vdlelie.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>
{
    Optional<Product> findByProductnumber(int productnumber);

    @Modifying
    @Transactional
    @Query("update Product product set product.isPacked = :isPacked where product.productnumber = :productnumber")
    int setIsPackedForProduct(@Param("isPacked") boolean isPacked, @Param("productnumber") int productnumber);
}
