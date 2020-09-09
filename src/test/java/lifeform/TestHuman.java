package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHuman {

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

}
