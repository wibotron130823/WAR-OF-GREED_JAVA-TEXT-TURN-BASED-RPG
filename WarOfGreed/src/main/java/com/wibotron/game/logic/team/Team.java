package com.wibotron.game.logic.team;
import com.wibotron.game.logic.hero.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import static com.wibotron.game.utils.Utils.scanner;

public class Team {
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<BattleSpell> spells = new ArrayList<>();
    private String teamName;
    Random random = new Random();

    public Team(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String data = bufferedReader.readLine(); //read it in one line
            while (data != null) {
                String[] parts = data.split(";");
                switch (parts[1]) {
                    case "Attacker":
                        heroes.add(new Attacker(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5])));
                        break;
                    case "Tanker":
                        heroes.add(new Tanker(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5])));
                        break;
                    case "Healer":
                        heroes.add(new Healer(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5])));
                        break;
                    case "Buffer":
                        heroes.add(new Buffer(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5])));
                        break;
                    case "Poisoner":
                        heroes.add(new Poisoner(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5])));
                        break;
                    default:
                        throw new IllegalArgumentException("invalid hero type: " + parts[1]);
                }
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Team() {}

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public void addSpell(BattleSpell spell) {
        spells.add(spell);
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<BattleSpell> getSpells() {
        return spells;
    }

    public void twoSpells() {
        if (spells.size() > 2) {
            spells = new ArrayList<>(spells.subList(spells.size() - 2, spells.size()));
        }
    }

    public int getAmountOfTeamMember() {
        return heroes.size();
    }

    public int getAmountOfSpell() {
        return spells.size();
    }

    public String getTeamName() {return this.teamName;}
    public void setTeamName(String teamName) {this.teamName = teamName;}

    public static int amountOfAliveHeroInTeam(ArrayList<Hero> team) {
        int counter = 0;
        for (Hero hero : team) {
            if (hero.isAlive() && hero != null) {
                counter++;
            }
        }
        return counter;
    }
    public boolean isTeamDefeated() {
        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void readAllSpellProvided(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                String[] parts = data.split(";");
                spells.add(new BattleSpell(parts[0], parts[1]));
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showTeamBattleSpell() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for(BattleSpell spell : spells) {
            count++;
            stringBuilder.append(String.format("%d. %s--> %s\n", count, spell.getSpellName(), spell.getSpellInfo()));
        }
        System.out.print(stringBuilder.toString());
    }
    public void readAndWriteHeroesChosenDatabase(String fileTarget, int maxChoice) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n======================================= PICK HEROES ==========================================\n");
        stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
        stringBuilder.append("| No | Name                 | Type       | Element    | DMG        | DEF        | HP         |\n");
        stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
        int count = 1;
        String[] heroes = new String[100];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\hero_database.txt"))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                heroes[count] = data;
                String[] parts = data.split(";");
                stringBuilder.append(String.format("| %-2d | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
                        count,
                        parts[0],
                        parts[1],
                        parts[2],
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5])
                ));
                data = bufferedReader.readLine();
                count++;
            }
            stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
            System.out.print(stringBuilder.toString());
            for (int i = 0; i < maxChoice; i++) {
                System.out.printf("choose hero for your team [1 - %d]: ", count - 1);
                int choice = scanner.nextInt();
                if (choice > 0 && choice <= count - 1) {
                    writeChosenHero(fileTarget, heroes[choice]);
                } else {
                    System.out.println("invalid choice, please re-choose the hero");
                    i--;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readAndWriteHeroesChosenDatabaseRandomly(String fileTarget, int maxChoice) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n======================================= PICK HEROES ==========================================\n");
        stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
        stringBuilder.append("| No | Name                 | Type       | Element    | DMG        | DEF        | HP         |\n");
        stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
        int count = 0;
        String[] heroes = new String[100];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\database\\hero_database.txt"))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                heroes[count] = data;
                String[] parts = data.split(";");
                stringBuilder.append(String.format("| %-2d | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n",
                        count+1,
                        parts[0],
                        parts[1],
                        parts[2],
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5])
                ));
                data = bufferedReader.readLine();
                count++;
            }
            stringBuilder.append("+----+----------------------+------------+------------+------------+------------+------------+\n");
            System.out.print(stringBuilder.toString());
            for (int i = 0; i < maxChoice; i++) {
                System.out.print("choose hero for team [randomly]: ");
                int choice = random.nextInt(count);
                if (choice < count) {
                    writeChosenHero(fileTarget, heroes[choice]);
                } else {
                    System.out.println("invalid choice, re-choosing the hero");
                    i--;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void cleanHeroDatabase(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            // as the append value is false, it means the value of chosen hero db will be automatically empty
            System.out.println("chosen hero database is cleaned");
        } catch (IOException e) {
            System.err.println("fail cleaning chosen hero database: " + e.getMessage());
        }
    }
    public void writeChosenHero(String fileName, String heroData) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(heroData);
            bufferedWriter.newLine();
            System.out.println("Hero successfully chosen!");
        } catch (IOException e) {
            System.err.println("Error writing hero data: " + e.getMessage());
            throw e; // Rethrow for upstream handling
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        stringBuilder.append("================================================== YOUR TEAM =====================================================\n");
        stringBuilder.append("+----+--------------------------+--------------+--------------+----------------+----------------+----------------+\n");
        stringBuilder.append("| No | Name                     | Type         | Element      | DMG            | DEF            | HP             |\n");
        stringBuilder.append("+----+--------------------------+--------------+--------------+----------------+----------------+----------------+\n");

        for (Hero hero : heroes) {
            count++;
            stringBuilder.append(String.format("| %-2d | %-24s | %-12s | %-12s | %-14s | %-14s | %-14s |\n",
                    count,
                    hero.getName(),
                    hero.getType(),
                    hero.getElement(),
                    hero.getCurrentAttack() + "/" + hero.getBaseAttack(),
                    hero.getCurrentDefense() + "/" + hero.getBaseDefense(),
                    hero.getCurrentHealth() + "/" + hero.getBaseHealth()
            ));
        }

        stringBuilder.append("+----+--------------------------+--------------+--------------+----------------+----------------+----------------+\n");

        return stringBuilder.toString();
    }



}
