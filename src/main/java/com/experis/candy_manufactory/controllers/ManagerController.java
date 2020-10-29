package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.ManagerRepository;
import com.experis.candy_manufactory.models.Manager;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/manager")

@RestController
public class ManagerController {

    @Autowired
    ManagerRepository managerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> addManager(@RequestBody Manager managerToAdd){
        CommonResponse commonResponse = new CommonResponse();
        managerToAdd = managerRepository.save(managerToAdd);

        commonResponse.data = managerToAdd;
        commonResponse.message = "Oompalompa manager: " +  managerToAdd.getFirstName() +
                " was added with id: " + managerToAdd.getId();

        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

}
