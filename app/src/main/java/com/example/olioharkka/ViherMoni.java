package com.example.olioharkka;

public class ViherMoni extends Moni{
    public ViherMoni(String name, int ID, int lvl) {
        super(name, ID, lvl);
        this.hpmax = 19 + lvl;
        this.hp = 19 + lvl;
        this.ap = 6 + lvl;
        this.dp = 3 + lvl;
        this.xp = 0;
        this.color = "Vihreä";
    }
}
