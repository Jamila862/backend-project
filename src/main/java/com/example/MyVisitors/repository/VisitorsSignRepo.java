package com.example.MyVisitors.repository;

import com.example.MyVisitors.model.VisitorsSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorsSignRepo extends JpaRepository<VisitorsSign ,Integer> {
}
