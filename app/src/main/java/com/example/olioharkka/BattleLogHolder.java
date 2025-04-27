package com.example.olioharkka;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BattleLogHolder extends RecyclerView.ViewHolder {

    TextView logText;
    public BattleLogHolder(@NonNull View itemView) {
        super(itemView);
        logText = itemView.findViewById(R.id.logText);

    }
}
