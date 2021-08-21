package com.montiel.studenttermtracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String courseName;
    private String courseStartDate;
    private String courseEndDate;
}
