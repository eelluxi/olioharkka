package com.example.olioharkka;

public class MustaMoni extends Moni{


    public MustaMoni(String name, int ID, int lvl) {
        super(name, ID, lvl);
        this.hpmax = 16 + lvl;
        this.hp = 16 + lvl;
        this.ap = 9 + lvl;
        this.dp = 0 + lvl;
        this.xp = 0;
        this.color = "Musta";
    }

}
