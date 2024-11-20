package com.dov.calculator.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CalculatorDao {
    @Insert
    void insert(Calculator calculator);

    @Query("SELECT * FROM calculator")
    List<Calculator> getAll();

    @Query("DELETE FROM calculator WHERE id = :calculatorId")
    void deleteById(int calculatorId);

    @Query("SELECT * FROM calculator")
    Cursor getAllAsCursor();
}
