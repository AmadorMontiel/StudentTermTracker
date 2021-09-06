package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.R;

import java.util.List;
import java.util.Objects;

public class CourseDetail extends AppCompatActivity {

    Repository repository;
    List<CourseEntity> allCourses;
    List<AssessmentEntity> allAssessments;

    int courseID;

    EditText editCourseName;
    EditText editCourseStartDate;
    EditText editCourseEndDate;
    EditText editCourseStatus;
    EditText editCourseInstructorName;
    EditText editCourseInstructorPhoneNumber;
    EditText editCourseInstructorEmailAddress;
    EditText editCourseNote;

    CourseEntity currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseID = getIntent().getIntExtra("courseID", -1);

        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();

        for (CourseEntity c: allCourses) {
            if (c.getCourseID() == courseID) {
                currentCourse = c;
            }
        }

        allAssessments = repository.getAssessmentsByCourse(currentCourse.getCourseID());

        editCourseName = findViewById(R.id.edit_course_name);
        editCourseStartDate = findViewById(R.id.edit_course_start_date);
        editCourseEndDate = findViewById(R.id.edit_course_end_date);
        editCourseStatus = findViewById(R.id.edit_course_status);
        editCourseInstructorName = findViewById(R.id.edit_course_instructor_name);
        editCourseInstructorPhoneNumber = findViewById(R.id.edit_course_instructor_phone_number);
        editCourseInstructorEmailAddress = findViewById(R.id.edit_course_instructor_email_address);
        editCourseNote = findViewById(R.id.edit_course_note);

        if (currentCourse != null) {

            editCourseName.setText(currentCourse.getCourseName());
            editCourseStartDate.setText(currentCourse.getCourseStartDate());
            editCourseEndDate.setText(currentCourse.getCourseEndDate());
            editCourseStatus.setText(currentCourse.getStatus());
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
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(CourseDetail.this, AddAssessment.class);
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
                editCourseStatus.getText().toString(),
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