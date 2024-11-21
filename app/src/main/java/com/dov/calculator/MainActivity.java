package com.dov.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText passwordEditText;
    Button loginButton;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialiser le ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setViewItems();
        setObservers();
    }

    private void setViewItems() {
        nameEditText = findViewById(R.id.name_edit);
        passwordEditText = findViewById(R.id.password_edit);
        loginButton = findViewById(R.id.login_bt);
        loginButton.setOnClickListener( v -> {
            mainActivityViewModel.login(nameEditText.getText().toString(), passwordEditText.getText().toString());
        });
        findViewById(R.id.responsive_form_bt).setOnClickListener( v -> {
           startActivity(new Intent(MainActivity.this, RichFormActivity.class));
        });
        nameEditText.setText(mainActivityViewModel.getSavedUserData().first);
        passwordEditText.setText(mainActivityViewModel.getSavedUserData().second);
        findViewById(R.id.photo_album_bt).setOnClickListener( v -> {
            startActivity(new Intent(MainActivity.this, AlbumActivity.class));
        });
    }

    private void setObservers() {
        // Observer le résultat de la connexion
        mainActivityViewModel.loginSuccess.observe(this, success -> {
            if (success) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                intent.putExtra("name", nameEditText.getText().toString());
                startActivity(intent);
                Log.d("login", "Connexion réussie");
                finish();
            }
        });
        // Observer les messages d’erreur
        mainActivityViewModel.errorMessage.observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                Log.d("login", "Connexion échouée");
            }
        });
    }

}