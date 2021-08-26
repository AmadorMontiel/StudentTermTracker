package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.R;

import java.util.Objects;

public class CourseList extends AppCompatActivity {

    String termName;
    EditText editTermName;

    String termStartDate;
    EditText editTermStartDate;
    String termEndDate;
    EditText editTermEndDate;

    int termID;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        termName = getIntent().getStringExtra("termName");
        editTermName = findViewById(R.id.term_title);
        termStartDate = getIntent().getStringExtra("termStartDate");
        editTermStartDate = findViewById(R.id.term_start_date_picker);
        termEndDate = getIntent().getStringExtra("termEndDate");
        editTermEndDate = findViewById(R.id.term_end_date_picker);

        repository = new Repository(getApplication());

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

    public void addCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        startActivity(intent);
    }

    public void viewCourseDetails(View view) {
        Intent intent = new Intent(CourseList.this, CourseDetail.class);
        startActivity(intent);
    }

    public void saveTerm(View view) {
        String screenTermName = editTermName.getText().toString();
        if (termName == null) {
            TermEntity editedTerm = new TermEntity(++termID, screenTermName, editTermStartDate.toString(), editTermEndDate.toString());
            repository.insert(editedTerm);
        } else {
            TermEntity oldTerm = new TermEntity(getIntent().getIntExtra("termID", -1), screenTermName, editTermStartDate.toString(), editTermEndDate.toString());
            repository.update(oldTerm);
        }

    }
}