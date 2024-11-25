package com.example.database_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId; // Changed to lowercase
    private String courseName; // Changed to lowercase
    private String courseCode; // Changed to lowercase
    private String courseUnit; // Changed to lowercase
//    @ManyToOne
//    @JoinColumn(name ="admin_id")
//    private Admin admin;
//    @ManyToOne
//    @JoinColumn(name ="schedule_id")
//    private Schedule schedule;
}

