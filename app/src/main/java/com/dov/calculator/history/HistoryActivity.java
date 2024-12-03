package com.dov.calculator.history;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.calculator.ApplicationData;
import com.dov.calculator.R;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setViewItems();
    }

    private void setViewItems() {
        findViewById(R.id.close_bt).setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.history_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(ApplicationData.getInstance().getOperationsHistory(), new HistoryRecyclerViewAdapter.OnDeleteButtonClicked() {
            @Override
            public void delete(int position) {
                ApplicationData.getInstance().getOperationsHistory().remove(position);
                historyRecyclerViewAdapter.setOperations(ApplicationData.getInstance().getOperationsHistory());
            }

            @Override
            public void displayOperation(String operation) {
                Toast.makeText(HistoryActivity.this, operation, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(historyRecyclerViewAdapter);
    }
}