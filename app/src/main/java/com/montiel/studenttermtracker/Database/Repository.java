package com.montiel.studenttermtracker.Database;

import android.app.Application;

import com.montiel.studenttermtracker.DAO.AssessmentDAO;
import com.montiel.studenttermtracker.DAO.CourseDAO;
import com.montiel.studenttermtracker.DAO.TermDAO;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TermDAO termDAO;
    private final CourseDAO courseDAO;
    private final AssessmentDAO assessmentDAO;
    private List<TermEntity> allTerms;
    private List<CourseEntity> allCourses;
    private List<AssessmentEntity> allAssessments;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        TermTrackerDatabaseBuilder db = TermTrackerDatabaseBuilder.getDatabase(application);
        termDAO = db.termDAO();
        courseDAO = db.courseDAO();
        assessmentDAO = db.assessmentDAO();
    }

    public void insertTerm(TermEntity termEntity) {
        databaseExecutor.execute(() ->{
            termDAO.insert(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermEntity termEntity) {
        databaseExecutor.execute(() -> {
            termDAO.delete(termEntity);
        });
    }

    public void updateTerm(TermEntity termEntity) {
        databaseExecutor.execute(() -> {
            termDAO.update(termEntity);
        });
    }

    public void updateCourse(CourseEntity oldCourse) {
        databaseExecutor.execute(() -> {
                courseDAO.update(oldCourse);
        });
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

    public void insertCourse(CourseEntity courseEntity) {
        databaseExecutor.execute(() -> {
            courseDAO.insert(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<CourseEntity> getAllCourses() {
        databaseExecutor.execute(() ->{
            allCourses = courseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allCourses;
    }

    public List<CourseEntity> getCoursesByTerm(int associatedTerm) {
        databaseExecutor.execute(() -> {
            allCourses = courseDAO.getCoursesByTerm(associatedTerm);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allCourses;
    }

    public List<AssessmentEntity> getAllAssessments() {
        databaseExecutor.execute(() -> {
            allAssessments = assessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allAssessments;
    }

    public List<AssessmentEntity> getAssessmentsByCourse(int associatedCourse) {
        databaseExecutor.execute(() -> {
            allAssessments = assessmentDAO.getAssessmentsByCourse(associatedCourse);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allAssessments;
    }


}
