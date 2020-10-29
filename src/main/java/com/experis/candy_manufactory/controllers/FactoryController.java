package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.FactoryRepository;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/factory")

@RestController
public class FactoryController {

    @Autowired
    FactoryRepository factoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<CommonResponse> getFactoryById(@PathVariable("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(factoryRepository.existsById(id)){
            commonResponse.data = factoryRepository.findById(id);
            commonResponse.message = "Factory with id: " + id + " was found.";
            httpStatus = HttpStatus.OK
        } else {
            commonResponse.message = "Factory with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (commonResponse, httpStatus);
    }

}
