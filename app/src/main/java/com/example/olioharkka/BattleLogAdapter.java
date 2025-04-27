package com.example.olioharkka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleLogAdapter extends RecyclerView.Adapter<BattleLogHolder> {
    private Context context;
    private ArrayList<String> battleLog;

    public BattleLogAdapter(ArrayList<String> battleLog, Context context) {
        this.battleLog = battleLog;
        this.context = context;
    }

    @NonNull
    @Override
    public BattleLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BattleLogHolder(LayoutInflater.from(context).inflate(R.layout.battlelog_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BattleLogHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.logText.setText(battleLog.get(pos).toString());
    }

    @Override
    public int getItemCount() {
        return battleLog.size();
    }
}
