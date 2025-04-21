package com.example.olioharkka;

public class Moni {

    protected int ID;
    protected int hpmax; // max health points
    protected int hp; //health points
    protected int ap; //attack points
    protected int dp; //defense points
    protected int xp; //experience points

    protected int xpmax; //max experience points
    protected int lvl; //level
    protected String name;
    protected String color;


    public Moni(String name, int ID, int lvl) {
        // Saa parametreina nimen, ID:n ja lvl:n. Väri valitaan satunnaisesti taistelukohtaamisessa.
        this.name = name;
        this.ID = ID; // MoniStorage.getInstance().getMonnit().size() + 1
        this.lvl = lvl;

        // Värittömän (yleinen) monin constructori. Alustetaan arvot varmuuden vuoksi nollaksi.
        this.hpmax = 0;
        this.hp = 0;
        this.ap = 0;
        this.dp = 0;
        this.xp = 0;

        this.xpmax = 100 + (lvl * 10);
        this.color = "Colorless";


    }

    public void defend(Moni moni){
        this.hp = this.hp - (moni.attack() - dp);
        this.xp = xp + dp;  //puolustuksesta saa xp-arvoa
        levelup(); // tarkistetaan, riittääkö uusi xp-arvo tason nousemiseen
    }

    public int attack(){
        int hit =(ap + (int)(Math.random() * (ap / 2))); //Lisätään pieni satunnaisuus hyökkäykseen
        this.xp = this.xp + ap; // hyökkäyksestä saa xp-arvoa
        levelup(); // tarkistetaan, riittääkö uusi xp-arvo tason nousemiseen
        return hit;
    }
    public boolean isalive(){ // tarkistetaan onko elossa
        if (hp <= 0) {
            return false;
        }
        return true;
    }
    public void gainxp(int xp){
        this.xp = this.xp + xp;
        levelup();
    }

    public void levelup(){
        if (xp >= xpmax) { // verrataan nykyistä xp-arvoa ja maksimiarvoa tason nousemista varten
            xp = xp - xpmax;
            xpmax = xpmax + 10;
            lvl = lvl + 1;
            hpmax = hpmax + 1;
            hp = hp + 1;
            ap = ap + 1;
            dp = dp + 1;
        }
    }

    }
