package com.wibotron.game.logic.match;
import com.wibotron.game.logic.hero.*;
import com.wibotron.game.logic.team.BattleSpell;
import com.wibotron.game.logic.team.Team;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import static com.wibotron.game.logic.team.Team.cleanHeroDatabase;
import static com.wibotron.game.utils.Utils.decisionYesOrNo;
import static com.wibotron.game.utils.Utils.scanner;

public class GameFlow {
    Team team1 = new Team();
    Team team2 = new Team();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private String startedMatchTime;
    private String finishedMatchTime;

    Random random = new Random();

    public GameFlow() {
        team1.setTeamName("PLAYER 1");
        team2.setTeamName("PLAYER 2");
    }

    public void start() {
        cleanHeroDatabase("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
        cleanHeroDatabase("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
        vsMode();
    }
    private void finishedTimeOfMatchInformation() {
        this.finishedTime = LocalDateTime.now();
        this.finishedMatchTime = this.finishedTime.format(dateTimeFormatter);
        System.out.printf("MATCH STARTED  TIME : %s\n", this.startedMatchTime);
        System.out.printf("MATCH FINISHED TIME : %s\n", this.finishedMatchTime);
        Duration duration = Duration.between(this.startedTime, this.finishedTime);
        System.out.printf("%s minutes\n", duration.toMinutes());
    }
    private void startedTimeOfMatchInformation() {
        this.startedTime = LocalDateTime.now();
        this.startedMatchTime = this.startedTime.format(dateTimeFormatter);
    }
    public void vsMode() {
        System.out.println("choose battle mode:\n1. vs Player\n2. vs COM");
        System.out.print("choose battle mode by index [1 or 2]: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                combatModeVsPlayer();
                displayChosenHeroes();
                displayChosenSpell();
                vsPlayerBattlingProcess();
                break;
            case 2:
                combatModeVsComputer();
                displayChosenHeroes();
                displayChosenSpell();
                vsComputerBattlingProcess();
                break;
            default:
                System.out.println("invalid input, please re-choose correctly");
                vsMode();
        }
    }
    void vsPlayerBattlingProcess() {
        startedTimeOfMatchInformation();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BATTLING ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(!team1.isTeamDefeated() && !team2.isTeamDefeated()) {
            turnHeroForNonComputer(team1, team2); //p1 menyerang p2
            if(!team2.isTeamDefeated()) {
                turnHeroForNonComputer(team2, team1); //p2 menyerang p1
            }
        }
        if(team2.isTeamDefeated()) {
            System.out.println("\nTEAM 1 WIN !");
        }
        else {
            System.out.println("\nTEAM 2 WIN !");
        }
        finishedTimeOfMatchInformation();
    }
    void vsComputerBattlingProcess() {
        startedTimeOfMatchInformation();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BATTLING ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(!team1.isTeamDefeated() && !team2.isTeamDefeated()) {
            turnHeroForNonComputer(team1, team2); //p1 menyerang p2
            if(!team2.isTeamDefeated()) {
                turnHeroForComputer(team2, team1); //p2 menyerang p1
            }
        }
        if(team2.isTeamDefeated()) {
            System.out.println("\nPlayer 1 WIN !");
        }
        else {
            System.out.println("\nPlayer 2 WIN !");
        }
        finishedTimeOfMatchInformation();
    }
    public void displayChosenHeroes() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CHOSEN HEROES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Player 1 Team: ");
        System.out.println(team1.toString());
        System.out.println("Player 2 Team: ");
        System.out.println(team2.toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    public void displayChosenSpell() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CHOSEN SPELL~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Player 1 Spell: ");
        team1.showTeamBattleSpell();
        System.out.println("Player 2 Spell: ");
        team2.showTeamBattleSpell();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    public void combatModeVsPlayer() {
        System.out.println("choose combat mode: ");
        System.out.println("1. 3 vs 3\n2. 5 vs 5");
        System.out.print("choose by index [1 or 2]: ");
        int choice = scanner.nextInt();
        int randomFirstChooser = random.nextInt(2);
        switch(choice) {
            case 1:
                switch (randomFirstChooser) {
                    case 0:
                        draftPickStartForVsPlayer(team1, team2, 3, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt");
                        break;
                    case 1:
                        draftPickStartForVsPlayer(team2, team1, 3, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
                        break;
                }
                break;
            case 2:
                switch (randomFirstChooser) {
                    case 0:
                        draftPickStartForVsPlayer(team1, team2, 5, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt");
                        break;
                    case 1:
                        draftPickStartForVsPlayer(team2, team1, 5, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
                        break;
                }
                break;
            default:
                System.out.println("invalid input, please re-choose correctly");
                combatModeVsPlayer();
        }
    }
    public void combatModeVsComputer() {
        System.out.println("choose combat mode: ");
        System.out.println("1. 3 vs 3\n2. 5 vs 5");
        System.out.print("choose by index [1 or 2]: ");
        int choice = scanner.nextInt();
        int randomFirstChooser = random.nextInt(2);
        switch(choice) {
            case 1:
                switch (randomFirstChooser) {
                    case 0:
                        draftPickStartForVsComputer(team1, team2, 3, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt");
                        break;
                    case 1:
                        draftPickStartForVsComputer(team2, team1, 3, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
                        break;
                }
                break;
            case 2:
                switch (randomFirstChooser) {
                    case 0:
                        draftPickStartForVsComputer(team1, team2, 5, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt");
                        break;
                    case 1:
                        draftPickStartForVsComputer(team2, team1, 5, "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team2.txt", "D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\chosen_hero_team1.txt");
                        break;
                }
                break;
            default:
                System.out.println("invalid input, please re-choose correctly");
                combatModeVsComputer();
        }
    }
    private void draftPickStartForVsPlayer(Team firstTeam, Team secondTeam, int maxChoice, String first_team_db, String second_team_db) {
        System.out.println("\nFIRST PICK GOES TO " + firstTeam.getTeamName());
        firstTeam.readAndWriteHeroesChosenDatabase(first_team_db, maxChoice);
        for (int i = 0; i < maxChoice; i++) {
            firstTeam.addHero(chooseHeroes(first_team_db, i));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("\n========================= PICK SPELLS =========================");
            firstTeam.addSpell(chooseSpells());
        }
        secondTeam.readAndWriteHeroesChosenDatabase(second_team_db, maxChoice);
        for (int i = 0; i < maxChoice; i++) {
            secondTeam.addHero(chooseHeroes(second_team_db, i));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("\n========================= PICK SPELLS =========================");
            secondTeam.addSpell(chooseSpells());
        }
        firstTeam.twoSpells();
        secondTeam.twoSpells();
    }
    private void draftPickStartForVsComputer(Team firstTeam, Team secondTeam, int maxChoice, String first_team_db, String second_team_db) {
        System.out.println("\nFIRST PICK GOES TO " + firstTeam.getTeamName());
        if(firstTeam.getTeamName().equals("PLAYER 1")) {
            firstTeam.readAndWriteHeroesChosenDatabase(first_team_db, maxChoice);
        } else {
            firstTeam.readAndWriteHeroesChosenDatabaseRandomly(first_team_db, maxChoice);
        }
        for (int i = 0; i < maxChoice; i++) {
            firstTeam.addHero(chooseHeroes(first_team_db, i));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("\n========================= PICK SPELLS ==========================");
            if(firstTeam.getTeamName().equals("PLAYER 1")) {
                firstTeam.addSpell(chooseSpells());
            } else {
                firstTeam.addSpell(chooseSpellsRandomly());
            }
        }
        if(secondTeam.getTeamName().equals("PLAYER 2")) {
            secondTeam.readAndWriteHeroesChosenDatabaseRandomly(second_team_db, maxChoice);
        } else {
            secondTeam.readAndWriteHeroesChosenDatabase(second_team_db, maxChoice);
        }
        for (int i = 0; i < maxChoice; i++) {
            secondTeam.addHero(chooseHeroes(second_team_db, i));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("\n========================= PICK SPELLS ==========================");
            if(secondTeam.getTeamName().equals("PLAYER 2")) {
                secondTeam.addSpell(chooseSpellsRandomly());
            } else {
                secondTeam.addSpell(chooseSpells());
            }
        }
        firstTeam.twoSpells();
        secondTeam.twoSpells();
    }
    private void turnHeroForNonComputer(Team atkTeam, Team defTeam) {
        boolean isTurnSkipped = onlyOnePoisonedLeft(atkTeam);
        if (isTurnSkipped) {return;}
        Hero attacker = selectHeroTurn(atkTeam);
        heroTurnConditionForNonComputer(attacker, atkTeam);
        Hero target;
        switch (attacker.getType()) {
            case "Attacker":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. attack all heroes of opponent team");
                System.out.print("select action by index: ");
                switch (scanner.nextInt()) {
                    case 1:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 2:
                        ((Attacker) attacker).attackOpponentTeam(defTeam.getHeroes());
                        break;
                }
                break;
            case "Tanker":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. protect teammate\n3. protect all heroes of your team");
                System.out.print("select action by index: ");
                switch (scanner.nextInt()) {
                    case 1:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 2:
                        target = selectTarget(atkTeam);
                        targetConditionForNonComputer(target, atkTeam);
                        ((Tanker) attacker).protect(target);
                        break;
                    case 3:
                        ((Tanker) attacker).protectTeam(atkTeam.getHeroes());
                        break;
                }
                break;
            case "Healer":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. heal teammate\n3. heal all heroes of your team");
                System.out.print("select action by index: ");
                switch (scanner.nextInt()) {
                    case 1:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 2:
                        target = selectTarget(atkTeam);
                        targetConditionForNonComputer(target, atkTeam);
                        ((Healer) attacker).heal(target);
                        break;
                    case 3:
                        ((Healer) attacker).healTeam(atkTeam.getHeroes());
                        break;
                }
                break;
            case "Buffer":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. buff teammate's attack\n3. debuff opponent's attack\n4. buff teammate's defense\n5. debuff opponent's defense");
                System.out.print("select action by index: ");
                switch (scanner.nextInt()) {
                    case 1:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 2:
                        target = selectTarget(atkTeam);
                        targetConditionForNonComputer(target, atkTeam);
                        ((Buffer) attacker).attackUp(target);
                        break;
                    case 3:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        ((Buffer) attacker).weaken(target);
                        break;
                    case 4:
                        target = selectTarget(atkTeam);
                        targetConditionForNonComputer(target, atkTeam);
                        ((Buffer) attacker).defenseUp(target);
                        break;
                    case 5:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        ((Buffer) attacker).defenseBreak(target);
                        break;
                }
                break;
            case "Poisoner":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. throw sleeping poison\n3. poisoner body mode");
                System.out.print("select action by index: ");
                switch (scanner.nextInt()) {
                    case 1:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 2:
                        target = selectTarget(defTeam);
                        targetConditionForNonComputer(target, defTeam);
                        ((Poisoner) attacker).sleepingPoison(target);
                        break;
                    case 3:
                        ((Poisoner)attacker).poisonerBodyMode();
                        break;
                }
                break;
            default:
                System.out.println("there is no match hero type on chosen_hero_database");
        }
        poisonedStatusSetChanging(atkTeam.getHeroes());
        boolean isDecided = decisionYesOrNo("deciding to use spell [Y/y/N/n]: ");
        if(isDecided) {
            System.out.println("decided to use spell");
            turnSpellForNonComputer(atkTeam, defTeam);
        }
        else{
            System.out.println("decided not to use spell");
        }
    }
    private void turnHeroForComputer(Team atkTeam, Team defTeam) {
        boolean isTurnSkipped = onlyOnePoisonedLeft(atkTeam);
        if (isTurnSkipped) {return;}
        Hero attacker = selectHeroTurnRandomly(atkTeam);
        heroTurnConditionForComputer(attacker, atkTeam);
        Hero target;
        switch (attacker.getType()) {
            case "Attacker":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. attack all heroes of opponent team");
                System.out.println("select action randomly: ");
                switch (random.nextInt(2)) {
                    case 0:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 1:
                        ((Attacker) attacker).attackOpponentTeam(defTeam.getHeroes());
                        break;
                }
                break;
            case "Tanker":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. protect teammate\n3. protect all heroes of your team");
                System.out.println("select action randomly: ");
                switch (random.nextInt(3)) {
                    case 0:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 1:
                        target = selectTargetRandomly(atkTeam);
                        targetConditionForComputer(target, atkTeam);
                        ((Tanker) attacker).protect(target);
                        break;
                    case 2:
                        ((Tanker) attacker).protectTeam(atkTeam.getHeroes());
                        break;
                }
                break;
            case "Healer":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. heal teammate\n3. heal all heroes of your team");
                System.out.println("select action randomly: ");
                switch (random.nextInt(3)) {
                    case 0:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 1:
                        target = selectTargetRandomly(atkTeam);
                        targetConditionForComputer(target, defTeam);
                        ((Healer) attacker).heal(target);
                        break;
                    case 2:
                        ((Healer) attacker).healTeam(atkTeam.getHeroes());
                        break;
                }
                break;
            case "Buffer":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. buff teammate's attack\n3. debuff opponent's attack\n4. buff teammate's defense\n5. debuff opponent's defense");
                System.out.println("select action randomly: ");
                switch (random.nextInt(5)) {
                    case 0:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 1:
                        target = selectTargetRandomly(atkTeam);
                        targetConditionForComputer(target, atkTeam);
                        ((Buffer) attacker).attackUp(target);
                        break;
                    case 2:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        ((Buffer) attacker).weaken(target);
                        break;
                    case 3:
                        target = selectTargetRandomly(atkTeam);
                        targetConditionForComputer(target, atkTeam);
                        ((Buffer) attacker).defenseUp(target);
                        break;
                    case 4:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        ((Buffer) attacker).defenseBreak(selectTargetRandomly(defTeam));
                        break;
                }
                break;
            case "Poisoner":
                System.out.println("++++++ ACTIONS ++++++\n1. attack opponent\n2. throw sleeping poison");
                System.out.println("select action randomly: ");
                switch (random.nextInt(3)) {
                    case 0:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        attacker.attack(target);
                        break;
                    case 1:
                        target = selectTargetRandomly(defTeam);
                        targetConditionForComputer(target, defTeam);
                        ((Poisoner) attacker).sleepingPoison(target);
                        break;
                    case 2:
                        ((Poisoner)attacker).poisonerBodyMode();
                        break;
                }
                break;
            default:
                System.out.println("there is no match battle spell on chosen_hero_database");
        }
        poisonedStatusSetChanging(atkTeam.getHeroes());
        System.out.print("deciding to use spell randomly: ");
        int decision = random.nextInt(2);
        if(decision == 0) {
            System.out.println("decided to use spell");
            turnSpellForComputer(atkTeam, defTeam);
        }
        else {
            System.out.println("decided not to use spell");
        }
    }
    private void heroTurnConditionForNonComputer(Hero hero, Team team) {
        while(!hero.isAlive() || hero.getPoisonedStatus()) {
            if(!hero.isAlive()) {
                System.out.println(hero.getName() + " is dead, please re-select the hero from your team");
            } else {
                System.out.println(hero.getName() + " is poisoned, please re-select the hero from your team");
            }
            hero = selectHeroTurn(team);
        }
    }
    private void targetConditionForNonComputer(Hero hero, Team team) {
        while(!hero.isAlive()) {
            System.out.println(hero.getName() + " is dead, please re-select the target");
            hero = selectTarget(team);
        }
    }
    private void heroTurnConditionForComputer(Hero hero, Team team) {
        while(!hero.isAlive() || hero.getPoisonedStatus()) {
            if(!hero.isAlive()) {
                System.out.println(hero.getName() + " is dead, re-selecting the hero");
            } else {
                System.out.println(hero.getName() + " is poisoned, re-selecting the hero");
            }
            hero = selectHeroTurnRandomly(team);
        }
    }
    private void targetConditionForComputer(Hero hero, Team team) {
        while (!hero.isAlive()) {
            System.out.println(hero.getName() + " is dead, re-selecting the target");
            hero = selectTargetRandomly(team);
        }
    }
    private boolean onlyOnePoisonedLeft(Team team) {
        int poisoned = 0;
        int dead = 0;
        for (Hero hero : team.getHeroes()) {
            if(hero.getPoisonedStatus() && hero.isAlive()) {
                poisoned++;
            }
            if(!hero.isAlive() && !hero.getPoisonedStatus()) {
                dead++;
            }
        }
        if(poisoned == 1 && (dead == 2 || dead == 4)) {
            System.out.println(team.getTeamName() + "'s turn is skipped due to the only one poisoned hero left");
            return true;
        }
        return false;
    }
    private void poisonedStatusSetChanging(ArrayList<Hero> heroes) {
        for (Hero hero : heroes) {
            if (hero.getPoisonedStatus()) {
                hero.setPoisonedStatus(false);
            }
        }
    }
    private void turnSpellForNonComputer(Team atkTeam, Team defTeam) {
        BattleSpell battleSpell = selectSpell(atkTeam);
        Hero target;
        switch (battleSpell.getSpellName()) {
            case "Meteor-Rain":
                battleSpell.meteorRain(defTeam.getHeroes(), atkTeam);
                break;
            case "Thunder-Strike":
                System.out.println("SELECTING THE TARGET");
                target = selectTarget(defTeam);
                targetConditionForNonComputer(target, defTeam);
                battleSpell.thunderStrike(target, atkTeam);
                break;
            case "Vampiric-Touch":
                System.out.println("SELECTING THE TARGET");
                target = selectTarget(defTeam);
                targetConditionForNonComputer(target, defTeam);
                System.out.println("SELECTING THE RECEIVER");
                Hero receiver = selectTarget(atkTeam);
                targetConditionForNonComputer(receiver, atkTeam);
                battleSpell.vampiricTouch(target, receiver, atkTeam);
                break;
            case "Sacrificer":
                System.out.println("SELECTING THE SACRIFICED");
                target = selectTarget(atkTeam);
                targetConditionForNonComputer(target, atkTeam);
                battleSpell.sacrificer(selectTarget(atkTeam), target, atkTeam);
                break;
            default:
                System.out.println("there is no match battle spell on spell_database");
        }
    }
    private void turnSpellForComputer(Team atkTeam, Team defTeam) {
        BattleSpell battleSpell = selectSpellRandomly(atkTeam);
        Hero target;
        switch (battleSpell.getSpellName()) {
            case "Meteor-Rain":
                battleSpell.meteorRain(defTeam.getHeroes(), atkTeam);
                break;
            case "Thunder-Strike":
                System.out.println("SELECTING THE TARGET");
                target = selectTargetRandomly(defTeam);
                targetConditionForComputer(target, defTeam);
                battleSpell.thunderStrike(target, atkTeam);
                break;
            case "Vampiric-Touch":
                System.out.println("SELECTING THE TARGET");
                target = selectTargetRandomly(defTeam);
                targetConditionForComputer(target, defTeam);
                System.out.println("SELECTING THE RECEIVER");
                Hero receiver = selectTargetRandomly(atkTeam);
                targetConditionForComputer(receiver, atkTeam);
                battleSpell.vampiricTouch(target, receiver, atkTeam);
                break;
            case "Sacrificer":
                System.out.println("SELECTING THE SACRIFICED");
                target = selectTargetRandomly(atkTeam);
                targetConditionForComputer(target, atkTeam);
                battleSpell.sacrificer(selectTargetRandomly(atkTeam), target, atkTeam);
                break;
            default:
                System.out.println("there is no match battle spell on spell_database");
        }
    }
    private Hero chooseHeroes(String fileName, int choice) {
        Team availableHero = new Team();
        try{
            availableHero = new Team(fileName); //akan membaca semua hero di file dan langsung dimasukkan ke array list
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return availableHero.getHeroes().get(choice);
    }
    private BattleSpell chooseSpells() {
        Team spellTeam = new Team();
        spellTeam.readAllSpellProvided("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\spell_database.txt");
        spellTeam.showTeamBattleSpell();
        System.out.print("choose battle spell for team by index [1-4]: ");
        int choice = scanner.nextInt();
        return spellTeam.getSpells().get(choice-1);
    }
    private BattleSpell chooseSpellsRandomly() {
        Team spellTeam = new Team();
        spellTeam.readAllSpellProvided("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\spell_database.txt");
        spellTeam.showTeamBattleSpell();
        System.out.println("choose battle spells for team [randomly]:");
        int choice = random.nextInt(spellTeam.getAmountOfSpell());
        return spellTeam.getSpells().get(choice);
    }
    private Hero selectHeroTurn(Team team) {
        System.out.println("select hero to play: ");
        System.out.println(team.toString());
        System.out.print("select hero to play by index: ");
        int choice = scanner.nextInt();
        return team.getHeroes().get(choice-1);
    }
    private Hero selectHeroTurnRandomly(Team team) {
        System.out.println("select hero to play: ");
        System.out.println(team.toString());
        System.out.println("select hero to play randomly: ");
        int choice = random.nextInt(team.getAmountOfTeamMember());
        return team.getHeroes().get(choice);
    }
    private Hero selectTarget(Team team) {
        System.out.println("select target: ");
        System.out.println(team.toString());
        System.out.print("select target by index: ");
        int choice = scanner.nextInt();
        return team.getHeroes().get(choice-1);
    }
    private Hero selectTargetRandomly(Team team) {
        System.out.println("select target: ");
        System.out.println(team.toString());
        System.out.println("select target randomly: ");
        int choice = random.nextInt(team.getAmountOfTeamMember());
        return team.getHeroes().get(choice);
    }
    private BattleSpell selectSpell(Team team) {
        System.out.println("========== YOUR SPELLS =========");
        team.showTeamBattleSpell();
        System.out.print("use battle spell by index: ");
        int index = scanner.nextInt();
        return team.getSpells().get(index-1);
    }
    private BattleSpell selectSpellRandomly(Team team) {
        System.out.println("========== YOUR SPELLS =========");
        team.showTeamBattleSpell();
        System.out.println("use battle spell randomly: ");
        int choice = random.nextInt(team.getAmountOfSpell());
        return team.getSpells().get(choice);
    }

}
