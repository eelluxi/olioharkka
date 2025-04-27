package com.example.olioharkka;

public class LutemonPink extends Lutemon {
    public LutemonPink(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 18 + lvl;
        this.hp = 18 + lvl;
        this.ap = 7 + lvl;
        this.dp = 2 + lvl;
        this.xp = 0;
        this.color = "pinkki";

        if (shiny == true) {
            this.image = R.drawable.lutemonpinkshiny;
        } else {
            this.image = R.drawable.lutemonpink;
        }
    }
}

