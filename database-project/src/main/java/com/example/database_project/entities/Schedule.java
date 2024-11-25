package com.example.database_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer ScheduleId;
    private String StartTime;
    private String Day_of_the_week;
    private String courseName;
    private String endTime;
//    @OneToMany(mappedBy = "schedule")
//    private List<Courses> courses;
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Users student;

}
