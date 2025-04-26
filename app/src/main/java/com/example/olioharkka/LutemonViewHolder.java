package com.example.olioharkka;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    TextView chooseText, releaseText;
    TextView nameText, lvlText, statText, hpText;
    ImageView lutemonImage;
    Button chooseButton, releaseButton;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        hpText = itemView.findViewById(R.id.hpText);
        statText = itemView.findViewById(R.id.statText);
        lvlText = itemView.findViewById(R.id.lvlText);
        lutemonImage = itemView.findViewById(R.id.LutemonImage);
        chooseButton = itemView.findViewById(R.id.chooseButton);
        releaseButton = itemView.findViewById(R.id.releaseButton);

    }
}
