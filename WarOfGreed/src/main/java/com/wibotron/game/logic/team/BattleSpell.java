package com.wibotron.game.logic.team;

import com.wibotron.game.logic.hero.Hero;
import java.util.ArrayList;
import static com.wibotron.game.logic.team.Team.amountOfAliveHeroInTeam;

public class BattleSpell {
    private String spellName;
    private String spellInfo;
    private int remainingSpellTicket_3V3 = 3;
    private int remainingSpellTicket_5V5 = 5;

    BattleSpell(String spellName, String spellInfo) {
        this.spellName = spellName;
        this.spellInfo = spellInfo;
    }

    public String getSpellName() {
        return this.spellName;
    }
    String getSpellInfo() {
        return this.spellInfo;
    }
    public void meteorRain(ArrayList<Hero> opponentTeam, Team spellUserTeam) {
        System.out.printf("Meteor rain gives %f AoE DMG\n", 60 / (double)amountOfAliveHeroInTeam(opponentTeam));
        for(Hero hero : opponentTeam) {
            if (hero.isAlive() && hero != null) {
                double damage = Math.max(0, 60 / (double)amountOfAliveHeroInTeam(opponentTeam) - hero.getCurrentDefense());
                hero.attacked(damage);
                System.out.println(hero.getName() + " receives " + damage + " DMG from the Meteor Rain");
                System.out.println(hero.getName() + " now has " + hero.getCurrentHealth() + " HP");
                hero.afterAttackConditionStatus(hero);
            }
        }
        spellTicketStatus(spellUserTeam);
    }
    public void thunderStrike(Hero target, Team spellUserTeam) {
        double damage = Math.max(0, 30 - target.getCurrentDefense());
        System.out.println("Thunder Strike attacks " + target.getName() + " with " +  damage + " DMG");
        target.attacked(damage);
        System.out.println(target.getName() + " receives " + damage + " DMG from the Thunder Strike");
        System.out.println(target.getName() + " now has " + target.getCurrentHealth() + " HP");
        target.afterAttackConditionStatus(target);
        spellTicketStatus(spellUserTeam);
    }
    public void vampiricTouch(Hero target, Hero receiver, Team spellUserTeam) {
        double damage = Math.max(0, 24 - target.getCurrentDefense());
        target.attacked(damage);
        receiver.setCurrentHealth(receiver.getCurrentHealth() + damage);
        System.out.println(target.getName() + "'s health is decreased for " + damage + " HP");
        System.out.println(receiver.getName() + "'s health is increased for " + damage + " HP");
        target.afterAttackConditionStatus(target);
        spellTicketStatus(spellUserTeam);
    }
    public void sacrificer(Hero target, Hero sacrificed, Team spellUserTeam) {
        if (!target.isAlive() && sacrificed.getCurrentHealth() > target.getBaseHealth() && target != sacrificed) {
            System.out.println(sacrificed.getName() + " sacrificed its Life for " + target.getName());
            System.out.println(target.getName() + " is back");
            System.out.println(sacrificed.getName() + " is died");
            target.setCurrentHealth(target.getCurrentHealth() + sacrificed.getCurrentHealth());
            sacrificed.setCurrentHealth(0);
            spellTicketStatus(spellUserTeam);
        }
    }
    public void spellTicketStatus(Team team) {
        if(team.getAmountOfTeamMember() == 3) {
            remainingSpellTicket_3V3--;
            if(remainingSpellTicket_3V3 == 0) {
                System.out.println("your all spell ticket has been used!");
            }
            else if(remainingSpellTicket_3V3 == 1){
                System.out.println("you still have " + remainingSpellTicket_3V3 + " spell ticket left to use");
            } else {
                System.out.println("you still have " + remainingSpellTicket_3V3 + " spell tickets left to use");
            }
        } else {
            remainingSpellTicket_5V5--;
            if(remainingSpellTicket_5V5 == 0) {
                System.out.println("your all spell ticket has been used!");
            }
            else if(remainingSpellTicket_5V5 == 1){
                System.out.println("you still have " + remainingSpellTicket_5V5 + " spell ticket left to use");
            } else {
                System.out.println("you still have " + remainingSpellTicket_5V5 + " spell tickets left to use");
            }
        }
    }


}
