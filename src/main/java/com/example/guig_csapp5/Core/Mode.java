package com.example.guig_csapp5.Core;

import java.io.Serializable;

public class Mode implements Serializable {
    private Range range;
    private Difficulty difficult;
    private int tryCount;
    private int taskNumber;

    public Mode(Range range, Difficulty difficult){
        this.range=range;
        this.difficult=difficult;
        //Создаем количество попыток на основе сложности и диапозона
        this.tryCount=tryGenerate(range,difficult);
    }

    private int tryGenerate(Range range, Difficulty difficulty){
        //Определяемся с базовым коэффицентом решения по бинарному поиску
        int binarySearchCoeff=0;
        int tmp=0;

        switch (range){
            case TEN:
                binarySearchCoeff=4;
                break;
            case HUNDRED:
                binarySearchCoeff=7;
                break;
            case THOUSAND:
                binarySearchCoeff=10;
                break;
            case TENTHOUSAND:
                binarySearchCoeff=14;
                break;
        }


        switch (difficulty){
            case EASY:                 //Легкая сложность
                tmp=(int)Math.round(binarySearchCoeff*1.2);
                break;
            case MEDIUM:
                tmp=(binarySearchCoeff);
                break;           //Средняя сложность
            case HARD:
                tmp=(int)Math.round(binarySearchCoeff*0.7);
                break;           //Тяжелая сложность
            case IMPOSSIBLE:
                tmp=(int)Math.round(binarySearchCoeff*0.5);
                break;           //Невозможная сложность
        }
        return tmp;

    }


    @Override
    public String toString() {
        return "Режим игры{" +
                "Диапазон=" + range +
                ", Сложность=" + difficult +
                ", Число попыток=" + tryCount +
                ", Заданное число=" + taskNumber +
                '}';
    }

    public int getBound(){
        return this.range.getValue();
    }
    public int getTryCount(){
        return this.tryCount;
    }

    public Range getRange(){
        return this.range;
    }


    public Difficulty getDifficult(){
        return this.difficult;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }
}
