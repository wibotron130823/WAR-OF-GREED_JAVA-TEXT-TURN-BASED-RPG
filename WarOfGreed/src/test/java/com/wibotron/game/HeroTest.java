package com.wibotron.game;
import com.wibotron.game.logic.hero.Attacker;
import com.wibotron.game.logic.hero.Healer;
import com.wibotron.game.logic.hero.Hero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/*
- Unit Testing for the Scope of Class Hero and its inside methods
- Goal: making sure some methods inside Hero Class work well
- Note: Assert mainly used here only for validating things
- Unit Test running independently
*/

public class HeroTest {

    @Test
    void testHeroAttack() {
        //testing scenario: making sure HP of the attacked one decreased with true rules (element logic, etc.)
        Hero attacker = new Attacker("Ryu", "Attacker", "Fire", 30, 10, 50);
        Hero enemy = new Healer("Zoe", "Healer", "Earth", 50,  20, 55);

        //simulate the attack
        attacker.attack(enemy);

        //making sure HP of the enemy decreased well
        assertTrue(enemy.getCurrentHealth()<55, "HP of the enemy should've decreased after getting attacked");
        //assertTrue: method for validating a condition(boolean) based on a scenario that dev wants
    }

    @Test
    void testHeroAfterGettingAttacked() {
        //testing scenario: making sure HP of the attacked one decreased with true rules (element logic, etc.)
        Hero attacker = new Attacker("Ryu", "Attacker", "Water", 30, 10, 50);
        Hero enemy = new Healer("Zoe", "Healer", "Fire", 50,  20, 55);

        //simulate the attack
        attacker.attack(enemy);

        //making sure HP of the enemy decreased well
        double expectedCurrentHealth = 55 - (1.2*(attacker.getCurrentAttack()-enemy.getCurrentDefense()));
        double actualCurrentHealth = enemy.getCurrentHealth();
        assertEquals(expectedCurrentHealth, actualCurrentHealth, "HP of the enemy should've decreased after getting attacked");
        //assertEquals: method for validating a whether the dev expectation is as big as the real result
    }
}
