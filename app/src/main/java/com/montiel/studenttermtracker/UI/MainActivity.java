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

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        TermEntity testTerm1 = new TermEntity(1, "Winter Term", "08/15/21", "08/15/22");
        repository.insertTerm(testTerm1);
        TermEntity testTerm2 = new TermEntity(2, "Fall Term", "09/07/22", "09/11/23");
        CourseEntity testCourse1 = new CourseEntity(1, "Math", "08/15/05", "09/30/05", "Completed", "Bob Smith", "555-243-1098", "bobsmith@hsd.edu", "This class will probably take 6 weeks.", 1);
        CourseEntity testCourse2 = new CourseEntity(2, "Chemistry", "08/15/05", "09/30/05", "In Progress", "Bob Smith", "555-243-1098", "bobsmith@hsd.edu", "This class will probably take 6 weeks.", 1);
        repository.insertTerm(testTerm2);
        repository.insertCourse(testCourse1);
        repository.insertCourse(testCourse2);
        AssessmentEntity testAssessment = new AssessmentEntity(1, "GCA-1", "Performance", "08/09/10", "08/09/15" ,1);
        repository.insertAssessment(testAssessment);
    }

    public void enterApplication(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}