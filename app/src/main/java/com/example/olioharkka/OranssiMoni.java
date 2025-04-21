package com.example.olioharkka;

public class OranssiMoni extends Moni {

    public OranssiMoni(String name, int ID, int lvl) {
        super(name, ID, lvl);
        this.hpmax = 17 + lvl;
        this.hp = 17 + lvl;
        this.ap = 8 + lvl;
        this.dp = 1 + lvl;
        this.xp = 0;
        this.color = "Oranssi";
    }
}
