package com.example.olioharkka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
    Button editButton;
    EditText namePrompt;
    TextView promptText;
    View popupView;
    Button readyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);




        popupView = getLayoutInflater().inflate(R.layout.name_lutemon_prompt, null);

        promptText = popupView.findViewById(R.id.PromptText);
        namePrompt = popupView.findViewById(R.id.NamePrompt);
        readyButton = popupView.findViewById(R.id.ReadyButton);

        lutemonStorage = LutemonStorage.getInstance();

        recyclerView = findViewById(R.id.lutemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonViewAdapter(lutemonStorage.getLutemons(), this));


        nameText = findViewById(R.id.nameText);
        hpText = findViewById(R.id.hpText);
        statText = findViewById(R.id.statText);
        lvlText = findViewById(R.id.lvlText);
        lutemonImage = findViewById(R.id.LutemonImage);

        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() { // Pressing "muokkaa" button next to the name
            PopupWindow popupWindow = new PopupWindow(HomeActivity.this);

            @Override
            public void onClick(View view) {
                popupWindow.setContentView(popupView);
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(view);

                namePrompt.setText(activeLutemon.getName());
                namePrompt.setHint(activeLutemon.getName());
                namePrompt.requestFocus();

                readyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = namePrompt.getText().toString();
                        activeLutemon.setName(name);
                        setChosenInfo();
                        recyclerView.getAdapter().notifyDataSetChanged();

                        popupWindow.dismiss();
                    }
                });
            }
        });
        setChosenInfo();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        public static void setActiveLutemon(Lutemon lutemon) {
            activeLutemon = lutemon;
            //System.out.println("Aktiivisen lutemonin ID: " + activeLutemon.getID());
        }

    public void setChosenInfo() {
        nameText.setText(activeLutemon.getName());
        hpText.setText("HP: " + Integer.toString(activeLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));
        lvlText.setText("LVL: " + Integer.toString(activeLutemon.getLvl()));
        statText.setText("AP/DP: " + Integer.toString(activeLutemon.getAp()) + "/" + Integer.toString(activeLutemon.getDp()));
        lutemonImage.setImageResource(activeLutemon.getImage());
    }

    public int getActiveLutemonID() {
        return activeLutemon.getID();
    }

    public void switchToBattle(View view) {
        BattleActivity.setActiveLutemon(activeLutemon);
        startActivity(new Intent(this, BattleActivity.class));
        this.finish();
    }

    public void restLutemons(View view) {
        for (Lutemon lutemon : lutemonStorage.getLutemons()) {
            lutemon.refreshHp();
        }
        recyclerView.getAdapter().notifyDataSetChanged();
        hpText.setText("HP: " + Integer.toString(activeLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));

    }
}
