package com.experis.candy_manufactory.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private int sizeOfArea;


    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager; //manager entity
    

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Address address; //address entity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getSizeOfArea() {
        return sizeOfArea;
    }

    public void setSizeOfArea(int sizeArea) {
        this.sizeOfArea = sizeArea;
    }
}
