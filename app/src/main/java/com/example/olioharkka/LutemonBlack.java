package com.example.olioharkka;

public class LutemonBlack extends Lutemon {
    public LutemonBlack(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 16 + lvl;
        this.hp = 16 + lvl;
        this.ap = 9 + lvl;
        this.dp = 0 + lvl;
        this.xp = 0;
        this.color = "musta";

        if (shiny == true){
            this.image = R.drawable.lutemonblackshiny;
        } else {
            this.image = R.drawable.lutemonblack;
        }
    }

}
