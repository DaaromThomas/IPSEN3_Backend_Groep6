package com.hsleiden.vdlelie.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "log")
public class Log
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;
    @OneToOne
    @JoinColumn(name = "product")
    private Product product;
    @OneToOne
    @JoinColumn(name = "packaging")
    private Packaging packaging;
    private int packagingamount;
    @Column(name = "dateoflog", updatable = false)
    @CreationTimestamp
    private LocalDate date;
    @Column(name = "timeoflog", updatable = false)
    @CreationTimestamp
    private LocalTime time;
    @Column(name = "reverted")
    private boolean reverted;

    public Log(Account account, Product product, Packaging packaging, int packagingamount, LocalDate date, LocalTime time) {
        this.account = account;
        this.product = product;
        this.packaging = packaging;
        this.packagingamount = packagingamount;
        this.date = date;
        this.time = time;
        this.reverted = false;
    }

    public Log(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public int getPackagingamount() {
        return packagingamount;
    }

    public void setPackagingamount(int packagingamount) {
        this.packagingamount = packagingamount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isReverted() {
        return reverted;
    }

    public void setReverted(boolean reverted) {
        this.reverted = reverted;
    }
}
