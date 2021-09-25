package com.montiel.studenttermtracker.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity (tableName = "course_table" )
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String courseName;
    private String courseStartDate;
    private String courseEndDate;
    private String status;
    private String instructorName;
    private String instructorPhoneNumber;
    private String instructorEmailAddress;
    private String note;

    private int termID;

    public CourseEntity(int courseID, String courseName, String courseStartDate,
                        String courseEndDate, String status, String instructorName,
                        String instructorPhoneNumber, String instructorEmailAddress,
                        String note, int termID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhoneNumber = instructorPhoneNumber;
        this.instructorEmailAddress = instructorEmailAddress;
        this.note = note;
        this.termID = termID;
    }

    //@Ignore
    /*public CourseEntity(int courseID, String courseName,
                        String courseStartDate, String courseEndDate,
                        String status, String instructorName,
                        String instructorPhoneNumber,
                        String instructorEmailAddress, int termID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhoneNumber = instructorPhoneNumber;
        this.instructorEmailAddress = instructorEmailAddress;
        this.termID = termID;
    }*/

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

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhoneNumber() {
        return instructorPhoneNumber;
    }

    public void setInstructorPhoneNumber(String instructorPhoneNumber) {
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    public String getInstructorEmailAddress() {
        return instructorEmailAddress;
    }

    public void setInstructorEmailAddress(String instructorEmailAddress) {
        this.instructorEmailAddress = instructorEmailAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseStartDate='" + courseStartDate + '\'' +
                ", courseEndDate='" + courseEndDate + '\'' +
                ", termID=" + termID +
                '}';
    }
}
