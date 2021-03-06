package com.montiel.studenttermtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.montiel.studenttermtracker.Entities.AssessmentEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentEntity assessment);

    @Update
    void update(AssessmentEntity assessment);

    @Delete
    void delete(AssessmentEntity assessment);

    @Query("SELECT * FROM assessment_table ORDER BY assessmentID")
    List<AssessmentEntity> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE courseID = :associatedCourse")
    List<AssessmentEntity> getAssessmentsByCourse(int associatedCourse);
}
