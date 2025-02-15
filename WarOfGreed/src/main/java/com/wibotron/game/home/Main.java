package com.wibotron.game.home;
import com.wibotron.game.assets.audio.GameSound;
import com.wibotron.game.logic.match.GameFlow;
import static com.wibotron.game.assets.about.AboutGame.showGameAbout;
import static com.wibotron.game.assets.display.MenuDisplay.mainMenutTextDisplay;
import static com.wibotron.game.utils.Utils.decisionYesOrNo;
import static com.wibotron.game.utils.Utils.scanner;

public class Main {

    public static void main(String[] args) {
        boolean isContinue = true;
        GameSound gameSound = new GameSound();
        GameFlow gameFlow = new GameFlow();
        while(isContinue) {
            mainMenutTextDisplay();
            System.out.println("1. start game\n2. about\n3. audio setting\n4. exit game");
            System.out.print("choose by index [1-6]: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    gameFlow.start();
                    break;
                case 2:
                    showGameAbout();
                    break;
                case 3:
                    boolean isAudioSetting = true;
                    System.out.println("\nAUDIO SOURCE: Avabel Online MMORPG\n1. play sound\n2. stop sound\n");
                    System.out.print("choose by index [1 or 2]: ");
                    int audioSettingChoice = scanner.nextInt();
                    while(isAudioSetting) {
                        switch (audioSettingChoice) {
                            case 1:
                                gameSound.playSound();
                                break;
                            case 2:
                                gameSound.stopSound();
                                break;
                            default:
                                audioSettingChoice = scanner.nextInt();
                        }
                        isAudioSetting = false;
                    }
                    break;
                case 4:
                    gameSound.stopSound();
                    isContinue = false;
                    break;
                default:
                    System.out.print("invalid input, please re-choose by index [1-5]: ");
                    choice = scanner.nextInt();
            }
            isContinue = decisionYesOrNo("choose 'Y/y' to go to main menu or choose 'N/n' for exit: ");
        }
    }
}
