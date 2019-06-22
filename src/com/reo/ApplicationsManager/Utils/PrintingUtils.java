package com.reo.ApplicationsManager.Utils;

public class PrintingUtils {

    public static String returnLine(char ch, int amount){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void printLine(char ch, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(ch);
        }
    }

    public static void printLine(char ch, int amount, boolean newLine) {
        for (int i = 0; i < amount; i++) {
            System.out.print(ch);
        }
        if (newLine)
            System.out.println();
    }
}
