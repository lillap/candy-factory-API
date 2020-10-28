package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.CandyRepository;
import com.experis.candy_manufactory.models.Candy;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
