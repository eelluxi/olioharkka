package com.example.olioharkka;

public class Monni {
    protected int hp;
    protected int ID;
    protected String name;

    public Monni(int hp, String name) {
        this.hp = hp;
        this.name = name;
        this.ID = MonniStorage.getInstance().getMonnit().size() + 1;
    }

}
