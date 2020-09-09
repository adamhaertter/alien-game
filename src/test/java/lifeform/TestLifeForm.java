package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm {

  /**
   * When a LifeForm is created, it should know how many life points it has.
   */
  @Test
  public void testInitializationPoints() {
    MockLifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals(40, entity.getCurrentLifePoints());
  }

  /**
   * When a LifeForm is created, it should know its own Name.
   */
  @Test
  public void testInitializationName() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
  }

  /**
   * Tests the ability of a LifeForm to take damage and alter its current life
   * points in response to the damage dealt.
   */
  @Test
  public void testHitDamage() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    entity.takeHit(5);
    assertEquals(35, entity.getCurrentLifePoints());
  }

  /**
   * Tests the functionality of the LifeForm taking damage multiple times in a
   * row. The LifeForm should properly register each hit and account for the
   * damage taken by each.
   */
  @Test
  public void testHitTwice() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    entity.takeHit(5);
    assertEquals(35, entity.getCurrentLifePoints());
    entity.takeHit(15);
    assertEquals(20, entity.getCurrentLifePoints());
  }
}
