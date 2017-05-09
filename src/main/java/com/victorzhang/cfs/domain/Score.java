package com.victorzhang.cfs.domain;

public enum Score {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5");

    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    Score(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return score;
    }
}
