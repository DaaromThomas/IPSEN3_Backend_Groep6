package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.*;
import com.hsleiden.vdlelie.services.OrderService;
import com.hsleiden.vdlelie.services.PackagingService;
import com.hsleiden.vdlelie.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    private final ProductService productService;
    private final PackagingService packagingService;
    private final OrderService orderService;

    public ProductController(ProductService productService, PackagingService packagingService, OrderService orderService) {
        this.productService = productService;
        this.packagingService = packagingService;
        this.orderService = orderService;
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public Product saveProduct(@RequestParam String prefferedPackageId, @RequestParam String orderId, @RequestParam String name, @RequestParam int productnumber,@RequestParam String productType) {
        Packaging prefferedPackaging = null;
        Order order = null;
        if (packagingService.findById(prefferedPackageId).isPresent()){
            prefferedPackaging = packagingService.findById(prefferedPackageId).get();
        }
        else {
            return null;
        }
        if (orderService.findById(orderId).isPresent()){
            order = orderService.findById(orderId).get();
        }
        else {
            return null;
        }
        Product product = new Product(prefferedPackaging, order, name, productnumber, ProductType.valueOf(productType));
        return productService.save(product);
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Product getProductById(@PathVariable String id){
        if (productService.findById(id).isPresent()){
            return productService.findById(id).get();
        }
        else {
            return null;
        }
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable String id){
        if (productService.findById(id).isPresent()){
            Product product = productService.findById(id).get();
            productService.delete(product);
        }
    }
}
