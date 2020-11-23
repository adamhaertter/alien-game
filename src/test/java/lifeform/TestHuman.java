package lifeform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adam Haertter - modified by Brennan Mulligan and Scott Bucher
 *
 */
public class TestHuman {

  /*
   * Creates a Human and checks that its max speed is 3
   */
  @Test
  public void testMaxSpeed() {
    Human human = new Human("Jeffery", 20, 10);
    assertEquals(human.maxSpeed, 3);
  }

  /**
   * Tests the Human's initialization by checking its default name and life
   * points.
   */
  @Test
  public void testInitialization() {
    Human human = new Human("Bart", 40, 0);
    assertEquals(human.getName(), "Bart");
    assertEquals(human.getCurrentLifePoints(), 40);
  }

  /**
   * Creates a new Human and checks that the default attackStrength of 5 is
   * properly set at initialization.
   */
  @Test
  public void testDefaultStrength() {
    Human h = new Human("Daryl", 40, 0);
    assertEquals(h.getAttackStrength(), 5);
  }

  /**
   * Tests the methods of setting and changing the armor values of a human.
   */
  @Test
  public void testSetArmor() {
    Human human = new Human("Tom", 30, 0);
    human.setArmorPoints(5);
    assertEquals(human.getArmorPoints(), 5);
  }

  /**
   * Tests the methods of getting a specific armor point value set up at
   * initialization.
   */
  @Test
  public void testGetArmor() {
    Human human = new Human("Jeff", 30, 16);
    assertEquals(16, human.getArmorPoints());
  }

  /**
   * Checks the bounds of setting armor points by trying to set a negative value.
   * When a negative value is set, the armor is set to zero.
   */
  @Test
  public void testArmorBounds() {
    Human human = new Human("Jim", 20, -5);
    assertEquals(human.getArmorPoints(), 0);
  }

  /**
   * Creates a new instance of Human and deals damage less than the Human's
   * armorPoints. This test checks to ensure that the Human remains undamaged.
   */
  @Test
  public void testArmorAbsorb() {
    Human human = new Human("Brian", 20, 10);
    human.takeHit(5);
    assertEquals(human.getCurrentLifePoints(), 20);
  }

  /**
   * Creates a new instance of Human and deals damage greater than the Human's
   * armorPoints. This test checks to ensure that the Human takes damage reduced
   * by its own armor.
   */
  @Test
  public void testArmorReduce() {
    Human human = new Human("Raven", 20, 10);
    human.takeHit(15);
    assertEquals(human.getCurrentLifePoints(), 15);
  }

  /**
   * Creates a new instance of Human and deals damage equal to the Human's
   * armorPoints. This test checks to ensure that the Human remains undamaged in
   * this specific scenario.
   */
  @Test
  public void testArmorPerfect() {
    Human human = new Human("Nishant", 20, 10);
    human.takeHit(10);
    assertEquals(human.getCurrentLifePoints(), 20);
  }
}
