package com.example.olioharkka;

public class LutemonGreen extends Lutemon {
    public LutemonGreen(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 19 + lvl;
        this.hp = 19 + lvl;
        this.ap = 6 + lvl;
        this.dp = 3 + lvl;
        this.xp = 0;
        this.color = "vihreä";

        if (shiny == true){
            this.image = R.drawable.lutemongreenshiny;
        } else {
            this.image = R.drawable.lutemongreen;
        }
    }

}
