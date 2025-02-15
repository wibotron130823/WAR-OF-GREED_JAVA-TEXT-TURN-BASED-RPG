package com.wibotron.game.logic.hero;

import java.util.ArrayList;

import static com.wibotron.game.logic.team.Team.amountOfAliveHeroInTeam;

public class Tanker extends Hero{
    private String name;
    private String type;
    private String element;
    private double baseAttack;
    private double baseDefense;
    private double baseHealth;
    private double currentAttack;
    private double currentDefense;
    private double currentHealth;


    public Tanker(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
        super(name, type, element, baseAttack, baseDefense, baseHealth);
        this.name = name;
        this.type = type;
        this.element = element;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.currentAttack = baseAttack;
        this.currentHealth = baseHealth;
        this.currentDefense = baseDefense;
    }

    public void protect(Hero target) {
        if (target.isAlive() && target != null && target != this) {
            target.Protected(this.currentDefense/2);
            target.protectedBy = this;
            target.protectionSign = "protection-type-1-key";
            System.out.println(this.name + " protects " + target.getName() + " by sharing a half of its current defense -->" + this.currentDefense / 2 + " DEF");
            System.out.println(target.getName() + " now has " + target.getCurrentDefense() + " temporary DEF for the next one turn");
            this.currentDefense -= 0.5*this.currentDefense;
        }
    }
    public void protectTeam(ArrayList<Hero> myTeam) {
        this.teamProtected = amountOfAliveHeroInTeam(myTeam);
        System.out.println(this.name + " protects its team with " + this.currentDefense + " DEF");
        System.out.println("each hero of your team is protected with " + this.currentDefense / this.teamProtected + " DEF");
        for(Hero hero: myTeam) {
            if (hero.isAlive() && hero != null && hero != this) {
                hero.Protected(this.currentDefense / (this.teamProtected));
                hero.protectedBy = this;
                hero.protectionSign = "protection-type-2-key";
                System.out.println(hero.getName() + " now has " + hero.getCurrentDefense() + " temporary DEF for the next one turn");
                this.currentDefense -= this.currentDefense / (this.teamProtected);
            }
        }
    }

}
