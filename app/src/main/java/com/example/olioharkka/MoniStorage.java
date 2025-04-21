package com.example.olioharkka;

import java.util.ArrayList;

public class MoniStorage {
    private static MoniStorage instance = null;
    private ArrayList<Moni> monnit = new ArrayList<>();



    public static MoniStorage getInstance() {
        if (instance == null) {
            instance = new MoniStorage();
        }
        return instance;
    }

    public ArrayList<Moni> getMonnit() {
        return monnit;
    }
    public void addMonni (Moni monni) {
        monnit.add(monni);
    }
    public void removeMonni(int index) {
        monnit.remove(index);
    }

}
