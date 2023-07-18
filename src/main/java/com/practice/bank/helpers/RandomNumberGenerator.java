package com.practice.bank.helpers;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random RANDOM = new Random();

    public static String generateNumber(int length){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++){
            int digit = RANDOM.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
