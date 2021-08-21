package com.montiel.studenttermtracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private String assessmentName;
    private String assessmentType;
    private String assessmentStartDate;
    private String assessmentEndDate;

    public AssessmentEntity(int assessmentID, String assessmentName, String assessmentType,
                            String assessmentStartDate, String assessmentEndDate) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }
}
