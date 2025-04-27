package com.example.olioharkka;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {

    private LogStorage battleLog;
    private static Lutemon activeLutemon;
    private Lutemon enemyLutemon;
    private LutemonStorage lutemonStorage;
    private RecyclerView recyclerView;
    private int enemySeed, enemyLvl, enemyLvlSeed, captureHp, dmg;
    private TextView nameText, hpText, statText, lvlText;
    private TextView enemynameText, enemyhpText, enemystatText, enemylvlText;
    private ImageView lutemonImage, enemyLutemonImage;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);

        lutemonStorage = LutemonStorage.getInstance();
        battleLog = LogStorage.getInstance();
        battleLog.clearLog();

        recyclerView = findViewById(R.id.LogBox);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BattleLogAdapter(battleLog.getBattleLog(), this));

        nameText = findViewById(R.id.nameText);
        hpText = findViewById(R.id.hpText);
        statText = findViewById(R.id.statText);
        lvlText = findViewById(R.id.lvlText);
        lutemonImage = findViewById(R.id.LutemonImage);

        enemynameText = findViewById(R.id.enemynameText);
        enemyhpText = findViewById(R.id.enemyhpText);
        enemystatText = findViewById(R.id.enemystatText);
        enemylvlText = findViewById(R.id.enemylvlText);
        enemyLutemonImage = findViewById(R.id.enemyLutemonImage);

        setChosenInfo();
        generateLutemon();

        // The battle starts
        battleLog.addText("Villi "+enemyLutemon.getColor()+" Lutemon ilmestyi. Taistelu alkoi!");

        if (enemyLutemon.isShiny() == true) {// Wild lutemon is shiny
            battleLog.addText("Villi lutemon kimaltelee!");
        }
        if (activeLutemon.isAlive()) {
            battleLog.addText("");
            battleLog.addText("Sinun vuorosi!");
        } else { // Your lutemon is already fainted
            battleLog.addText("Lähdit taisteluun pyörtyneellä lutemonilla! Palaa takaisin kotiin lepäämään.");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static void setActiveLutemon(Lutemon lutemon) {
        BattleActivity.activeLutemon = lutemon;
    }

    public void setChosenInfo(){// Setting active lutemon's info
        nameText.setText(activeLutemon.getName());
        hpText.setText("HP: " +Integer.toString(activeLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));
        lvlText.setText("LVL: "+Integer.toString(activeLutemon.getLvl()));
        statText.setText("AP/DP: "+Integer.toString(activeLutemon.getAp()) + "/" + Integer.toString(activeLutemon.getDp()));
        lutemonImage.setImageResource(activeLutemon.getImage());
    }

    private void generateLutemon(){
        enemySeed  = (int)(Math.random()*100); // Generating seed for wild lutemon

        // Generating level. If your lutemon's lvl is 1, the wild lutemon's lvl is 1. Otherwise wild lutemon's lvl is your lutemon's lvl +-1.
        if (activeLutemon.getLvl() < 2) {
            enemyLvl = 1;
        } else{
            enemyLvlSeed = (int)(Math.random()*100);
            if (enemyLvlSeed <= 33){
                enemyLvl = activeLutemon.getLvl() - 1;
            } else if (enemyLvlSeed >= 66) {
                enemyLvl = activeLutemon.getLvl() + 1;
            }else {
                enemyLvl = activeLutemon.getLvl();
            }
            
        }

        // Selecting lutemon type from seed
        if (enemySeed <= 5) {
            enemyLutemon = new LutemonRed("Pauli Punainen",enemyLvl);
        } else if (enemySeed <= 15) {
            enemyLutemon = new LutemonBlack("Milla Musta",enemyLvl);
        } else if (enemySeed <= 40) {
            enemyLutemon = new LutemonOrange("Ossi Oranssi",enemyLvl);
        } else if (enemySeed <= 65) {
            enemyLutemon = new LutemonPink("Pietu Pinkki",enemyLvl);
        } else if (enemySeed <= 90) {
            enemyLutemon = new LutemonGreen("Ville Vihreä",enemyLvl);
        } else {
            enemyLutemon = new LutemonWhite("Vallu Valkoinen",enemyLvl);
        }

        captureHp = (int)(enemyLutemon.getHpmax() / 5); // Creating capture hp threshold

        //Setting stats visible
        enemynameText.setText(enemyLutemon.getName());
        enemyhpText.setText("HP: " +Integer.toString(enemyLutemon.getHp()) + "/" + Integer.toString(enemyLutemon.getHpmax()));
        enemylvlText.setText("LVL: "+Integer.toString(enemyLutemon.getLvl()));
        enemystatText.setText("AP/DP: "+Integer.toString(enemyLutemon.getAp()) + "/" + Integer.toString(enemyLutemon.getDp()));
        enemyLutemonImage.setImageResource(enemyLutemon.getImage());

    }

    public void battle(View view){ // Pressing "Hyökkää!" button


        if (activeLutemon.isAlive() && enemyLutemon.isAlive()) { // Both lutemons are alive

            dmg = activeLutemon.attack();
            battleLog.addText("Oma lutemon "+activeLutemon.getName()+" löi voimalla "+dmg+".");
            enemyLutemon.defend(dmg);
            battleLog.addText("Villi lutemon "+enemyLutemon.getName() + " puolustautui dp-voimalla "+enemyLutemon.getDp()+". Villillä lutemonilla on nyt "+enemyLutemon.getHp()+"hp.");
            enemyhpText.setText("HP: " + Integer.toString(enemyLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));

            if (enemyLutemon.isAlive() == true) { // Your attack didn't kill enemy lutemon
                battleLog.addText("");
                battleLog.addText("Vihollisen vuoro!");

                dmg = enemyLutemon.attack();
                battleLog.addText("Villi lutemon "+enemyLutemon.getName()+" löi voimalla "+dmg+".");
                activeLutemon.defend(dmg);
                battleLog.addText("Oma lutemon "+activeLutemon.getName() + " puolustautui dp-voimalla "+activeLutemon.getDp()+". Omalla lutemonilla on nyt "+activeLutemon.getHp()+"hp.");
                hpText.setText("HP: " + Integer.toString(activeLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));
            }

            if ((activeLutemon.isAlive() == true) && enemyLutemon.isAlive() == false) { // Wild lutemon fainted from your attack
                activeLutemon.gainxp(enemyLutemon.getLvl() * 40);
            }

            enemyhpText.setText("HP: " + Integer.toString(enemyLutemon.getHp()) + "/" + Integer.toString(enemyLutemon.getHpmax()));
            hpText.setText("HP: " + Integer.toString(activeLutemon.getHp()) + "/" + Integer.toString(activeLutemon.getHpmax()));

            if (activeLutemon.isAlive() == false) { // Your lutemon fainted
                battleLog.addText("");
                battleLog.addText("Oma Lutemon " + activeLutemon.getName() + " pyörtyi!");
            } else if (enemyLutemon.isAlive() == false) { // Wild lutemon fainted
                battleLog.addText("");
                battleLog.addText("Villi Lutemon " + enemyLutemon.getName() + " pyörtyi!");
                lvlText.setText("LVL: "+Integer.toString(activeLutemon.getLvl()));
                statText.setText("AP/DP: "+Integer.toString(activeLutemon.getAp()) + "/" + Integer.toString(activeLutemon.getDp()));
            } else { // Both lutemons are alive
                battleLog.addText("");
                battleLog.addText("Sinun vuorosi!");
            }

        } else { // A lutemon is fainted
            battleLog.addText("");
            battleLog.addText("Taistelu on jo päättynyt.");
        }

        recyclerView.getAdapter().notifyDataSetChanged();
    }
    public void escape(View view){ // Pressing "Takaisin" button
        startActivity(new Intent(this, HomeActivity.class));
        HomeActivity.setActiveLutemon(activeLutemon);
        this.finish();
    }

    public void capture(View view){ // Pressing "Vangitse" button
        battleLog.addText("");
        battleLog.addText("Yritetään vangita villi lutemon.");

        if (enemyLutemon.isAlive() == false) { // Wild lutemon is fainted
            battleLog.addText("Et voi vangita pyörtynyttä lutemonia!");
        } else if (activeLutemon.isAlive() == false) { // Your lutemon is fainted
            battleLog.addText("Et voi vangita pyörtyneellä lutemonlilla!");
            battleLog.addText("");
        } else if (enemyLutemon.getHp() > captureHp){ // Hp threshold not met
            battleLog.addText("Villi lutemon on liian terve vangittavaksi. Villillä lutemonilla on oltava "+ captureHp +"hp.");
        } else if (LutemonStorage.getInstance().getLutemons().size() >= 10) { // Storage is full
            battleLog.addText("Lutemon varasto täynnä! Voit omistaa korkeintaan 10 Lutemonia.");
        } else { // Adding captured lutemon to storage and returning Home
            LutemonStorage.getInstance().addLutemon(enemyLutemon);
            startActivity(new Intent(this, HomeActivity.class));
            HomeActivity.setActiveLutemon(activeLutemon);
            this.finish();
        }

        recyclerView.getAdapter().notifyDataSetChanged();

    }
}