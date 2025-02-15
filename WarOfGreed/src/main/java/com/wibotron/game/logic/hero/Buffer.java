package com.wibotron.game.logic.hero;

import static com.wibotron.game.utils.Utils.scanner;

public class Buffer extends Hero {
    private String name;
    private String type;
    private String element;
    private double baseAttack;
    private double baseDefense;
    private double baseHealth;
    private double currentAttack;
    private double currentDefense;
    private double currentHealth;

    public Buffer(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
        super(name, type, element, baseAttack, baseDefense, baseHealth);
    }

    //buff action
    public void attackUp(Hero target) {
        if(remainingBuffTicket>0) {
            target.attackIncreased();
            target.buffed_debuffed_attack_sign = "attack-buffed-sign";
            remainingBuffTicket--;
            printRemainingBuffTicketStatus();
        } else {
            printRemainingBuffTicketStatus();
            System.out.println("++++++ ALTERNATIVE ACTIONS ++++++\n1. attack opponent\n2. add buff ticket");
            System.out.print("choose alternative action by index [1 or 2]: ");
            switch (scanner.nextInt()) {
                case 1:
                    this.attack(target);
                    break;
                case 2:
                    addBuffTicket();
                    break;
                default:
                    attackUp(target);
            }
        }
    }
    public void defenseUp(Hero target) {
        if(remainingBuffTicket>0) {
            target.defenseIncreased();
            target.buffed_debuffed_defense_sign = "defense-buffed-sign";
            remainingBuffTicket--;
            printRemainingBuffTicketStatus();
        } else {
            printRemainingBuffTicketStatus();
            System.out.println("++++++ ALTERNATIVE ACTIONS ++++++\n1. attack opponent\n2. add buff ticket");
            System.out.print("choose alternative action by index [1 or 2]: ");
            switch (scanner.nextInt()) {
                case 1:
                    this.attack(target);
                    break;
                case 2:
                    addBuffTicket();
                    break;
                default:
                    defenseUp(target);
            }
        }
    }
    //debuff action
    public void weaken(Hero target) {
        if(remainingBuffTicket>0) {
            target.attackDecreased();
            target.buffed_debuffed_attack_sign = "attack-debuffed-sign";
            remainingBuffTicket--;
            printRemainingBuffTicketStatus();
        } else {
            printRemainingBuffTicketStatus();
            System.out.println("++++++ ALTERNATIVE ACTIONS ++++++\n1. attack opponent\n2. add buff ticket");
            System.out.print("choose alternative action by index [1 or 2]: ");
            switch (scanner.nextInt()) {
                case 1:
                    this.attack(target);
                    break;
                case 2:
                    addBuffTicket();
                    break;
                default:
                    weaken(target);
            }
        }
    }
    public void defenseBreak(Hero target) {
        if(remainingBuffTicket>0) {
            target.defenseDecreased();
            target.buffed_debuffed_defense_sign = "defense-debuffed-sign";
            remainingBuffTicket--;
            printRemainingBuffTicketStatus();
        } else {
            printRemainingBuffTicketStatus();
            System.out.println("++++++ ALTERNATIVE ACTIONS ++++++\n1. attack opponent\n2. add buff ticket");
            System.out.print("choose alternative action by index [1 or 2]: ");
            switch (scanner.nextInt()) {
                case 1:
                    this.attack(target);
                    break;
                case 2:
                    addBuffTicket();
                    break;
                default:
                    defenseBreak(target);
            }
        }
    }
    public void addBuffTicket() {
        printRemainingBuffTicketStatus();
        remainingBuffTicket++;
    }
    void printRemainingBuffTicketStatus() {
        if(remainingBuffTicket == 0) {
            System.out.println("you have no buff-ticket left");
        } else if(remainingBuffTicket == 1) {
            System.out.println("you have 1 buff-ticket left");
        } else {
            System.out.printf("you have %d buff-tickets left\n", remainingBuffTicket);
        }
    }

}
