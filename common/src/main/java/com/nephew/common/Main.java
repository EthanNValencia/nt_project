package com.nephew.common;

// Just a little question I had in my mind. Not really related to project.
public class Main {
    private static double population = 8;

    public static void main(String[] args) {
        int year = 100;
        for(int i = year; i >= 0; i--) {
            double rate = 0.01;
            double yearlyIncrease = population * rate;
            population += yearlyIncrease;
            System.out.println("Increase: " + yearlyIncrease + " New Population: " + population);
        }
        population = (population * 100);
        System.out.println("Population: " + (int) population);
        // 2,147,483,647
    }
}
