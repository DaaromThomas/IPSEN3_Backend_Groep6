package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.ProductRepository;
import com.hsleiden.vdlelie.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public int setIsPackedForProduct(boolean isPacked, int productNumber){
        return this.productRepository.setIsPackedForProduct(isPacked, productNumber);
    }

    @Override
    public Optional<Product> findByProductNumber(int productnumber) { return productRepository.findByProductnumber(productnumber);}
    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
