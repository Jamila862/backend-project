package com.example.MyVisitors.controller;

import com.example.MyVisitors.model.Managers;
import com.example.MyVisitors.repository.ManagersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping
public class ManagersAPI {
    @Autowired
    private ManagersRepo managersRepo;

    @PostMapping
    public ResponseEntity<?> addManagers(@RequestBody Managers managers){
        try {
            Managers managers1 = managersRepo.save(managers);
            return  new ResponseEntity<>(managers1, HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("Delete/managers{Id}")
    public ResponseEntity<?> deleteManagers(@PathVariable int Id){
        try {
            managersRepo.deleteById(Id);
            return new ResponseEntity<>("managers delete successfully",HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/managers")
    public ResponseEntity<?>get(){
        try {
            List<Managers>managersList = managersRepo.findAll();
            if (managersList.isEmpty()){
                return new ResponseEntity<>("Managers is not found", HttpStatus.NOT_FOUND);
            }else {
                return  new ResponseEntity<>(managersList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("Update/managers{Id}")
    public ResponseEntity<?>updateManagers(@PathVariable int Id,@RequestBody Managers updateManagers){
        Optional<Managers> managersOptional = managersRepo.findById(Id);
        if (managersOptional.isPresent()){
            Managers managers = managersOptional.get();
            managers.setId(updateManagers.getId());
            managers.setUsername(updateManagers.getUsername());
            managersRepo.save(managers);
            return  new ResponseEntity<>(managers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Managers is not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getmanagers{Id}")
    public ResponseEntity<?> getId(@PathVariable int Id){
        try {
            Optional<Managers> optionalManagers = managersRepo.findById(Id);
            if (optionalManagers.isPresent()){
                return  new ResponseEntity<>(optionalManagers, HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("Managers is not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
}
