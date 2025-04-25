package com.example.olioharkka;

public class LutemonRed extends Lutemon {
    public LutemonRed(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 15 + lvl;
        this.hp = 15 + lvl;
        this.ap = 11 + lvl;
        this.dp = 1 + lvl;
        this.xp = 0;
        this.color = "Punainen";

        if (shiny == true){ // Super harvinainen shiny punainen lutemon
            this.image = R.drawable.lutemonredshiny;
        } else {
            this.image = R.drawable.lutemonred;
        }
    }
}
