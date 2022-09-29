package com.example.guig_csapp5.Core;

public class DefinerNumber {
    //Проверить число на вхождение в границы
    public static boolean checkNumberBoundaries(String text, Mode mode){
        int number=Integer.parseInt(text);
        return number >= 0 && number <= mode.getBound();

    }
}
