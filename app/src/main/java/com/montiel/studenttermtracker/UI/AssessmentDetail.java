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

import java.util.List;
import java.util.Objects;

public class AssessmentDetail extends AppCompatActivity {

    Repository repository;
    List<AssessmentEntity> allAssessments;
    int assessmentID;

    EditText editAssessmentName;
    EditText editAssessmentStartDate;
    EditText editAssessmentEndDate;

    RadioButton objective;
    RadioButton performance;

    AssessmentEntity currentAssessment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        assessmentID = getIntent().getIntExtra("assessmentID", -1);

        for (AssessmentEntity a: allAssessments) {
            if (a.getAssessmentID() == assessmentID) {
                currentAssessment = a;
            }
        }

        objective = findViewById(R.id.objective_radio_button);
        performance = findViewById(R.id.performance_radio_button);

        editAssessmentName = findViewById(R.id.edit_assessment_name_text);
        editAssessmentStartDate = findViewById(R.id.edit_assessment_start_date_text);
        editAssessmentEndDate = findViewById(R.id.edit_assessment_end_date_text);

        if (currentAssessment != null) {
            editAssessmentName.setText(currentAssessment.getAssessmentName());
            editAssessmentStartDate.setText(currentAssessment.getAssessmentStartDate());
            editAssessmentEndDate.setText(currentAssessment.getAssessmentEndDate());

            if (currentAssessment.getAssessmentType().equals("Objective")) {
                objective.toggle();
            } else {
                performance.toggle();
            }
        }



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

    public void saveAssessment(View view) {

        if (objective.isChecked()) {
            AssessmentEntity oldAssessment = new AssessmentEntity(getIntent().getIntExtra("assessmentID", -1),
                    editAssessmentName.getText().toString(),
                    "Objective",
                    editAssessmentStartDate.getText().toString(),
                    editAssessmentEndDate.getText().toString(),
                    getIntent().getIntExtra("courseID", -1));
            repository.updateAssessment(oldAssessment);
        } else if (performance.isChecked()) {
            AssessmentEntity oldAssessment = new AssessmentEntity(getIntent().getIntExtra("assessmentID", -1),
                    editAssessmentName.getText().toString(),
                    "Performance",
                    editAssessmentStartDate.getText().toString(),
                    editAssessmentEndDate.getText().toString(),
                    getIntent().getIntExtra("courseID", -1));
            repository.updateAssessment(oldAssessment);
        }

        Intent intent = new Intent(AssessmentDetail.this, CourseDetail.class);
        intent.putExtra("courseID", currentAssessment.getCourseID());
        startActivity(intent);


    }
}