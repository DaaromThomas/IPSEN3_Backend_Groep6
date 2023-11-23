package com.hsleiden.vdlelie.model;

import jakarta.persistence.*;

@Entity
public class Stock
{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int stocknumber;

    public Stock() {
        this.stocknumber = getRandomNumber(100, 1000);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(int stocknumber) {
        this.stocknumber = stocknumber;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
