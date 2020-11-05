package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        char lastChar = str.charAt(0);
        int lastNumber = 0;
        for (char c : str.toCharArray()) {
            if(c == lastChar){
                lastNumber++;
            }else{
                if(lastNumber>1){
                    stringBuilder.append(lastNumber);
                    lastNumber =1;
                }
                stringBuilder.append(lastChar);
                lastChar =c;
            }

        }
        if(lastNumber>1){
            stringBuilder.append(lastNumber);
        }
        stringBuilder.append(lastChar);
        System.out.println(stringBuilder.toString());

    }

}
