package com.montiel.studenttermtracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String courseName;
    private String courseStartDate;
    private String courseEndDate;

    private static List<AssessmentEntity> associatedAssessments;

    public CourseEntity(int courseID, String courseName, String courseStartDate, String courseEndDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public static List<AssessmentEntity> getAssociatedAssessments() {
        return associatedAssessments;
    }

    public static void setAssociatedAssessments(List<AssessmentEntity> associatedAssessments) {
        CourseEntity.associatedAssessments = associatedAssessments;
    }
}
