package com.tutorial.main;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Leaderboard {

    public Set<Integer> scores = new TreeSet<>();

    public Leaderboard() {
    }

    public void addScore(int score) {
        scores.add(score);
    }
}
