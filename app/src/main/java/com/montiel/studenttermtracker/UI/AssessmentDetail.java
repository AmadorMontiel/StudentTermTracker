package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    String dateFormat = "mm/dd/yy";
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);


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
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.notify_assessment_start_menu_item: {

                Date notifyStartDate = null;
                try {
                    notifyStartDate = formatter.parse(currentAssessment.getAssessmentStartDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long startTrigger = notifyStartDate.getTime();

                Intent intent = new Intent(AssessmentDetail.this, NotificationReceiver.class);
                intent.putExtra("key", "Your assessment: " + currentAssessment.getAssessmentName() + " is starting on " + currentAssessment.getAssessmentStartDate());
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, sender);


                return true;
            }

            case R.id.notify_assessment_end_menu_item: {

                Date notifyEndDate = null;
                try {
                    notifyEndDate = formatter.parse(currentAssessment.getAssessmentEndDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long endTrigger = notifyEndDate.getTime();

                Intent intent = new Intent(AssessmentDetail.this, NotificationReceiver.class);
                intent.putExtra("key", "Your course: " + currentAssessment.getAssessmentName() + " is ending on " + currentAssessment.getAssessmentEndDate());
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, sender);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessment_menu, menu);
        return true;
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