package com.dov.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dov.calculator.history.HistoryActivity;

public class CalculatorActivity extends AppCompatActivity {
    EditText firstNumberEditText;
    EditText secondNumberEditText;
    TextView resultTextView;
    Button plusButton;
    Button moinsButton;
    TextView titleTextView;
    Button historyButton;
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setViewItems();
        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

    }

    private void setViewItems() {
        firstNumberEditText = findViewById(R.id.first_number);
        secondNumberEditText = findViewById(R.id.second_number);
        resultTextView = findViewById(R.id.result_tv);
        plusButton = findViewById(R.id.operator_plus);
        moinsButton = findViewById(R.id.operator_moins);
        titleTextView = findViewById(R.id.title);
        historyButton = findViewById(R.id.history_bt);

        plusButton.setOnClickListener(v -> {
            calculate('+');
        });
        moinsButton.setOnClickListener(v -> {
            calculate('-');
        });
        historyButton.setOnClickListener(v -> {
            startActivity(new Intent(this, HistoryActivity.class));
        });

        String name = getIntent().getStringExtra("name");
        titleTextView.setText("Bonjour " + name);
    }

    private void calculate(char c) {
        if (firstNumberEditText.getText().toString().isEmpty() || secondNumberEditText.getText().toString().isEmpty()) {
            resultTextView.setText("Veuillez remplir tous les champs");
            return;
        }
        double firstNumber = Double.parseDouble(firstNumberEditText.getText().toString());
        double secondNumber = Double.parseDouble(secondNumberEditText.getText().toString());
        double result = 0;
        if (c == '+') {
            result = firstNumber + secondNumber;
        } else if (c == '-') {
            result = firstNumber - secondNumber;
        }
        String resultString = firstNumberEditText.getText().toString() + " " + c + " " + secondNumberEditText.getText().toString() + " = " + result;
        resultTextView.setText(resultString);
        ApplicationData.getInstance().getOperationsHistory().add(resultString);
        calculatorViewModel.writeFile(this, resultString);
        Toast.makeText(this, calculatorViewModel.readFromFile(this), Toast.LENGTH_SHORT).show();
        calculatorViewModel.saveWithSQLDatabase(this, resultString);
        Toast.makeText(this, calculatorViewModel.getFromSQLDatabase(this), Toast.LENGTH_SHORT).show();
        calculatorViewModel.insertWithRoom(this, resultString);
        calculatorViewModel.getAllResultsWithRoom(this).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(CalculatorActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

}