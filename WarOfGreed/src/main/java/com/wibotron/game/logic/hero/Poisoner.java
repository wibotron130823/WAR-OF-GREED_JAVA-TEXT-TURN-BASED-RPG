package com.wibotron.game.logic.hero;

public class Poisoner extends Hero {
    private String name;
    private String type;
    private String element;
    private double baseAttack;
    private double baseDefense;
    private double baseHealth;
    private double currentAttack;
    private double currentDefense;
    private double currentHealth;
    private boolean poisonerMode = false;

    public Poisoner(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
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

    public void sleepingPoison(Hero target) {
        //target who gets hit by "sleeping poison" can not do any action for the next one turn
        if (target.isAlive() && target != null) {
            target.setPoisonedStatus(true);
        }
        System.out.println(target.getName() + " is poisoned for the next one turn by " + this.getName());
    }
    public void poisonerBodyMode() {
        this.poisonerMode = true;
    }
    public void setPoisonerMode(boolean poisonerMode) {
        this.poisonerMode = poisonerMode;
    }
    public boolean getPoisonerMode() {
        return this.poisonerMode;
    }
}
