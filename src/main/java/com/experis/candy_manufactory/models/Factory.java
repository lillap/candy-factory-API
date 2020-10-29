package com.experis.candy_manufactory.models;

import javax.persistence.*;

@Entity
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String factoryName;

    @Column
    private int sizeArea;

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
