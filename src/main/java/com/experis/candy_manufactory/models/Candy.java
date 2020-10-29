package com.experis.candy_manufactory.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Candy {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private CandyType candyType;

    @Column
    private double weightPerUnit;

    @Column
    private double costPerUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    public void setCandyType(CandyType candyType) {
        this.candyType = candyType;
    }

    public double getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(double weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }
}
