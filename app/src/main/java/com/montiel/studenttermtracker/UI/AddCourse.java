package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddCourse extends AppCompatActivity {

    Repository repository;
    ArrayList<CourseEntity> allCourses;
    int courseID;
    int termID;
    Spinner spinner;
    String[] courseStatus;

    EditText addCourseName;
    EditText addCourseStartDate;
    EditText addCourseEndDate;
    EditText addCourseInstructorName;
    EditText addCourseInstructorPhoneNumber;
    EditText addCourseInstructorEmailAddress;
    EditText addCourseNote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseStatus = new String[] {"In Progress", "Completed", "Dropped", "Plan To Take"};
        spinner = findViewById(R.id.edit_course_status_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseStatus);
        spinner.setAdapter(adapter);

        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();
        termID = getIntent().getIntExtra("termID", -1);


        addCourseName = findViewById(R.id.edit_course_name);
        addCourseStartDate = findViewById(R.id.edit_course_start_date);
        addCourseEndDate = findViewById(R.id.edit_course_end_date);
        addCourseInstructorName = findViewById(R.id.edit_course_instructor_name);
        addCourseInstructorPhoneNumber = findViewById(R.id.edit_course_instructor_phone_number);
        addCourseInstructorEmailAddress = findViewById(R.id.edit_course_instructor_email_address);
        addCourseNote = findViewById(R.id.edit_course_note);

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

    public void saveNewCourse(View view) {
        courseID = allCourses.get(allCourses.size() - 1).getCourseID();
        CourseEntity newCourse = new CourseEntity(++courseID, addCourseName.getText().toString(),
                addCourseStartDate.getText().toString(),
                addCourseEndDate.getText().toString(),
                spinner.getSelectedItem().toString(),
                addCourseInstructorName.getText().toString(),
                addCourseInstructorPhoneNumber.getText().toString(),
                addCourseInstructorEmailAddress.getText().toString(),
                addCourseNote.getText().toString(),
                termID);
        repository.insertCourse(newCourse);

        Intent intent = new Intent(AddCourse.this, CourseList.class);
        intent.putExtra("termID", termID);
        startActivity(intent);
    }
}