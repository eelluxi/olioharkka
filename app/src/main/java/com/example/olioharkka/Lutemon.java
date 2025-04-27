package com.example.olioharkka;

import android.util.Log;

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
    protected int shinySeed;
    protected String name;
    protected String color;
    public static int counter = 0;

    protected boolean shiny = false;


    public Lutemon(String name, int lvl) { // Gets name and lvl as parameters. Color is decided beforehand and dictates stats.
        counter++;
        this.ID = counter;
        this.name = name;
        this.lvl = lvl;
        this.shinySeed = (int)(Math.random()*100);
        this.xpmax = 100 + (lvl * 10);
        if (shinySeed <= 5) { // 5% chance for a lutemon to be shiny. Purely visual
            shiny = true;
        }

    }

    public void defend(int dmg){
        this.hp = this.hp - (dmg - dp);

        this.xp = xp + dp;  // defending gives xp-points
    }

    public int attack(){
        int hit =(ap + (int)(Math.random() * (ap / 2))); // Adds a small random modifier to attack
        this.xp = this.xp + ap; // attacking gives xp-points
        return hit;
    }
    public boolean isAlive(){ // false if dead, true if alive.
        if (hp <= 0) {
            return false;
        }
        return true;
    }
    public void gainxp(int xp){
        this.xp = this.xp + xp;
        LogStorage.getInstance().addText("Oma lutemon "+name+" sai "+xp+" xp-pisteitä!");
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
            LogStorage.getInstance().addText("Oma lutemon "+name+" on nyt lvl "+lvl+"!");
        }
    }

    public int getID() {
        return ID;
    }
    public int getHpmax() {
        return hpmax;
    }

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public int getDp() {
        return dp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void refreshHp(){
        hp = hpmax;
    }

    public boolean isShiny() {
        return shiny;
    }

}
