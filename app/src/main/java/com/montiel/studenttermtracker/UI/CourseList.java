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

import java.util.List;
import java.util.Objects;

public class CourseList extends AppCompatActivity {

    Repository repository;
    List<TermEntity> allTerms;
    int termID;
    String termName;
    String termStartDate;
    String termEndDate;

    EditText editTermName;
    EditText editTermStartDate;
    EditText editTermEndDate;

    TermEntity currentTerm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        termID = getIntent().getIntExtra("termID", -1);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();

        for (TermEntity t: allTerms) {
            if (t.getTermID() == termID) {
                currentTerm = t;
            }
        }

        editTermName = findViewById(R.id.term_title);
        editTermStartDate = findViewById(R.id.term_start_date_picker);
        editTermEndDate = findViewById(R.id.term_end_date_picker);

        if (currentTerm != null) {
            editTermName.setText(currentTerm.getTermName());
            editTermStartDate.setText(currentTerm.getTermStartDate());
            editTermEndDate.setText(currentTerm.getTermEndDate());
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

    public void addCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        startActivity(intent);
    }

    public void viewCourseDetails(View view) {
        Intent intent = new Intent(CourseList.this, CourseDetail.class);
        startActivity(intent);
    }

    public void saveTerm(View view) {
        if (termID == -1) {
            termID = allTerms.get(allTerms.size() - 1).getTermID();
            TermEntity editedTerm = new TermEntity(++termID, editTermName.getText().toString(), editTermStartDate.getText().toString(), editTermEndDate.getText().toString());
            repository.insert(editedTerm);
        } else {
            TermEntity oldTerm = new TermEntity(getIntent().getIntExtra("termID", -1), editTermName.getText().toString(), editTermStartDate.getText().toString(), editTermEndDate.getText().toString());
            repository.update(oldTerm);
        }

        Intent intent = new Intent(CourseList.this, TermList.class);
        startActivity(intent);

    }
}