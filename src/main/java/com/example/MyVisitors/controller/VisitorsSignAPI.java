package com.example.MyVisitors.controller;

import com.example.MyVisitors.model.VisitorsSign;
import com.example.MyVisitors.repository.VisitorsSignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class VisitorsSignAPI {
    @Autowired
    private VisitorsSignRepo visitorsSignRepo;


    @PostMapping("add/visitorsSign")
    public ResponseEntity<?>addVisitorsSign(@RequestBody VisitorsSign visitorsSign){
        try {
            VisitorsSign visitorsSign1 = visitorsSignRepo.save(visitorsSign);
            return new ResponseEntity<>(visitorsSign1, HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/visitorsSign")
    public ResponseEntity<?>get(){
        try {
            List<VisitorsSign>visitorsSignList = visitorsSignRepo.findAll();
            if (visitorsSignList.isEmpty()) {

                return  new ResponseEntity<>("Visitors is not found", HttpStatus.NOT_FOUND);
            }else {
                return  new ResponseEntity<>(visitorsSignList, HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("Delete/visitors{Id}")
    public ResponseEntity<?> deleteVisitorsSign(@PathVariable int Id){
        try {
            visitorsSignRepo.deleteById(Id);
            return  new ResponseEntity<>("VisitorSign delete successfully",HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("Update/visitorsSign{Id}")
    public ResponseEntity<?>updateVisitorsSign(@PathVariable int Id,@RequestBody VisitorsSign updateVisitorsSign){
        Optional<VisitorsSign> visitorsSignOptional = visitorsSignRepo.findById(Id);
        if (visitorsSignOptional.isPresent()){
            VisitorsSign visitorsSign = visitorsSignOptional.get();
            visitorsSign.setId(updateVisitorsSign.getId());
            visitorsSign.setFirstName(updateVisitorsSign.getFirstName());
            visitorsSignRepo.save(visitorsSign);
            return  new ResponseEntity<>(visitorsSign,HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("visitorsSign is not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getVisitorsSign{Id}")
    public ResponseEntity<?> getId(@PathVariable int Id){
        try {
            Optional<VisitorsSign>optionalVisitorsSign = visitorsSignRepo.findById(Id);
            if (optionalVisitorsSign.isPresent()){
                return new ResponseEntity<>(optionalVisitorsSign,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("visitorsSign is not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
}
