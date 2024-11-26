package com.example.database_project.service;

import com.example.database_project.entities.Courses;

import java.util.List;

public interface CourseService {
    Courses addNewCourse(Courses courses);

    List<Courses> findAllCourses();

    void deleteCourse(Integer courseId);
}
