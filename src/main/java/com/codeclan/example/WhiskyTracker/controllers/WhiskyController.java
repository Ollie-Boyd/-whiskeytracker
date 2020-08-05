package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

//    @GetMapping(value = "/whiskys")
//    public ResponseEntity<List<Whisky>> getAll(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }


    @GetMapping(value = "/whiskys/year")
    public ResponseEntity<List<Whisky>> getByYear(@RequestParam(name = "year") int year){
        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskys")
    public ResponseEntity<List<Whisky>> getByCondition(@RequestParam(name = "year", required = false) Integer year,
                                                       @RequestParam(name = "age", required = false) Integer age,
                                                       @RequestParam(name = "distillery", required = false) Long distilleryId){
        if (year!=null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        } else if ( distilleryId!=null && age!=null){
            Optional<Distillery> distillery = distilleryRepository.findById(distilleryId);
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery.get(), age), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }


    @GetMapping(value = "/whiskys/distilleries")
    public ResponseEntity<List<Whisky>> getByDistilleryAndAge(@RequestParam(name = "distillery") Long id,
                                                              @RequestParam(name = "age") Integer age){
        Optional<Distillery> distillery = distilleryRepository.findById(id);
        return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery.get(), age), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskys/regions")
    public ResponseEntity<List<Whisky>> getByDistilleryRegion(@RequestParam(name = "region") String region){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
    }



}
