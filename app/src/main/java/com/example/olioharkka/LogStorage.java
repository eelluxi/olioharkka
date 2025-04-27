package com.example.olioharkka;

import java.util.ArrayList;

public class LogStorage {
    private static LogStorage instance = null;
    private ArrayList<String> battleLog = new ArrayList<>();

    public static LogStorage getInstance() {
        if (instance == null) {
            instance = new LogStorage();
        }
        return instance;
    }

    public ArrayList<String> getBattleLog() {
        return battleLog;
    }

    public void addText(String text) {
        if (battleLog.size() > 10) { // Making space if log is full
            battleLog.remove(0);
        }
        battleLog.add(text);
    }

    public void clearLog() {
        battleLog.clear();
    }

}
