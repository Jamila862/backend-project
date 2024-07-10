package com.example.MyVisitors.repository;

import com.example.MyVisitors.model.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepo extends JpaRepository<Managers , Integer> {
}
