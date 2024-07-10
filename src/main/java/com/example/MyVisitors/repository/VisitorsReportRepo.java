package com.example.MyVisitors.repository;

import com.example.MyVisitors.model.VisitorsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorsReportRepo extends JpaRepository<VisitorsReport ,Integer> {
}
