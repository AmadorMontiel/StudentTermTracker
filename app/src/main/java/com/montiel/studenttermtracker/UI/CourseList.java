package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.montiel.studenttermtracker.DAO.TermDAO;
import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseList extends AppCompatActivity {

    Repository repository;
    ArrayList<TermEntity> allTerms;
    ArrayList<CourseEntity> allCourses;
    int termID;

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

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();
        termID = getIntent().getIntExtra("termID", -1);


        for (TermEntity t: allTerms) {
            if (t.getTermID() == termID) {
                currentTerm = t;
            }
        }

        allCourses = repository.getCoursesByTerm(currentTerm.getTermID());

        editTermName = findViewById(R.id.term_title);
        editTermStartDate = findViewById(R.id.term_start_date_picker);
        editTermEndDate = findViewById(R.id.term_end_date_picker);

        if (currentTerm != null) {

            RecyclerView recyclerView = findViewById(R.id.courseListRecyclerView);
            final CourseAdapter courseAdapter = new CourseAdapter(this);
            recyclerView.setAdapter(courseAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            courseAdapter.setCourses(allCourses);

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
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete_term_menu_item: {

                boolean hasNoAssociatedCourses = true;

                for (CourseEntity c : allCourses) {
                    if (c.getTermID() == termID) {
                        hasNoAssociatedCourses = false;
                        break;
                    }
                }

                if (hasNoAssociatedCourses) {
                    repository.deleteTerm(repository.getTermByID(termID));
                    Intent intent = new Intent(CourseList.this, TermList.class);
                    startActivity(intent);
                    return true;
                } else {
                    Toast.makeText(this, "Unable to delete term. Must delete associated courses first!", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.term_detail_menu, menu);
        return true;
    }

    public void addCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        intent.putExtra("termID", currentTerm.getTermID());
        startActivity(intent);
    }

    public void viewCourseDetails(View view) {
        Intent intent = new Intent(CourseList.this, CourseDetail.class);
        startActivity(intent);
    }

    public void saveTerm(View view) {
        TermEntity oldTerm = new TermEntity(getIntent().getIntExtra("termID", -1), editTermName.getText().toString(), editTermStartDate.getText().toString(), editTermEndDate.getText().toString());
        repository.updateTerm(oldTerm);

        Intent intent = new Intent(CourseList.this, TermList.class);
        startActivity(intent);

    }
}