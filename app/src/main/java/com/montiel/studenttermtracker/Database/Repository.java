package com.montiel.studenttermtracker.Database;

import android.app.Application;

import com.montiel.studenttermtracker.DAO.TermDAO;
import com.montiel.studenttermtracker.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO termDAO;
    private List<TermEntity> allTerms;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        TermTrackerDatabaseBuilder db = TermTrackerDatabaseBuilder.getDatabase(application);
        termDAO=db.termDAO();
    }

    public void insert(TermEntity termEntity) {
        databaseExecutor.execute(() ->{
            termDAO.insert(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<TermEntity> getAllTerms() {
        databaseExecutor.execute(() ->{
            allTerms = termDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allTerms;
    }
}
