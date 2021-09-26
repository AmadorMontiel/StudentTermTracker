package com.montiel.studenttermtracker.Database;

import android.app.Application;

import com.montiel.studenttermtracker.DAO.AssessmentDAO;
import com.montiel.studenttermtracker.DAO.CourseDAO;
import com.montiel.studenttermtracker.DAO.TermDAO;
import com.montiel.studenttermtracker.Entities.AssessmentEntity;
import com.montiel.studenttermtracker.Entities.CourseEntity;
import com.montiel.studenttermtracker.Entities.TermEntity;
import com.montiel.studenttermtracker.UI.AssessmentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TermDAO termDAO;
    private final CourseDAO courseDAO;
    private final AssessmentDAO assessmentDAO;
    private TermEntity selectedTerm;
    private ArrayList<TermEntity> allTerms;
    private ArrayList<CourseEntity> allCourses;
    private ArrayList<AssessmentEntity> allAssessments;
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

    public void deleteTerm(TermEntity termEntity) {
        databaseExecutor.execute(() -> {
            termDAO.delete(termEntity);
        });
    }

    public void deleteCourse(CourseEntity courseEntity) {
        databaseExecutor.execute(() -> {
            courseDAO.delete(courseEntity);
        });
    }

    public void deleteAssessment(AssessmentEntity assessmentEntity) {
        databaseExecutor.execute(() -> {
            assessmentDAO.delete(assessmentEntity);
        });
    }

    public void updateAssessment(AssessmentEntity assessmentEntity) {
        databaseExecutor.execute(() -> {
            assessmentDAO.update(assessmentEntity);
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

    public ArrayList<TermEntity> getAllTerms() {
        databaseExecutor.execute(() ->{
            allTerms = (ArrayList<TermEntity>) termDAO.getAllTerms();
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

    public void insertAssessment(AssessmentEntity assessmentEntity) {
        databaseExecutor.execute(() -> {
            assessmentDAO.insert(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CourseEntity> getAllCourses() {
        databaseExecutor.execute(() ->{
            allCourses = (ArrayList<CourseEntity>) courseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allCourses;
    }

    public ArrayList<CourseEntity> getCoursesByTerm(int associatedTerm) {
        databaseExecutor.execute(() -> {
            allCourses = (ArrayList<CourseEntity>) courseDAO.getCoursesByTerm(associatedTerm);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allCourses;
    }

    public ArrayList<AssessmentEntity> getAllAssessments() {
        databaseExecutor.execute(() -> {
            allAssessments = (ArrayList<AssessmentEntity>) assessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allAssessments;
    }

    public ArrayList<AssessmentEntity> getAssessmentsByCourse(int associatedCourse) {
        databaseExecutor.execute(() -> {
            allAssessments = (ArrayList<AssessmentEntity>) assessmentDAO.getAssessmentsByCourse(associatedCourse);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allAssessments;
    }

    public TermEntity getTermByID (int termID) {
        databaseExecutor.execute(() -> {
            selectedTerm = termDAO.getTermByID(termID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return selectedTerm;
    }



}
