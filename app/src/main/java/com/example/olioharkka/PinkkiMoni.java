package com.example.olioharkka;

public class PinkkiMoni extends Moni {
    public PinkkiMoni(String name, int ID, int lvl) {
        super(name, ID, lvl);
        this.hpmax = 18 + lvl;
        this.hp = 18 + lvl;
        this.ap = 7 + lvl;
        this.dp = 2 + lvl;
        this.xp = 0;
        this.color = "Pinkki";
    }
}

