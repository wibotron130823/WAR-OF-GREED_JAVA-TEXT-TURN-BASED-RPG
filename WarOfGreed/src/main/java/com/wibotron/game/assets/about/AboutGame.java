package com.wibotron.game.assets.about;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AboutGame {
    public static void showGameAbout() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\assets\\about\\about.txt"))) {
            String data = bufferedReader.readLine();
            System.out.println();
            while(!(data.equals("Creator:@Wibotron.ai"))) {
                System.out.println(data);
                data = bufferedReader.readLine();
            }
            System.out.println("\n");
        }
        catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
