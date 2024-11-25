package com.example.database_project.controller;

import com.example.database_project.entities.Courses;
import com.example.database_project.entities.Responses;
import com.example.database_project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody Courses courses){
        Courses courses1=courseService.addNewCourse(courses);
       return (courses1==null)? new ResponseEntity<>(new Responses("99","unable to add course "), HttpStatus.OK):
               new ResponseEntity<>(new Responses("00"," course added successfully"), HttpStatus.OK);
    }
   @PostMapping("/allCourses")
    public ResponseEntity<?> allCourse(){
     return new ResponseEntity<>(courseService.findAllCourses(),HttpStatus.OK);
   }
   @PostMapping("deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestBody Courses courses){
      courseService.deleteCourse(courses.getCourseId());
      return new ResponseEntity<>(new Responses("00","succesully deleted"), HttpStatus.OK);
   }
}
