package com.dov.calculator.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.calculator.R;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter  extends RecyclerView.Adapter<HistoryViewHolder> {

    public interface OnDeleteButtonClicked {
        void delete(int position);
        void displayOperation(String operation);
    }

    private ArrayList<String> operations;
    private OnDeleteButtonClicked onDeleteButtonClicked;

    public HistoryRecyclerViewAdapter(ArrayList<String> operations, OnDeleteButtonClicked onDeleteButtonClicked) {
        this.operations = operations;
        this.onDeleteButtonClicked = onDeleteButtonClicked;
    }

    public void setOperations(ArrayList<String> operations) {
        this.operations = operations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(operations.get(position), position, onDeleteButtonClicked);
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }
}