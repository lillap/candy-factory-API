package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.ManagerRepository;
import com.experis.candy_manufactory.models.Manager;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<CommonResponse> deleteManagerRecordById(@PathVariable("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(managerRepository.existsById(id)){

            commonResponse.data = managerRepository.findById(id);
            managerRepository.deleteById(id);
            commonResponse.message = "Manager record with id: " + id + " has been deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Manager record with id " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

}
