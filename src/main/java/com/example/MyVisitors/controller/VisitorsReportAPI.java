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
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RequestMapping("/api")
public class VisitorsReportAPI {

    @Autowired
    private VisitorsReportRepo visitorsReportRepo;

    @PostMapping("/add/visitorsReport")
    public ResponseEntity<VisitorsReport> addVisitorsReport(@RequestBody VisitorsReport visitorsReport) {
        try {
            VisitorsReport newVisitor = visitorsReportRepo.save(visitorsReport);
            return new ResponseEntity<>(newVisitor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/visitorsReport")
    public ResponseEntity<List<VisitorsReport>> getAllVisitorsReport() {
        try {
            List<VisitorsReport> visitorsReports = visitorsReportRepo.findAll();
            if (visitorsReports.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(visitorsReports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/visitorsReport/{id}")
    public ResponseEntity<HttpStatus> deleteVisitorsReport(@PathVariable("id") int id) {
        try {
            visitorsReportRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/visitorsReport/{id}")
    public ResponseEntity<VisitorsReport> updateVisitorsReport(@PathVariable("id") int id, @RequestBody VisitorsReport updateVisitorsReport) {
        Optional<VisitorsReport> optionalVisitorsReport = visitorsReportRepo.findById(id);
        if (optionalVisitorsReport.isPresent()) {
            VisitorsReport visitorsReport = optionalVisitorsReport.get();
            visitorsReport.setFirstName(updateVisitorsReport.getFirstName());
            visitorsReport.setLastName(updateVisitorsReport.getLastName());
            visitorsReport.setPhoneNumber(updateVisitorsReport.getPhoneNumber());
            visitorsReport.setEmail(updateVisitorsReport.getEmail());
            visitorsReport.setRoom(updateVisitorsReport.getRoom());
            visitorsReport.setDate(updateVisitorsReport.getDate());

            return new ResponseEntity<>(visitorsReportRepo.save(visitorsReport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/visitorsReport/{id}")
    public ResponseEntity<VisitorsReport> getVisitorsReportById(@PathVariable("id") int id) {
        Optional<VisitorsReport> visitorsReportData = visitorsReportRepo.findById(id);

        return visitorsReportData.map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
