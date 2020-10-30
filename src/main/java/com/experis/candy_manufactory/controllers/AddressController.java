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
        commonResponse.message = "The address in " + addressToAdd.getCountry() + " has been added.";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAddressById (@PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(addressRepository.existsById(id)){
           commonResponse.data = addressRepository.findById(id).get();
            commonResponse.message = "The address with id: " + id + " was found.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "The address with id: " +  id +  " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }
}
