package com.dov.calculator;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dov.calculator.database.AppRoomDatabase;
import com.dov.calculator.database.Calculator;
import com.dov.calculator.database.SQLDatabaseHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CalculatorViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> _loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> loginSuccess = _loginSuccess;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    public CalculatorViewModel(Application application) {
        super(application);
    }


    public void writeFile(Context context, String content) {
        String filename = "log.txt";
        try (FileOutputStream fos = context.openFileOutput(filename,
                Context.MODE_PRIVATE | Context.MODE_APPEND)) {
            fos.write(content.getBytes());
            fos.write("*file*".getBytes());
        } catch (IOException e) {
            Log.e("writeFile", e.getMessage());
        }
    }

    public String readFromFile(Context context) {
        try (FileInputStream fis = context.openFileInput("log.txt")) {
            int content;
            StringBuilder stringBuilder = new StringBuilder();
            while ((content = fis.read()) != -1) {
                stringBuilder.append((char) content);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            Log.e("readFromFile", e.getMessage());
            return null;
        }
    }

    public void saveWithSQLDatabase(Context context, String result) {
        SQLiteDatabase db = new SQLDatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("result", result);
        db.insert("calculator", null, values);
    }

    public String getFromSQLDatabase(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        SQLiteDatabase db = new SQLDatabaseHelper(context).getWritableDatabase();
        Cursor cursor = db.query("calculator", null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String result = cursor.getString(cursor.getColumnIndex("result"));
            stringBuilder.append(result).append("*sql*");
        }
        cursor.close();
        return stringBuilder.toString();
    }

    public void insertWithRoom(Context context, String result) {
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(context);
            com.dov.calculator.database.Calculator calculator = new com.dov.calculator.database.Calculator();
            calculator.result = result;
            db.calculatorDao().insert(calculator);
        }).start();
    }

    public LiveData<List<Calculator>> getAllWithRoom(Context context) {
        MutableLiveData<List<com.dov.calculator.database.Calculator>> result = new MutableLiveData<>();
        new Thread(() -> {
            AppRoomDatabase db = AppRoomDatabase.getInstance(context);
            List<com.dov.calculator.database.Calculator> calculators = db.calculatorDao().getAll();
            result.postValue(calculators);
        }).start();
        return result;
    }

    public LiveData<String> getAllResultsWithRoom(Context context) {
        MutableLiveData<String> result = new MutableLiveData<>();
        new Thread(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            AppRoomDatabase db = AppRoomDatabase.getInstance(context);
            List<com.dov.calculator.database.Calculator> calculators = db.calculatorDao().getAll();
            for (com.dov.calculator.database.Calculator calculator : calculators) {
                stringBuilder.append(calculator.result).append("*room*");
            }
            result.postValue(stringBuilder.toString());
        }).start();
        return result;
    }

    public interface DatabaseCallback {
        void onResult(String result);
    }

    public void getAllWithRoom(Context context, DatabaseCallback callback) {
        new Thread(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            AppRoomDatabase db = AppRoomDatabase.getInstance(context);  // Use proper context
            List<com.dov.calculator.database.Calculator> calculators = db.calculatorDao().getAll();
            for (com.dov.calculator.database.Calculator calculator : calculators) {
                stringBuilder.append(calculator.result).append("***");
            }
            // Run callback on main thread
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onResult(stringBuilder.toString());
            });
        }).start();
    }
}
