package com.example.olioharkka;

public class ValkoMoni extends Moni {


    public ValkoMoni(String name, int ID, int lvl) {
        super(name, ID, lvl);
        this.hpmax = 20 + lvl;
        this.hp = 20 + lvl;
        this.ap = 5 + lvl;
        this.dp = 4 + lvl;
        this.xp = 0;
        this.color = "Valkoinen";
    }
}
