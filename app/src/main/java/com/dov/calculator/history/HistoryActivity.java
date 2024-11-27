package com.dov.calculator.history;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.calculator.data.ApplicationData;
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
        setTitle("Historique");
        recyclerView = findViewById(R.id.history_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(ApplicationData.getInstance().getOperationsHistory(), new HistoryRecyclerViewAdapter.OnDeleteButtonClicked() {
            @Override
            public void delete(int position) {
                ApplicationData.getInstance().getOperationsHistory().remove(position);
                historyRecyclerViewAdapter.setOperations(ApplicationData.getInstance().getOperationsHistory());
            }
        });
        recyclerView.setAdapter(historyRecyclerViewAdapter);
    }
}