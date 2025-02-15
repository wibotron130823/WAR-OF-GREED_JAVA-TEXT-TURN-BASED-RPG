package com.wibotron.game.assets.display;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MenuDisplay {

    public static void mainMenutTextDisplay() {
        System.out.println(" ============ WAR-OF-GREED ============");
        try {
            FileReader fr = new FileReader("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\assets\\display\\menu_text_display.txt");
            BufferedReader br = new BufferedReader(fr);
            String data = br.readLine();
            while(data!=null) {
                System.out.println(data);
                data = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
