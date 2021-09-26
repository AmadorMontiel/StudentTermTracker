package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddAssessment extends AppCompatActivity {

    Repository repository;
    ArrayList<AssessmentEntity> allAssessments;
    int assessmentID;
    int courseID;

    EditText addAssessmentName;
    EditText addAssessmentStartDate;
    EditText addAssessmentEndDate;

    RadioButton objective;
    RadioButton performance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        courseID = getIntent().getIntExtra("courseID", -1);

        addAssessmentName = findViewById(R.id.add_assessment_name);
        addAssessmentStartDate = findViewById(R.id.add_assessment_start_date);
        addAssessmentEndDate = findViewById(R.id.add_assessment_end_date);
        objective = findViewById(R.id.objective_radio_button);
        performance = findViewById(R.id.performance_radio_button);


    }

    /*
     * Method Author: Carolyn Sher-DeCusatis
     * Borrowed from webinar: https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=f0919dd1-047e-44b0-af85-ad4e01665bf3
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNewAssessment(View view) {
        assessmentID = allAssessments.get(allAssessments.size() - 1).getAssessmentID();

        if (objective.isChecked()) {
            AssessmentEntity newAssessment = new AssessmentEntity(++assessmentID,
                    addAssessmentName.getText().toString(),
                    "Objective",
                    addAssessmentStartDate.getText().toString(),
                    addAssessmentEndDate.getText().toString(),
                    courseID);
            repository.insertAssessment(newAssessment);
        } else if (performance.isChecked()){
            AssessmentEntity newAssessment = new AssessmentEntity(++assessmentID,
                    addAssessmentName.getText().toString(),
                    "Performance",
                    addAssessmentStartDate.getText().toString(),
                    addAssessmentEndDate.getText().toString(),
                    courseID);
            repository.insertAssessment(newAssessment);
        }

        Intent intent = new Intent(AddAssessment.this, CourseDetail.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }
}