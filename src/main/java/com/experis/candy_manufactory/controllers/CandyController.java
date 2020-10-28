package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.CandyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/candy")

@RestController
public class CandyController {

    @Autowired
    private CandyRepository candyRepository;

   
}
