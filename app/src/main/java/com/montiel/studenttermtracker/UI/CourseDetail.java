package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CourseDetail extends AppCompatActivity {

    Repository repository;
    ArrayList<CourseEntity> allCourses;
    ArrayList<AssessmentEntity> allAssessments;
    Spinner editCourseStatusSpinner;
    String[] courseStatus;

    int courseID;
    int position;

    EditText editCourseName;
    EditText editCourseStartDate;
    EditText editCourseEndDate;
    EditText editCourseInstructorName;
    EditText editCourseInstructorPhoneNumber;
    EditText editCourseInstructorEmailAddress;
    EditText editCourseNote;

    CourseEntity currentCourse;

    String dateFormat = "mm/dd/yy";
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseStatus = new String[] {"In Progress", "Completed", "Dropped", "Plan To Take"};
        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();
        courseID = getIntent().getIntExtra("courseID", -1);

        for (CourseEntity c: allCourses) {
            if (c.getCourseID() == courseID) {
                currentCourse = c;
            }
        }

        allAssessments = repository.getAssessmentsByCourse(currentCourse.getCourseID());

        editCourseName = findViewById(R.id.edit_course_name);
        editCourseStartDate = findViewById(R.id.edit_course_start_date);
        editCourseEndDate = findViewById(R.id.edit_course_end_date);
        editCourseStatusSpinner = findViewById(R.id.edit_course_status_spinner);
        editCourseInstructorName = findViewById(R.id.edit_course_instructor_name);
        editCourseInstructorPhoneNumber = findViewById(R.id.edit_course_instructor_phone_number);
        editCourseInstructorEmailAddress = findViewById(R.id.edit_course_instructor_email_address);
        editCourseNote = findViewById(R.id.edit_course_note);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseStatus);
        editCourseStatusSpinner.setAdapter(adapter);

        if (currentCourse != null) {

            RecyclerView recyclerView = findViewById(R.id.assessment_recycler_view);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            assessmentAdapter.setAssessments(allAssessments);

            switch (currentCourse.getStatus()) {
                case "In Progress": {
                    position = 0;
                    break;
                }
                case "Completed": {
                    position = 1;
                    break;
                }
                case "Dropped": {
                    position = 2;
                    break;
                }
                case "Plan To Take": {
                    position = 3;
                    break;
                }
            }

            editCourseName.setText(currentCourse.getCourseName());
            editCourseStartDate.setText(currentCourse.getCourseStartDate());
            editCourseEndDate.setText(currentCourse.getCourseEndDate());
            editCourseStatusSpinner.setSelection(position);
            editCourseInstructorName.setText(currentCourse.getInstructorName());
            editCourseInstructorPhoneNumber.setText(currentCourse.getInstructorPhoneNumber());
            editCourseInstructorEmailAddress.setText(currentCourse.getInstructorEmailAddress());
            editCourseNote.setText(currentCourse.getNote());
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

            case R.id.share_course_note_menu_item: {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, currentCourse.getNote());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            }

            case R.id.notify_course_start_menu_item: {

                Date notifyStartDate = null;
                try {
                    notifyStartDate = formatter.parse(currentCourse.getCourseStartDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long startTrigger = notifyStartDate.getTime();

                Intent intent = new Intent(CourseDetail.this, NotificationReceiver.class);
                intent.putExtra("key", "Your course: " + currentCourse.getCourseName() + " is starting on " + currentCourse.getCourseStartDate());
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetail.this, ++MainActivity.numAlert, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, sender);


                return true;
            }

            case R.id.notify_course_end_menu_item: {

                Date notifyEndDate = null;
                try {
                    notifyEndDate = formatter.parse(currentCourse.getCourseEndDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long endTrigger = notifyEndDate.getTime();

                Intent intent = new Intent(CourseDetail.this, NotificationReceiver.class);
                intent.putExtra("key", "Your course: " + currentCourse.getCourseName() + " is ending on " + currentCourse.getCourseEndDate());
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetail.this, ++MainActivity.numAlert, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, sender);

                return true;
            }

            case R.id.delete_course_menu_item: {
                repository.deleteCourse(currentCourse);
                Intent intent = new Intent(CourseDetail.this, CourseList.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(CourseDetail.this, AddAssessment.class);
        intent.putExtra("courseID", currentCourse.getCourseID());
        startActivity(intent);
    }

    public void assessmentDetails(View view) {
        Intent intent = new Intent(CourseDetail.this, AssessmentDetail.class);
        startActivity(intent);
    }

    public void saveCourse(View view) {
        CourseEntity oldCourse = new CourseEntity(getIntent().getIntExtra("courseID", -1),
                editCourseName.getText().toString(),
                editCourseStartDate.getText().toString(),
                editCourseEndDate.getText().toString(),
                editCourseStatusSpinner.getSelectedItem().toString(),
                editCourseInstructorName.getText().toString(),
                editCourseInstructorPhoneNumber.getText().toString(),
                editCourseInstructorEmailAddress.getText().toString(),
                editCourseNote.getText().toString(),
                getIntent().getIntExtra("termID", -1));
        repository.updateCourse(oldCourse);

        Intent intent = new Intent(CourseDetail.this, CourseList.class);
        intent.putExtra("termID", currentCourse.getTermID());
        startActivity(intent);
    }
}