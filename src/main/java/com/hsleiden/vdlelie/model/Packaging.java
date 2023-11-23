package com.hsleiden.vdlelie.model;

import jakarta.persistence.*;

@Entity
@Table(name="packaging")
public class Packaging
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JoinColumn(name = "stock")
    @ManyToOne
    private Stock stock;
    private int amountinstock;
    private int minamount;
    private String name;
    private String packaginggroup;

    public Packaging(Stock stock, int amountinstock, int minAmount, String name, String packagingGroup) {
        this.stock = stock;
        this.amountinstock = amountinstock;
        this.minamount = minAmount;
        this.name = name;
        this.packaginggroup = packagingGroup;
    }

    public Packaging() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getAmountinstock() {
        return amountinstock;
    }

    public void setAmountinstock(int amountinstock) {
        this.amountinstock = amountinstock;
    }

    public int getMinAmount() {
        return minamount;
    }

    public void setMinAmount(int minAmount) {
        this.minamount = minAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackagingGroup() {
        return packaginggroup;
    }

    public void setPackagingGroup(String packagingGroup) {
        this.packaginggroup = packagingGroup;
    }
}
