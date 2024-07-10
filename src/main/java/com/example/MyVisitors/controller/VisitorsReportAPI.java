package com.example.MyVisitors.controller;

import com.example.MyVisitors.model.VisitorsReport;
import com.example.MyVisitors.repository.VisitorsReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping
public class VisitorsReportAPI {
    @Autowired
    private VisitorsReportRepo visitorsReportRepo;

    @PostMapping("add/visitorsReport")
    public ResponseEntity<?>addVisitorsReport(@RequestBody VisitorsReport visitorsReport){
        try {
            VisitorsReport visitorsReport1 = visitorsReportRepo.save(visitorsReport);
            return  new ResponseEntity<>(visitorsReport1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("all/visitorsReport")
    public ResponseEntity<?> get(){
        try {
            List<VisitorsReport>visitorsReportList = visitorsReportRepo.findAll();
            if (visitorsReportList.isEmpty()){
                return new ResponseEntity<>("VisitorsReport is not found",HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(visitorsReportList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("Delete/visitorsReport{Id}")
    public ResponseEntity<?>deleteVisitorsReport(@PathVariable int Id){
        try {
            visitorsReportRepo.deleteById(Id);
            return new ResponseEntity<>("VisitorsReport delete successfully",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("Update/VisitorsReport{Id}")
    public ResponseEntity<?>updateVisitorsReport(@PathVariable int Id,@RequestBody VisitorsReport updateVisitorsReport){
        Optional<VisitorsReport>visitorsReportOptional = visitorsReportRepo.findById(Id);
        if (visitorsReportOptional.isPresent()){
            VisitorsReport visitorsReport = visitorsReportOptional.get();
            visitorsReport.setId(updateVisitorsReport.getId());
            visitorsReport.setFirstName(updateVisitorsReport.getFirstName());
            visitorsReportRepo.save(visitorsReport);
            return  new ResponseEntity<>(visitorsReport,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("VisitorsReport is not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getVisitorsReport{Id}")
    public ResponseEntity<?>getId(@PathVariable int Id){
        try {
            Optional<VisitorsReport> optionalVisitorsReport = visitorsReportRepo.findById(Id);
            if (optionalVisitorsReport.isPresent()) {
                return new ResponseEntity<>(optionalVisitorsReport,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("visitorsReport is not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
}
