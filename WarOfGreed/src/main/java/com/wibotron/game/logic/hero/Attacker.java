package com.wibotron.game.logic.hero;
import java.util.ArrayList;
import static com.wibotron.game.logic.team.Team.amountOfAliveHeroInTeam;

public class Attacker extends Hero {
  private String name;
  private String type;
  private String element;
  private double baseAttack;
  private double baseDefense;
  private double baseHealth;
  private double currentAttack;
  private double currentDefense;
  private double currentHealth;

  public Attacker(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
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

  public Attacker() {}

  public void attackOpponentTeam(ArrayList<Hero> opponentTeam) {
   System.out.println(this.name + " attacks opponent team  with " + this.currentAttack + " DMG");
   System.out.println("each hero of the opponent team is attacked with "+ this.currentAttack/ amountOfAliveHeroInTeam(opponentTeam) + " DMG");
   for(Hero hero: opponentTeam) {
      if (hero.isAlive() && hero != null && hero != this) {
          double damage = Math.max(0, this.currentAttack / amountOfAliveHeroInTeam(opponentTeam) - hero.getCurrentDefense());
          hero.attacked(damage);
          System.out.println(hero.getName() + " receives " + damage + " DMG from" + this.name);
          System.out.println(hero.getName() + " now has " + hero.getCurrentHealth() + " HP");
          afterAttackConditionStatus(hero);
      }
   }
  }


 }
