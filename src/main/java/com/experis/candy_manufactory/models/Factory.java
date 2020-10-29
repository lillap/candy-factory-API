package com.experis.candy_manufactory.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private int sizeArea;


    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager; //manager entity

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Address address; //address entity

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getSizeArea() {
        return sizeArea;
    }

    public void setSizeArea(int sizeArea) {
        this.sizeArea = sizeArea;
    }
}
