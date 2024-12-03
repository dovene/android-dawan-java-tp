package com.dov.calculator.history;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.calculator.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    TextView operationTextView;
    ImageView deleteImageView;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        operationTextView = itemView.findViewById(R.id.operation_tv);
        deleteImageView = itemView.findViewById(R.id.delete_iv);
    }

    public void bind(String operation, int position, HistoryRecyclerViewAdapter.OnDeleteButtonClicked onDeleteButtonClicked) {
        operationTextView.setText(operation);
        deleteImageView.setOnClickListener(v -> {
            onDeleteButtonClicked.delete(position);
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClicked.displayOperation(operation);
            }
        });
    }
}