package com.wibotron.game.utils;

import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);

    public static boolean decisionYesOrNo(String message) {
        System.out.print(message + ": ");
        String choice = scanner.next();
        while( !(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) ) {
            System.out.println("invalid input, please re-input [Y/y/N/n]: ");
            choice = scanner.next();
        }
        if(choice.equalsIgnoreCase("N")) {
            return false;
        }
        return choice.equalsIgnoreCase("Y");
    }
}
