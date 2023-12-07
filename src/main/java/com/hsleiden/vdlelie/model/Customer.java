package com.hsleiden.vdlelie.model;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int customernumber;
    private String name;
    private String address;
    @Column(nullable = true)
    private String phonenumber;
    @Column(nullable = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "preferredPackage")
    private Packaging preferredPackaging;


    public Customer(int customerNumber, String name, String address, String phonenumber, String email, Packaging preferredPackage) {
        this.customernumber = customerNumber;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.preferredPackaging = preferredPackage;
    }

    public Customer() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerNumber() {
        return customernumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customernumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Packaging getPreferredPackaging() {
        return preferredPackaging;
    }

    public void setPreferredPackaging(Packaging preferredPackaging) {
        this.preferredPackaging = preferredPackaging;
    }
}
