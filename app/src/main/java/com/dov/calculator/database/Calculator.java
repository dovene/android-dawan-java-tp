package com.dov.calculator.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calculator")
public class Calculator {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String result;
}
