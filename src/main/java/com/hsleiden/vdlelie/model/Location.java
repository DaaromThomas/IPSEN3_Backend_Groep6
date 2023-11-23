package com.hsleiden.vdlelie.model;

import jakarta.persistence.*;


@Entity
@Table(name="location")
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String address;
    @JoinColumn(name = "stock")
    @OneToOne
    private Stock stock;

    public Location(String address, Stock stock){
        this.address = address;
        this.stock = stock;
    }

    public Location(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
