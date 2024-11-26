package com.example.database_project.service;

import com.example.database_project.entities.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(Schedule schedule);

    List<Schedule> findAllSchedules();
}
