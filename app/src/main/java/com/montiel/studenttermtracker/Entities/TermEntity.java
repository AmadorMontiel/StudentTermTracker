package com.montiel.studenttermtracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity (tableName = "term_table")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;
    private String termStartDate;
    private String termEndDate;
    private List<CourseEntity> associatedCourses;

    public TermEntity(int termID, String termName, String termStartDate, String termEndDate) {
        this.termID = termID;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {
        return termStartDate;
    }

    public void setStartDate(String startDate) { this.termStartDate = startDate; }

    public String getEndTime() {
        return termEndDate;
    }

    public void setEndTime(String endTime) {
        this.termEndDate = endTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "TermEntity{" +
                ", termName='" + termName + '\'' +
                ", startTime=" + termStartDate +
                ", endTime=" + termEndDate +
                '}';
    }
}
