package com.example.olioharkka;

import java.util.ArrayList;

public class MonniStorage {
    private static MonniStorage instance = null;
    private ArrayList<Monni> monnit = new ArrayList<>();



    public static MonniStorage getInstance() {
        if (instance == null) {
            instance = new MonniStorage();
        }
        return instance;
    }

    public ArrayList<Monni> getMonnit() {
        return monnit;
    }
}
