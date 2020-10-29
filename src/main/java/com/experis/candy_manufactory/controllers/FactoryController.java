package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.FactoryRepository;
import com.experis.candy_manufactory.models.Factory;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RequestMapping(value = "/api/v1/factory")

@RestController
public class FactoryController {

    @Autowired
    FactoryRepository factoryRepository;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<CommonResponse> addFactory(@RequestBody Factory factoryToAdd) {
        CommonResponse commonResponse = new CommonResponse();

        factoryToAdd = factoryRepository.save(factoryToAdd);

        commonResponse.data = factoryToAdd;
        commonResponse.message = "Factory with name: " + factoryToAdd.getFactoryName() +
                " and id: " + factoryToAdd.getId() + " has been added.";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<CommonResponse> getFactoryById( @PathVariable("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(factoryRepository.existsById(id)){
            commonResponse.data = factoryRepository.findById(id);
            commonResponse.message = "Factory with id: " + id + " was found.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Factory with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

}
