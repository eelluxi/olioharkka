package com.example.olioharkka;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity {
    private static Lutemon activeLutemon;
    private LutemonStorage lutemonStorage;
    private RecyclerView recyclerView;

    private TextView nameText, hpText, statText, lvlText;
    private ImageView lutemonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        lutemonStorage = LutemonStorage.getInstance();

        recyclerView = findViewById(R.id.lutemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonViewAdapter(lutemonStorage.getLutemons(), this));

        nameText = findViewById(R.id.nameText);
        hpText = findViewById(R.id.hpText);
        statText = findViewById(R.id.statText);
        lvlText = findViewById(R.id.lvlText);
        lutemonImage = findViewById(R.id.LutemonImage);
        setActiveLutemon(lutemonStorage.getLutemon(0));


        setChosenInfo();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public static void setActiveLutemon(Lutemon lutemon){
        activeLutemon = lutemon;
        System.out.println("Aktiivisen lutemonin ID: " + activeLutemon.getID());
    }

    public void setChosenInfo(){
        nameText.setText(activeLutemon.getName());
        hpText.setText("HP: " +Integer.toString(activeLutemon.getHpmax()) + "/" + Integer.toString(activeLutemon.getHp()));
        lvlText.setText("LVL: "+Integer.toString(activeLutemon.getLvl()));
        statText.setText("AP/DP: "+Integer.toString(activeLutemon.getAp()) + "/" + Integer.toString(activeLutemon.getDp()));
        lutemonImage.setImageResource(activeLutemon.getImage());
    }

    public int getActiveLutemonID(){
        return activeLutemon.getID();
    }

}