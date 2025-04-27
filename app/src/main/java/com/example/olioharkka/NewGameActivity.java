package com.example.olioharkka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewGameActivity extends AppCompatActivity {
    ImageView orangeChoice, greenChoice, pinkChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        orangeChoice = findViewById(R.id.orangeChoice);
        greenChoice = findViewById(R.id.greenChoice);
        pinkChoice = findViewById(R.id.pinkChoice);

        LutemonStorage.getInstance().clearLutemons(); // Clears the lutemon storage for a new game

        orangeChoice.setOnClickListener(v -> { // Orange lutemon is chosen
            LutemonStorage.getInstance().addLutemon(new LutemonOrange("Ossi Oranssi",1));
            Intent intent = new Intent(NewGameActivity.this, HomeActivity.class);
            startActivity(intent);
            HomeActivity.setActiveLutemon(LutemonStorage.getInstance().getLutemon(0));
            this.finish();
        });

        pinkChoice.setOnClickListener(v -> { // Pink lutemon is chosen
            LutemonStorage.getInstance().addLutemon(new LutemonPink("Pietu Pinkki",1));
            Intent intent = new Intent(NewGameActivity.this, HomeActivity.class);
            startActivity(intent);
            HomeActivity.setActiveLutemon(LutemonStorage.getInstance().getLutemon(0));
            this.finish();
        });

        greenChoice.setOnClickListener(v -> { // Green lutemon is chosen
            LutemonStorage.getInstance().addLutemon(new LutemonGreen("Ville Vihreä",1));
            Intent intent = new Intent(NewGameActivity.this, HomeActivity.class);
            startActivity(intent);
            HomeActivity.setActiveLutemon(LutemonStorage.getInstance().getLutemon(0));
            this.finish();
        });
        }
}