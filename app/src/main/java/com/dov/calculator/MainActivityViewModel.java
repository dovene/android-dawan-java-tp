package com.dov.calculator;

import android.app.Application;
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
        }
    }
}
