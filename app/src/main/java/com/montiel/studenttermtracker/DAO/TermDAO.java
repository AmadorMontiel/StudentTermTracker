package com.montiel.studenttermtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.montiel.studenttermtracker.Entities.TermEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermEntity term);

    @Update
    void update(TermEntity term);

    @Delete
    void delete(TermEntity term);

    @Query("SELECT * FROM term_table ORDER BY termID")
    List<TermEntity> getAllTerms();

    @Query("SELECT * FROM term_table WHERE termID = :termID")
    TermEntity getTermByID(int termID);
}
