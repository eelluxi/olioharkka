package com.example.olioharkka;

public abstract class Lutemon { // abstract base class for all lutemons

    protected int ID;
    protected int hpmax; // max health points
    protected int hp; //health points
    protected int ap; //attack points
    protected int dp; //defense points
    protected int xp; //experience points

    protected int xpmax; //max experience points
    protected int lvl; //level
    protected int image;
    protected String name;
    protected String color;
    public static int counter = 0;

    protected boolean shiny = false;


    public Lutemon(String name, int lvl) { // Gets name and lvl as parameters. Color is decided beforehand and dictates stats.
        counter++;
        this.ID = counter;

        this.name = name;

        //this.ID = ID; // MoniStorage.getInstance().getLutemons().size() + 1

        this.lvl = lvl;

        /* Tämä turhaa
        // Värittömän (yleinen) monin constructori. Alustetaan arvot varmuuden vuoksi nollaksi.
        this.hpmax = 0;
        this.hp = 0;
        this.ap = 0;
        this.dp = 0;
        this.xp = 0;
        this.color = "Colorless";
        */

        this.xpmax = 100 + (lvl * 10);

        if ((int)(Math.random()*100) <= 5) { // 5% chance for a lutemon to be shiny. Purely visual
            shiny = true;
        }

    }

    public void defend(Lutemon lutemon){
        this.hp = this.hp - (lutemon.attack() - dp);
        this.xp = xp + dp;  // defending gives xp-points
        levelup(); // checking if levelup is needed
    }

    public int attack(){
        int hit =(ap + (int)(Math.random() * (ap / 2))); // Adds a small random modifier to attack
        this.xp = this.xp + ap; // attacking gives xp-points
        levelup(); // checking if levelup is needed
        return hit;
    }
    public boolean isalive(){ // false if dead, true if alive.
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
        if (xp >= xpmax) {
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
