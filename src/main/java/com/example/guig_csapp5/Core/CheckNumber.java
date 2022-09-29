package com.example.guig_csapp5.Core;

public class CheckNumber {

    public static boolean check(String text){
        int myNumber=Integer.parseInt(text);
        return myNumber == GlobalVar.NUMBER_RECEIVED;
    }

}
