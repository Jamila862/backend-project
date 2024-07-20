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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/managers") // Base path for all endpoints related to managers
public class ManagersAPI {

    @Autowired
    private ManagersRepo managersRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addManagers(@RequestBody Managers managers) {
        try {
            Managers savedManager = managersRepo.save(managers);
            return new ResponseEntity<>(savedManager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add manager", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManagers(@PathVariable("id") int id) {
        try {
            managersRepo.deleteById(id);
            return new ResponseEntity<>("Manager deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete manager", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllManagers() {
        try {
            List<Managers> managersList = managersRepo.findAll();
            if (managersList.isEmpty()) {
                return new ResponseEntity<>("No managers found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(managersList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch managers", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateManagers(@PathVariable("id") int id, @RequestBody Managers updatedManager) {
        Optional<Managers> managerOptional = managersRepo.findById(id);
        if (managerOptional.isPresent()) {
            Managers manager = managerOptional.get();
            manager.setUsername(updatedManager.getUsername());
            try {
                Managers savedManager = managersRepo.save(manager);
                return new ResponseEntity<>(savedManager, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to update manager", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Manager not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManagerById(@PathVariable("id") int id) {
        Optional<Managers> managerOptional = managersRepo.findById(id);
        if (managerOptional.isPresent()) {
            return new ResponseEntity<>(managerOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Manager not found", HttpStatus.NOT_FOUND);
        }
    }
}
