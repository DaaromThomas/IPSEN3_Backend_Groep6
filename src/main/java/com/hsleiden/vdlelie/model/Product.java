package com.hsleiden.vdlelie.model;


import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JoinColumn(name = "prefferedpackage")
    @ManyToOne
    private Packaging prefferedpackage;
    @JoinColumn(name = "order_")
    @ManyToOne
    private Order order;
    private String name;
    private int productnumber;
    private ProductType producttype;
    private boolean isPacked;

    public Product(Packaging prefferedPackage, Order order, String name, int productnumber, ProductType productType) {
        this.prefferedpackage = prefferedPackage;
        this.order = order;
        this.name = name;
        this.productnumber = productnumber;
        this.producttype = productType;
        isPacked = false;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Packaging getPrefferedpackage() {
        return prefferedpackage;
    }

    public void setPrefferedpackage(Packaging prefferedpackage) {
        this.prefferedpackage = prefferedpackage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(int productnumber) {
        this.productnumber = productnumber;
    }

    public ProductType getProducttype() {
        return producttype;
    }

    public void setProducttype(ProductType producttype) {
        this.producttype = producttype;
    }

    public boolean isPacked() {
        return isPacked;
    }

    public void setPacked(boolean packed) {
        isPacked = packed;
    }
}
