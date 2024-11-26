package com.example.database_project.service;

import com.example.database_project.entities.Courses;
import com.example.database_project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Courses addNewCourse(Courses courses) {
        Courses courses1=courseRepository.findByCourseName(courses.getCourseName()).orElse(null);
        return (courses1==null)?courseRepository.save(courses):null;
    }

    @Override
    public List<Courses> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }


}
