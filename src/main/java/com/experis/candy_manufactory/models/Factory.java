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
    private int areaSize;
}
