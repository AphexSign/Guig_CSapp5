package com.example.guig_csapp5.Core;

public enum Range {TEN("[1-10]",10), HUNDRED("[1-100]",100),THOUSAND("[1-1000]",1000),TENTHOUSAND("[1-10000]",10000);

    private String description;
    private int value;

    private Range(String description, int value){
        this.description=description;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return description;
    }
}
