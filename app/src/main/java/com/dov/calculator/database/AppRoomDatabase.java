package com.dov.calculator.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Calculator.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract CalculatorDao calculatorDao();
    // Singleton instance
    private static AppRoomDatabase instance;

    public static AppRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "calculator_database")
                    .build();
        }
        return instance;
    }
}