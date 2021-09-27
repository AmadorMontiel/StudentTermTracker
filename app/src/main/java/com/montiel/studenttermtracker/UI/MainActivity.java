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
        TermEntity testTerm1 = new TermEntity(1, "Fall Term", "08/30/21", "12/23/21");
        TermEntity testTerm2 = new TermEntity(2, "Spring Term", "01/03/22", "05/13/22");

        CourseEntity testCourse1 = new CourseEntity(1, "Calculus I", "08/30/21", "12/23/21", "In Progress", "Dee Snyder", "555-243-1098", "dsnyder@wgu.edu", "Pre-Calc Needed First", 1);
        CourseEntity testCourse5 = new CourseEntity(2, "Calculus II", "01/03/22", "05/23/22", "Plan To Take", "Dee Snyder", "555-243-1098", "dsnyder@wgu.edu", "Calc I needed", 2);

        AssessmentEntity testAssessment1 = new AssessmentEntity(1, "CAL1-1", "Objective", "09/10/21", "09/10/21" ,1);
        AssessmentEntity testAssessment2 = new AssessmentEntity(2, "CAL1-2", "Performance", "10/08/21", "10/08/21" ,1);
        AssessmentEntity testAssessment3 = new AssessmentEntity(3, "CAL1-3", "Objective", "11/04/21", "11/04/21" ,1);
        AssessmentEntity testAssessment4 = new AssessmentEntity(4, "CAL1-4", "Performance", "12/01/21", "12/01/21" ,1);
        AssessmentEntity testAssessment5 = new AssessmentEntity(5, "CAL1-5", "Objective", "12/23/21", "12/23/21" ,1);

        AssessmentEntity testAssessment21 = new AssessmentEntity(11, "CAL2-1", "Objective", "02/10/22", "02/10/22" ,2);
        AssessmentEntity testAssessment22 = new AssessmentEntity(12, "CAL2-2", "Performance", "03/08/22", "03/08/22" ,2);
        AssessmentEntity testAssessment23 = new AssessmentEntity(13, "CAL2-3", "Objective", "04/04/22", "04/04/22" ,2);
        AssessmentEntity testAssessment24 = new AssessmentEntity(14, "CAL2-4", "Performance", "05/01/22", "05/01/22" ,2);
        AssessmentEntity testAssessment25 = new AssessmentEntity(15, "CAL2-5", "Objective", "05/23/22", "05/23/22" ,2);


        repository.insertTerm(testTerm1); repository.insertTerm(testTerm2);

        repository.insertCourse(testCourse1);
        repository.insertCourse(testCourse5);

        repository.insertAssessment(testAssessment1); repository.insertAssessment(testAssessment2); repository.insertAssessment(testAssessment3); repository.insertAssessment(testAssessment4);
        repository.insertAssessment(testAssessment5);
        repository.insertAssessment(testAssessment21); repository.insertAssessment(testAssessment22); repository.insertAssessment(testAssessment23); repository.insertAssessment(testAssessment24);
        repository.insertAssessment(testAssessment25);
    }

    public void enterApplication(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}