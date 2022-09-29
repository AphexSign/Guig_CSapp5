package com.example.guig_csapp5.Core;

public enum Difficulty {
    EASY(1),
    MEDIUM(4),
    HARD(8),
    IMPOSSIBLE(16);


    private int value;

    private Difficulty(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }







}
