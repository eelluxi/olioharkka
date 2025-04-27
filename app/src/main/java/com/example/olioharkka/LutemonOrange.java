package com.example.olioharkka;

public class LutemonOrange extends Lutemon {

    public LutemonOrange(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 17 + lvl;
        this.hp = 17 + lvl;
        this.ap = 8 + lvl;
        this.dp = 1 + lvl;
        this.xp = 0;
        this.color = "oranssi";

        if (shiny == true) {
            this.image = R.drawable.lutemonorangeshiny;
        } else {
            this.image = R.drawable.lutemonorange;
        }
    }
}
