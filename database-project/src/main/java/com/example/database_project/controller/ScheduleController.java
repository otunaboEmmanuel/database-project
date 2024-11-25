package com.example.database_project.controller;

import com.example.database_project.entities.Responses;
import com.example.database_project.entities.Schedule;
import com.example.database_project.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    @PostMapping("/add")
    public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule){
        Schedule schedule1=service.addSchedule(schedule);
        return (schedule1==null)? new ResponseEntity<>(new Responses("99","unable to add"), HttpStatus.OK):
                new ResponseEntity<>(new Responses("00","schedule added successfully"),HttpStatus.OK);
    }
    @GetMapping("/allSchedule")
    public ResponseEntity<?> allSchedule(){
        return new ResponseEntity<>(service.findAllSchedules(),HttpStatus.OK);
    }
}
