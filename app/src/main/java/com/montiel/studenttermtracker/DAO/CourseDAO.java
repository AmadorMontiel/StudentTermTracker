package com.montiel.studenttermtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.montiel.studenttermtracker.Entities.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseEntity course);

    @Update
    void update(CourseEntity course);

    @Delete
    void delete(CourseEntity course);

    @Query("SELECT * FROM course_table  ORDER BY courseID")
    List<CourseEntity> getAllCourses();

    @Query("SELECT * FROM course_table WHERE termID = :associatedTerm")
    List<CourseEntity> getCoursesByTerm(int associatedTerm);
}
