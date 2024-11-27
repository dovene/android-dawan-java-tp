package com.dov.calculator.data;

import java.util.ArrayList;

public class ApplicationData {
    private static ApplicationData instance;
    private ArrayList<String> operationsHistory = new ArrayList<>();

    private ApplicationData() {
    }

    public static ApplicationData getInstance() {
        if (instance == null) {
            instance = new ApplicationData();
        }
        return instance;
    }

    public ArrayList<String> getOperationsHistory() {
        return operationsHistory;
    }


}
