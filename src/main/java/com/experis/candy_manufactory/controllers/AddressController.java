package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.AddressRepository;
import com.experis.candy_manufactory.Repositories.ManagerRepository;
import com.experis.candy_manufactory.models.Address;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/address")

@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public ResponseEntity<CommonResponse> addAddress(@RequestBody Address addressToAdd){

        CommonResponse commonResponse = new CommonResponse();

        addressToAdd = addressRepository.save(addressToAdd);

        commonResponse.data = addressToAdd;
        commonResponse.message = "The address in: " + addressToAdd.getCity() + " has been added.";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);


    }
}
