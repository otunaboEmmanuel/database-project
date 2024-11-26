package com.example.database_project.repository;

import com.example.database_project.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {
    Optional<Courses> findByCourseName(String courseName);
}
