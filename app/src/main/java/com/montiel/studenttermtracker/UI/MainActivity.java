package com.montiel.studenttermtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.montiel.studenttermtracker.Database.Repository;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.R;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        TermEntity testTerm1 = new TermEntity(1, "Test Term 1", "08/15/2021", "08/15/2022");
        repository.insert(testTerm1);
        TermEntity testTerm2 = new TermEntity(2, "Fall Term", "09/07/2022", "09/11/2023");
        repository.insert(testTerm2);
    }

    public void enterApplication(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}