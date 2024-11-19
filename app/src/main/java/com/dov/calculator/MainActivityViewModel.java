package com.dov.calculator;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> _loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> loginSuccess = _loginSuccess;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    public MainActivityViewModel(Application application) {
        super(application);
    }

    public void login(String name, String password) {
        if (name.isEmpty() || password.isEmpty()) {
            _errorMessage.setValue("Veuillez remplir tous les champs");
            _loginSuccess.setValue(false);
        } else {
            _loginSuccess.setValue(true);
            savePreferences(name, password);
        }
    }

    private void savePreferences(String name, String password) {
        SharedPreferences prefs = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name);
        editor.putString("password", password);
        editor.apply();
    }

    public Pair<String, String> getSavedUserData() {
        SharedPreferences prefs = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return new Pair<>(prefs.getString("name", ""), prefs.getString("password", ""));
    }

}
