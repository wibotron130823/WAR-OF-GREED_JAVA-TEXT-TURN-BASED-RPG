package com.wibotron.game.logic.hero;

import java.util.ArrayList;

import static com.wibotron.game.logic.team.Team.amountOfAliveHeroInTeam;

public class Healer extends  Hero{
    private String name;
    private String type;
    private String element;
    private double baseAttack;
    private double baseDefense;
    private double baseHealth;
    private double currentAttack;
    private double currentDefense;
    private double currentHealth;

    public Healer(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
        super(name, type, element, baseAttack, baseDefense, baseHealth);
        this.name = name;
        this.type = type;
        this.element = element;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.currentAttack = baseAttack;
        this.currentDefense = baseDefense;
        this.currentHealth = baseHealth;
    }

    public void heal(Hero target) {
        try {
            if (target.isAlive() && target != null && target != this) {
                target.healed(this.currentAttack);
                System.out.println(target.getName() + " receives " + this.currentAttack + " HP from " + this.name);
                System.out.println(target.getName() + " now has " + target.getCurrentHealth() + " HP");
            }
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }
    public void healTeam(ArrayList<Hero> myTeam) {
        System.out.println(this.name + " heals its teammate with " + this.currentAttack + " HP");
        System.out.println("each hero of your team is healed with " + this.currentAttack / (amountOfAliveHeroInTeam(myTeam)-1) + " HP");
        for (Hero hero: myTeam) {
            if(hero.isAlive() && hero != null &&  hero != this) {
                hero.healed(this.currentAttack);
                System.out.println(hero.getName() + " receives " + this.currentAttack / (amountOfAliveHeroInTeam(myTeam)-1) + " HP from " + this.name);
                System.out.println(hero.getName() + " now has " + hero.getCurrentHealth() + " HP");
            }
        }
    }
}
