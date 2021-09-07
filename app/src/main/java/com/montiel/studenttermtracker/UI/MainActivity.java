package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        TermEntity testTerm1 = new TermEntity(1, "Test Term 1", "08/15/2021", "08/15/2022");
        repository.insertTerm(testTerm1);
        TermEntity testTerm2 = new TermEntity(2, "Fall Term", "09/07/2022", "09/11/2023");
        CourseEntity testCourse1 = new CourseEntity(1, "Math", "08152005", "08152006", "Enrolled", "Bob Smith", "555-555-5555", "bobsmith@hsd.edu", 1);
        repository.insertTerm(testTerm2);
        repository.insertCourse(testCourse1);
        AssessmentEntity testAssessment = new AssessmentEntity(1, "GCA-1", "Objective", "08092010", "08092015" ,1);
        repository.insertAssessment(testAssessment);
    }

    public void enterApplication(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}