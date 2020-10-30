package com.experis.candy_manufactory.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToMany
    @JoinTable(
            name = "candy_factory",
            joinColumns =
                    { @JoinColumn(name = "factory_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "candy_id")}
    )
    List<Candy> candies;

    @JsonGetter("candies")
    public List<String> candies(){
        return candies.stream()
                .map(candy ->{
                    return "/api/v1/candies/" + candy.getId();
                }).collect(Collectors.toList());
    }

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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void setCandies(List<Candy> candies) {
        this.candies = candies;
    }
}
