package com.example.olioharkka;

import java.util.ArrayList;

public class LutemonStorage {
    private static LutemonStorage instance = null;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();



    public static LutemonStorage getInstance() {
        if (instance == null) {
            instance = new LutemonStorage();
        }
        return instance;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }
    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
        LogStorage.getInstance().addText("Lutemon " + lutemon.getName() + " lisätty!");
    }
    public void removeLutemon(int index) {
        lutemons.remove(index);
    }
    public Lutemon getLutemon(int index) {
        return lutemons.get(index);
    }

    public void clearLutemons() {
        lutemons.clear();
    }

}
