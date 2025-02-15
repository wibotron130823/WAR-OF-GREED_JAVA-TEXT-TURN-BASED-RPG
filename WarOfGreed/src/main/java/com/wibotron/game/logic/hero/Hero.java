package com.wibotron.game.logic.hero;
import java.util.Map;
import java.util.Random;

public class Hero {
    private String name;
    private String type;
    private String element;
    private double baseAttack;
    private double baseDefense;
    private double baseHealth;
    private double currentAttack;
    protected double currentDefense;
    private double currentHealth;
    protected String protectionSign;
    protected String buffed_debuffed_defense_sign;
    protected String buffed_debuffed_attack_sign;
    private boolean poisonedStatus = false;
    protected Tanker protectedBy;
    protected int teamProtected;
    protected double buffOrDebuffDefensePow;
    protected double buffOrDebuffAttackPow;
    protected static int remainingBuffTicket = 5;
    Random random = new Random();

    Hero(String name, String type, String element, double baseAttack, double baseDefense, double baseHealth) {
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

    Hero() {}

    public void attack(Hero target) {
        double damage = Math.max(0, this.currentAttack - target.currentDefense);
        double elementMultiplier = getElementMultiplier(this.element, target.element);
        damage *= elementMultiplier;
        target.attacked(damage);
        System.out.println(this.name + " attacks " + target.name + " with " + this.currentAttack + " DMG");
        System.out.println(target.name + " receives " + damage + " DMG from " + this.name + (elementMultiplier > 1 ? " (Effective due to the Elements!)" : elementMultiplier < 1 ? " (Not Effective due to the elements!)" : ""));
        System.out.println(target.name + " now has " + target.currentHealth + " HP");
        afterAttackConditionStatus(target);
    }
    public void attacked(double damage){
        this.currentHealth = Math.max(0, this.currentHealth - damage);
    }
    public void afterAttackConditionStatus(Hero target) {
        //target protection no longer exists && the tank who protects the target will get its defense back
        if (target.protectionSign == "protection-type-1-key") {
            target.currentDefense -= target.currentDefense / 2;
            this.currentDefense += target.currentDefense / 2;
            target.protectionSign = "";
            System.out.println(target.getName() + "'s temporary protection type 1 no longer exists");
        } else if (target.protectionSign == "protection-type-2-key") {
            target.protectionSign = "";
            this.currentDefense += target.protectedBy.getBaseDefense()/target.protectedBy.teamProtected;
            System.out.println(target.getName() + "'s temporary protection type 2 no longer exists");
        }

        //target's buff or debuff effect no longer exists
        if(target.buffed_debuffed_defense_sign == "defense-buffed-sign") {
            target.currentDefense -= target.buffOrDebuffDefensePow;
            target.buffed_debuffed_defense_sign = "";
            System.out.println(target.getName() + "'s defense buff effect no longer exists");
        } else if(target.buffed_debuffed_defense_sign == "defense-debuffed-sign") {
            target.buffOrDebuffDefensePow += target.buffOrDebuffDefensePow;
            target.buffed_debuffed_defense_sign = "";
            System.out.println(target.getName() + "'s defense debuff effect no longer exists");
        }

        //Hero who gets buff or debuff attack effect, the effect no longer exists
        if(this.buffed_debuffed_attack_sign == "attack-buffed-sign") {
            this.currentAttack -= this.buffOrDebuffAttackPow;
            this.buffed_debuffed_attack_sign = "";
            System.out.println(this.getName() + "'s attack buff effect no longer exists");
        } else if(this.buffed_debuffed_attack_sign == "attack-debuffed-sign") {
            this.currentAttack += this.buffOrDebuffAttackPow;
            this.buffed_debuffed_attack_sign = "";
            System.out.println(this.getName() + "'s attack debuff effect no longer exists");
        }
        if(target instanceof Poisoner) {
            if (((Poisoner) target).getPoisonerMode()) {
                this.currentHealth = Math.max(0, this.currentHealth - (target.getCurrentAttack() * 0.4 - this.currentDefense));
                System.out.println(target.getName() + " using poisoner body mode then " + this.name + " is attacked by the poison for " + target.currentAttack * 0.4 + " DMG");
                System.out.println(this.name + " now has " + this.currentHealth + " HP");
                ((Poisoner) target).setPoisonerMode(false);
            }
        }
    }
    private double getElementMultiplier(String attackerElement, String targetElement) {
        Map<String, String> strongAgainst = Map.of(
                "Fire", "Metal",
                "Water", "Fire",
                "Earth", "Water",
                "Metal", "Wood",
                "Wood", "Earth"
        );
        if(strongAgainst.getOrDefault(attackerElement, "").equals(targetElement)) {
            return 1.2; //the current damage would be 1,2 * current damage (+20%)
        } else if(strongAgainst.getOrDefault(targetElement, "").equals(attackerElement)) {
            return 0.8; //the current damage would be 0,8 * current damage (-20%)
        }
        return 1.0; //normal
    }
    void Protected(double protectorDefense) {
         this.currentDefense = Math.max(this.baseDefense, this.currentDefense + protectorDefense);
    }
    void healed(double healingPower) {
        this.currentHealth = Math.min(this.currentHealth + healingPower, this.baseHealth);
    }
    void attackIncreased() {
        double damageIncreased = Math.max(0, random.nextDouble(6, 11+1));
        this.buffOrDebuffAttackPow = damageIncreased;
        this.currentAttack += damageIncreased;
        System.out.println(this.getName() +"'s DMG is buffed into " + this.getCurrentAttack() + " DMG");
    }
    void defenseIncreased() {
        double defenseIncreased = Math.max(0, random.nextDouble(6, 11+1));
        this.buffOrDebuffDefensePow = defenseIncreased;
        this.currentDefense += defenseIncreased;
        System.out.println(this.getName() +"'s DEF is buffed into " + this.getCurrentDefense() + " DEF");
    }
    void attackDecreased() {
        int damageDecreased = Math.max(0, random.nextInt(6, 11+1));
        this.buffOrDebuffAttackPow = damageDecreased;
        this.currentAttack -= damageDecreased;
        System.out.println(this.getName() +"'s DMG is de-buffed into " + this.getCurrentAttack() + " DMG");
    }
    void defenseDecreased() {
        int defenseDecreased = Math.max(0, random.nextInt(6, 11+1));
        this.buffOrDebuffDefensePow = defenseDecreased;
        this.currentDefense -= defenseDecreased;
        System.out.println(this.getName() +"'s DEF is de-buffed into " + this.getCurrentDefense() + " DEF");
    }

    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getElement() {
        return this.element;
    }
    public double getBaseAttack() {
        return this.baseAttack;
    }
    public double getBaseDefense() {
        return this.baseDefense;
    }
    public double getBaseHealth() {
        return this.baseHealth;
    }
    public double getCurrentAttack() {
        return this.currentAttack;
    }
    public double getCurrentDefense() {
        return this.currentDefense;
    }
    public double getCurrentHealth() {
        return this.currentHealth;
    }
    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }
    public boolean getPoisonedStatus() {
        return this.poisonedStatus;
    }
    public void setPoisonedStatus(boolean poisonedStatus) {
        this.poisonedStatus = poisonedStatus;
    }
    public boolean isAlive() {
        return this.currentHealth > 0;
    }
    @Override
    public String toString() {
        return String.format("%s(%s), Element = %s, HP = %f/%f, DMG = %f/%f, DEF = %f/%f",
                this.name,
                this.type,
                this.element,
                this.currentHealth,
                this.baseHealth,
                this.currentAttack,
                this.baseAttack,
                this.currentDefense,
                this.baseDefense
        );
    }
}
