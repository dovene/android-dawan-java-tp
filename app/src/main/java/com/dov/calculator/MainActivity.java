package com.dov.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewItems();
    }

    private void setViewItems() {
        nameEditText = findViewById(R.id.name_edit);
        passwordEditText = findViewById(R.id.password_edit);
        loginButton = findViewById(R.id.login_bt);
        loginButton.setOnClickListener( v -> {
           login();
        });
    }

    private void login() {
        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            Log.d("login","Connexion échouée");
            return;
        }

        Intent intent = new Intent(this, CalculatorActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        Log.d("login","Connexion réussie");
        finish();
    }
}