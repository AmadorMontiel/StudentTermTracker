package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.montiel.studenttermtracker.DAO.TermDAO_Impl;
import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.R;

import java.util.List;
import java.util.Objects;

public class AddTerm extends AppCompatActivity {

    Repository repository;
    List<TermEntity> allTerms;
    int termID;

    EditText editTermName;
    EditText editTermStartDate;
    EditText editTermEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();

        editTermName = findViewById(R.id.add_term_title);
        editTermStartDate = findViewById(R.id.add_term_start_date);
        editTermEndDate = findViewById(R.id.add_term_end_date);

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

    public void saveNewTerm(View view) {
            termID = allTerms.get(allTerms.size() - 1).getTermID();
            TermEntity newTerm = new TermEntity(++termID, editTermName.getText().toString(), editTermStartDate.getText().toString(), editTermEndDate.getText().toString());
            repository.insert(newTerm);

            Intent intent = new Intent(AddTerm.this, TermList.class);
            startActivity(intent);
    }
}