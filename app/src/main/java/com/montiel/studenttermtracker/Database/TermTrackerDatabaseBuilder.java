package com.montiel.studenttermtracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.montiel.studenttermtracker.DAO.TermDAO;
import com.montiel.studenttermtracker.Entities.TermEntity;

@Database(entities = {TermEntity.class}, version = 1,exportSchema = false)
public abstract class TermTrackerDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();

    private static volatile TermTrackerDatabaseBuilder INSTANCE;

    static TermTrackerDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (TermTrackerDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermTrackerDatabaseBuilder.class, "TermTrackerDatabase.db")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
