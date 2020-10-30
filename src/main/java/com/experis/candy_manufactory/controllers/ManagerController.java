package com.experis.candy_manufactory.controllers;

import com.experis.candy_manufactory.Repositories.ManagerRepository;
import com.experis.candy_manufactory.models.Candy;
import com.experis.candy_manufactory.models.Manager;
import com.experis.candy_manufactory.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/manager")
public class ManagerController {

    @Autowired
    ManagerRepository managerRepository;

    @PostMapping
    public ResponseEntity<CommonResponse> addManager(@RequestBody Manager managerToAdd){
        CommonResponse commonResponse = new CommonResponse();
        managerToAdd = managerRepository.save(managerToAdd);

        commonResponse.data = managerToAdd;
        commonResponse.message = "Oompalompa manager with name: " +  managerToAdd.getFirstName() +
                " has been added with id: " + managerToAdd.getId();

        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> getManagerById(@PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (managerRepository.existsById(id)) {
            commonResponse.data = managerRepository.findById(id);
            commonResponse.message = "Manager with id: " + id +  " was found.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.data = null;
            commonResponse.message = "Manager with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<CommonResponse> getAllManagerRecords(){
        CommonResponse commonResponse = new CommonResponse();
        List<Manager> managers = managerRepository.findAll();
        commonResponse.data = managers;
        commonResponse.message = "List of all existing managers in the database.";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> updateManagerRecord(@RequestBody Manager managerToUpdate,
                                                             @PathVariable ("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (managerRepository.existsById(id)){
            Optional<Manager> managerToUpdateRepository = managerRepository.findById(id);
            Manager manager = managerToUpdateRepository.get();

            if(managerToUpdate.getFirstName() != null){
                manager.setFirstName(managerToUpdate.getFirstName());
            }
            if(managerToUpdate.getLastName() != null){
                manager.setLastName(managerToUpdate.getLastName());
            }
            if(managerToUpdate.getAddress() != null){
                manager.setAddress(managerToUpdate.getAddress());
            }
            if(managerToUpdate.getFactory() != null){
                manager.setFactory(managerToUpdate.getFactory());
            }

            managerRepository.save(manager);

            commonResponse.data = manager;
            commonResponse.message = "Record of manager with id: " +  id + " has been updated.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Record of manager with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<CommonResponse> deleteManagerRecordById(@PathVariable("id") Long id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(managerRepository.existsById(id)){

            commonResponse.data = managerRepository.findById(id);
            managerRepository.deleteById(id);
            commonResponse.message = "Record of manager with id: " + id + " has been deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Record of manager with id " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

}
