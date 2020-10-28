package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.CandyRepository;
import com.experis.candy_manufactory.models.Candy;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/candy")

@RestController
public class CandyController {

    @Autowired
    private CandyRepository candyRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    private ResponseEntity<CommonResponse> addCandy(@RequestBody Candy candyToAdd){
        CommonResponse commonResponse = new CommonResponse();

        candyToAdd = candyRepository.save(candyToAdd);

        commonResponse.data = candyToAdd;
        commonResponse.message = "The candy: " + candyToAdd + " has been added!";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<CommonResponse> getCandyById(@PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (candyRepository.existsById(id)) {
            commonResponse.data = candyRepository.findById(id);
            commonResponse.message = "Candy with id: " + id +  " was found.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.data = null;
            commonResponse.message = "The candy was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ResponseEntity<CommonResponse> getAllCandyRecords(){
        CommonResponse commonResponse = new CommonResponse();
        List<Candy> candies = candyRepository.findAll();
        commonResponse.data = candies;
        commonResponse.message = "List of all existing candy records";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
