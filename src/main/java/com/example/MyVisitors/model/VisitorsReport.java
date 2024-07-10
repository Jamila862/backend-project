package com.example.MyVisitors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class VisitorsReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    public String FirstName;
    public String LastName;
    private int PhoneNumber;
    public String emailAddress;
    public String Email;
    public String Room;
    public java.util.Date Date;
    public Date Time_Sign_in;
    public Date Time_Sign_Out;
}
