package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.FactoryRepository;
import com.experis.candy_manufactory.models.Factory;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/v1/factory")

@RestController
public class FactoryController {

    @Autowired
    FactoryRepository factoryRepository;

    @PostMapping
    private ResponseEntity<CommonResponse> addFactory(@RequestBody Factory factoryToAdd) {
        CommonResponse commonResponse = new CommonResponse();

        factoryToAdd = factoryRepository.save(factoryToAdd);

        commonResponse.data = factoryToAdd;
        commonResponse.message = "Factory with name: " + factoryToAdd.getFactoryName() +
                " has been added with id: " + factoryToAdd.getId();

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
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

    @GetMapping
    private ResponseEntity<CommonResponse> getAllFactories(){
        CommonResponse commonResponse = new CommonResponse();
        List<Factory> factories = factoryRepository.findAll();
        commonResponse.data = factories;
        commonResponse.message = "List of all existing factories in this database.";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> updateFactoryById ( @RequestBody Factory factoryToUpdate,
                                                              @PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(factoryRepository.existsById(id)){

            Optional<Factory> factoryToUpdateRepository = factoryRepository.findById(id);
            Factory factory =factoryToUpdateRepository.get();

            if (factoryToUpdate.getFactoryName() != null){
                factory.setFactoryName(factoryToUpdate.getFactoryName());
            }
            if(factoryToUpdate.getSizeOfArea() != 0){
                factory.setSizeOfArea(factoryToUpdate.getSizeOfArea());
            }
            factoryRepository.save(factory);

            commonResponse.data = factory;
            commonResponse.message = "Factory record with id: " + id + " has been updated.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Factory record with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @RequestMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> deleteFactoryById(@PathVariable ("id") Long id) {
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(factoryRepository.existsById(id)){
            commonResponse.data = factoryRepository.findById(id);
            factoryRepository.deleteById(id);
            commonResponse.message = "The factory with id: " + id + " has been deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Factory with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

}
