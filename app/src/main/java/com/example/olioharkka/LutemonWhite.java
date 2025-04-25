package com.example.olioharkka;

public class LutemonWhite extends Lutemon {


    public LutemonWhite(String name, int lvl) {
        super(name, lvl);
        this.hpmax = 20 + lvl;
        this.hp = 20 + lvl;
        this.ap = 5 + lvl;
        this.dp = 4 + lvl;
        this.xp = 0;
        this.color = "Valkoinen";

        if (shiny == true){
            this.image = R.drawable.lutemonwhiteshiny;
        } else {
            this.image = R.drawable.lutemonwhite;
        }
    }
}
