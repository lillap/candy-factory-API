package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.CandyRepository;
import com.experis.candy_manufactory.models.Candy;
import com.experis.candy_manufactory.models.CandyType;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/candy")

public class CandyController {

    @Autowired
    private CandyRepository candyRepository;

    @PostMapping
    private ResponseEntity<CommonResponse> addCandy(@RequestBody Candy candyToAdd){
        CommonResponse commonResponse = new CommonResponse();

        candyToAdd = candyRepository.save(candyToAdd);

        commonResponse.data = candyToAdd;
        commonResponse.message = "Candy with name: " + candyToAdd.getName()
                + " has been added with id: " + candyToAdd.getId();

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> getCandyById(@PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (candyRepository.existsById(id)) {
            commonResponse.data = candyRepository.findById(id);
            commonResponse.message = "Candy with id: " + id +  " was found.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.data = null;
            commonResponse.message = "The candy with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @GetMapping
    private ResponseEntity<CommonResponse> getAllCandyRecords(){
        CommonResponse commonResponse = new CommonResponse();
        List<Candy> candies = candyRepository.findAll();
        commonResponse.data = candies;
        commonResponse.message = "List of all managers working for Willy Wonka.";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/grouped")
    private ResponseEntity<CommonResponse> sortCandyRecordsByType(){

        CommonResponse commonResponse = new CommonResponse();

        HashMap<Enum, Integer> mapOfCandyRecordsByType = new HashMap<>();
        for(CandyType candyType : CandyType.values()){
            int amount = candyRepository.countByCandyType(candyType);
            mapOfCandyRecordsByType.put(candyType,amount);
        }

        commonResponse.data = mapOfCandyRecordsByType;
        commonResponse.message = "Amount of each candy type in the factory. ";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> updateCandyRecord(@RequestBody Candy candyToUpdate,
                                                             @PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (candyRepository.existsById(id)){
            Optional<Candy> candyToUpdateRepository = candyRepository.findById(id);
            Candy candy = candyToUpdateRepository.get();

            if(candyToUpdate.getName() != null){
                candy.setName(candyToUpdate.getName());
            }
            if(candyToUpdate.getCandyType() != null) {
                candy.setCandyType(candyToUpdate.getCandyType());
            }
            if(candyToUpdate.getCostPerUnit() != 0) {
                candy.setCostPerUnit(candyToUpdate.getCostPerUnit());
            }
            if(candyToUpdate.getWeightPerUnit() != 0){
                candy.setWeightPerUnit(candyToUpdate.getWeightPerUnit());
            }
            if(candyToUpdate.getFactories() != null){
                candy.setFactories(candyToUpdate.getFactories());
            }
            candyRepository.save(candy);

            commonResponse.data = candy;
            commonResponse.message = "Record of candy with id: " +  id + " has been updated.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Record of candy with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> deleteCandyRecordById(@PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(candyRepository.existsById(id)){

            commonResponse.data = candyRepository.findById(id);
            candyRepository.deleteById(id);
            commonResponse.message = "Record of candy with id: " + id + " has been deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Record of candy with id " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }
}
